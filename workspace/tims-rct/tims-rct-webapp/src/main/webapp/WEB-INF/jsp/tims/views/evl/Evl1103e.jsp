<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1103e.jsp
	// 설명 : 심사관리
	// 작성자 : 이예찬
	// 일자 : 2025.05.15
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1103e"/>    
<c:set var="svnmSearch" value="${svnm}/search"/>    
<c:set var="svnmGetApp" value="${svnm}/getApplicant"/>  
<c:set var="svnmGetSheet" value="${svnm}/getSheet"/>
<c:set var="svnmConfirmResult" value="${svnm}/confirmResult"/>
<c:set var="svnmCancelConfirmResult" value="${svnm}/cancelConfirmResult"/>
<c:set var="svnmGetResultCd" value="${svnm}/getResultCd"/>

<div class="x_panel_wrap">  
	<div class="x_panel">		
		<div class="search_condition"> 
			<div class="condition_list" style="display: flex; align-items: center; gap: 10px;">
				<dl style="margin-bottom: 0;">
					<dt><label>채용공고 차수</label></dt>
					<dd><div id="step"></div></dd>
				</dl>
				<dl style="margin-bottom: 0;">
					<dt><label>채용구분</label></dt>
					<dd><div id="ntcTypeNm"></div></dd>
				</dl> 
				<dl style="margin-bottom: 0;">
					<dt><label for="usrCd">평가위원</label></dt>
					<dd><select class="form-control" id="usrCd" name="usrCd"></select></dd>
				</dl>
				<button class="n_btn btn_md btn_c02" data-url="evl1100e" type="button" style="margin-left: auto;">돌아가기</button>
			</div>
		</div>
	</div> 
	
	<div class="x_content">
		<div class="description">※ 지원자 성명을 클릭하시면 지원서를 확인할 수 있습니다.</div>
		<div class="description">※ 모든 평가 항목에 점수를 입력한 후 확정 버튼을 클릭하시면 평가가 완료됩니다.</div>
		<div class="description">※ 확정 후에는 수정이 불가능합니다.</div>
		
		<div class="row">
			<div class="col-md-5">
				<div class="list_top">
					<div class="lft-area">
						<p class="list_title">지원자 목록</p>
					</div>
				</div>
				<div class="content_list">
					<table class="table" id="applicant-table">
						<thead>
							<tr>
								<th>성명</th>
								<th>지원분야</th>
								<th>심사여부</th>
							</tr>
						</thead>
						<tbody id="app-tbody"></tbody>
					</table>
				</div>
			</div>
			
			<div class="col-md-7">
				<div class="list_top">
					<div class="lft-area">
						<p class="list_title">평가표</p>
					</div>
					<div class="rgt-area">
						<div class="btn-group">
							<button id="btn-confirm" data-action="confirmResult" type="button" class="n_btn btn_md btn_c02">확정</button>
							<button id="btn-cancel-confirm" data-action="cancelConfirmResult" type="button" class="n_btn btn_md btn_c04" style="display:none;">확정취소</button>
						</div>
					</div>
				</div>
				<div class="content_list">
					<input type="hidden" id="ntcCd" name="ntcCd" />
					<input type="hidden" id="stepCd" name="stepCd" />
					<input type="hidden" id="appCd" name="appCd" />
					<input type="hidden" id="sheetCd" name="sheetCd" />
					<input type="hidden" id="resultCd" name="resultCd" />
					<input type="hidden" id="staffType" name="staffType" />
					
					<table class="table" id="sheet-table">
						<thead>
							<tr>
								<th>심사구분</th>
								<th>항목</th>
								<th>배점</th>
								<th>평가지표</th>
								<th>세부배점</th>
								<th>점수 기준</th>
								<th>평가 점수</th>
							</tr> 
						</thead>
						<tbody id="sheet-tbody"></tbody>
					</table> 
					
					<div class="opinion_area" style="display:none;">
						<div class="form-group">
							<label for="opinion">소견서</label>
							<textarea class="form-control" id="opinion" name="opinion" rows="3"></textarea>
						</div>
						<div class="form-group" id="minutes-area" style="display:none;">
							<label for="minutes">회의록 (위원장만 작성가능)</label>
							<textarea class="form-control" id="minutes" name="minutes" rows="3"></textarea>
						</div>
					</div>
					
					<div class="total_score" style="display:none;">
						<span>총점: </span>
						<span id="total-score">0</span>
					</div>
				</div>
			</div>
		</div>
	</div> 
</div>

<script>
// 테이블 컬럼 정의
const applicantColumns = [
    { type: "text", name: "appUsrNm", readonly: true },
    { type: "text", name: "fieldInfo", readonly: true },
    { type: "text", name: "confirmedStatus", readonly: true }
];

const sheetColumns = [
    { type: "text", name: "qstTypeNm", readonly: true },
    { type: "text", name: "qstNm", readonly: true },
    { type: "text", name: "qstScore", readonly: true },
    { type: "text", name: "indNm", readonly: true },
    { type: "text", name: "indScore", readonly: true },
    { type: "text", name: "scoreCriteria", readonly: true },
    { type: "number", name: "resultScore" }
];

$(document).ready(function() {
    setParameters();
    initEventListeners();
    loadData();
});

// URL 파라미터 설정
function setParameters() {
    const params = TimsUtil.paramToMap(location.search);
    $('#ntcCd').val(params.ntcCd || '');
    $('#stepCd').val(params.stepCd || '');
}

// 이벤트 리스너 초기화
function initEventListeners() {
    // 돌아가기 버튼
    $(document).on('click', 'button[data-url]', function() {
        location.href = '${serviceBathPath}/' + $(this).data('url');
    });
    
    // 평가위원 선택 시 지원자 목록 로드 (자동 조회)
    $(document).on('change', '#usrCd', function() {
        if ($(this).val()) {
            loadApplicants($(this).val());
            clearEvalSheet();
        } else {
            clearApplicants();
            clearEvalSheet();
        }
    });
}

// 초기 데이터 로드
function loadData() {
    const params = {
        ntcCd: $('#ntcCd').val(),
        stepCd: $('#stepCd').val()
    };
    
    const obj = TimsUtil.getObject("${svnmSearch}", params, false);
    if (obj && obj.list.length > 0) {
        // 상단 패널 정보 설정
        const stepInfo = obj.list[0];
        $('#step').text(stepInfo.step);
        $('#ntcTypeNm').text(stepInfo.ntcTypeNm);
        
        // 평가위원 셀렉트 박스 로드
        loadJudgesSelectBox(obj.list);
    } else {
        alert("평가 정보를 불러올 수 없습니다.");
        location.href = '${serviceBathPath}/' + 'evl1100e';
    }
}

// 평가위원 셀렉트 박스 로드
function loadJudgesSelectBox(judges) {
    const $jdgSelect = $('#usrCd');
    $jdgSelect.empty();
    $jdgSelect.append('<option value="">선택</option>');
    
    if (!judges || !Array.isArray(judges)) return;
    
    judges.forEach(judge => {
        $jdgSelect.append(`<option value="\${judge.usrCd}" data-chair="\${judge.chairYn}">\${judge.org} \${judge.nm} (\${judge.jdgDivNm})</option>`);
    });
}

// 지원자 목록 로드
function loadApplicants(usrCd) {
    if (!usrCd) return;
    
    const params = { usrCd: usrCd };
    
    const obj = TimsUtil.getObject("${svnmGetApp}", params, false);
    if (!obj) {
        clearApplicants();
        return;
    }
    
    // 데이터 가공
    const formattedData = obj.list.map(app => {
        return {
            appCd: app.appCd,
            appUsrNm: app.appUsrNm,
            fieldInfo: `\${app.collegeNm} \${app.deptNm} \${app.majorNm} \${app.staffTypeNm}`,
            confirmedStatus: app.confirmedYn === 'Y' ? 'O' : 'X',
            resultCd: app.resultCd,
            staffType: app.staffType
        };
    });
    
    // 전역 변수에 데이터 저장 (나중에 참조)
    window.formattedAppData = formattedData;
    
    // 테이블 렌더링
    RctUtil.renderTable("app-tbody", applicantColumns, formattedData, null, null, "appCd");
    
    // 지원자 행 클릭 이벤트 추가
    $('#app-tbody tr').on('click', function() {
        const $tr = $(this);
        const appCd = $tr.data('id');
        if (!appCd) return;
        
        // 현재 행 선택 처리
        $tr.addClass('active').siblings().removeClass('active');
        $('#appCd').val(appCd);
        
        // 결과코드 데이터 저장
        const rowData = formattedData.find(item => item.appCd === appCd);
        if (rowData) {
            $('#resultCd').val(rowData.resultCd);
            $('#staffType').val(rowData.staffType);
        }
        
        // 평가표 로드
        loadEvalSheet(appCd);
        
        // 평가 상태에 따라 UI 상태 변경
        updateUiState($tr);
    });
}

// 지원자 목록 초기화
function clearApplicants() {
    $('#app-tbody').empty();
    $('#appCd').val('');
    $('#resultCd').val('');
}

    // 평가표 로드
function loadEvalSheet(appCd) {
    const usrCd = $('#usrCd').val();
    if (!usrCd || !appCd) return;
    
    // 소견서, 총점 영역 표시
    $('.opinion_area, .total_score').show();
    
    // 위원장 여부 확인
    const isChair = $('#usrCd option:selected').data('chair') === 'Y';
    if (isChair) {
        $('#minutes-area').show();
    } else {
        $('#minutes-area').hide();
    }
    
    const params = {
        usrCd: usrCd,
        appCd: appCd
    };
    
    const obj = TimsUtil.getObject("${svnmGetSheet}", params, false);
    if (!obj) {
        clearEvalSheet();
        return;
    }
    
    // 첫 번째 항목에서 시트 정보 추출
    if (obj.list && obj.list.length > 0) {
        const firstItem = obj.list[0];
        $('#sheetCd').val(firstItem.sheetCd);
        $('#opinion').val(firstItem.opinion);
        $('#minutes').val(firstItem.minutes);
    }
    
    // 평가표 점수 항목이 없는 경우 처리
    if (!obj.list || obj.list.length === 0) {
        $('#sheet-tbody').html('<tr><td colspan="7" class="text-center">평가할 항목이 없습니다.</td></tr>');
        return;
    }
    
    // 평가표 데이터 가공
    const formattedData = obj.list.map(qst => {
        return {
            qstCd: qst.qstCd,
            qstTypeNm: qst.qstTypeNm,
            qstNm: qst.qstNm,
            qstScore: qst.qstScore,
            indNm: qst.indNm,
            indScore: qst.indScore,
            scoreCriteria: qst.scoreCriteria,
            resultScore: qst.resultScore
        };
    });
    
    // 테이블 렌더링
    RctUtil.renderEditableTable("sheet-tbody", sheetColumns, formattedData, null, null, "qstCd");
    
    // 총점 계산
    calculateTotalScore();
    
    // 점수 입력 이벤트 추가
    $('#sheet-tbody input[name="resultScore"]').on('input', function() {
        // 숫자만 입력 가능
        $(this).val(TimsUtil.getNumberOnly($(this).val()));
        calculateTotalScore();
    });
    
    // UI 상태 업데이트 - 이미 확정된 경우
    const $selectedRow = $('#app-tbody tr.active');
    if ($selectedRow.length > 0) {
        const isConfirmed = $selectedRow.find('td:eq(2)').text() === 'O';
        // 확정 상태에 따라 버튼 토글
        if (isConfirmed) {
            $('#btn-confirm').hide();
            $('#btn-cancel-confirm').show();
        } else {
            $('#btn-confirm').show();
            $('#btn-cancel-confirm').hide();
        }
        updateUiState($selectedRow);
    }
}

// 평가표 초기화
function clearEvalSheet() {
    $('#sheet-tbody').empty();
    $('#opinion').val('');
    $('#minutes').val('');
    $('#sheetCd').val('');
    $('#staffType').val('');
    $('#total-score').text('0');
    
    // 소견서, 총점 영역 숨기기
    $('.opinion_area, .total_score').hide();
}

// 총점 계산
function calculateTotalScore() {
    let total = 0;
    $('#sheet-tbody input[name="resultScore"]').each(function() {
        const score = parseInt($(this).val()) || 0;
        total += score;
    });
    
    $('#total-score').text(total);
}

// UI 상태 업데이트 (확정 여부에 따라)
function updateUiState($selectedRow) {
    const isConfirmed = $selectedRow.find('td:eq(2)').text() === 'O';
    
    // 입력 필드 상태 조정
    $('#sheet-tbody input[name="resultScore"]').prop('disabled', isConfirmed);
    $('#opinion').prop('disabled', isConfirmed);
    $('#minutes').prop('disabled', isConfirmed);
}

// 평가 점수 저장 및 확정
function confirmResult() {
    const usrCd = $('#usrCd').val();
    const appCd = $('#appCd').val();
    const sheetCd = $('#sheetCd').val();
    const resultCd = $('#resultCd').val();
    const opinion = $('#opinion').val();
    const minutes = $('#minutes').val();
    
    // 신규 평가인지 여부
    const isNew = !resultCd;
    
    // 필수 조건 확인
    if (!validateEvaluation(usrCd, appCd, sheetCd, opinion)) return;
    
    if (!confirm("평가표를 확정하시겠습니까? 확정 후에는 수정할 수 없습니다.")) {
        return;
    }
    
    const data = {
        resSheet: {
            usrCd: usrCd,
            appCd: appCd,
            sheetCd: sheetCd,
            resultCd: resultCd,
            opinion: opinion,
            minutes: minutes
        },
        scores: collectScores()
    };
    
    const obj = TimsUtil.getObject("${svnmConfirmResult}", data, true);
    
    if (obj) {
		let message = "평가가 확정되었습니다.\n";
        
        if (obj.insertCnt > 0) message += "- 추가: " + obj.insertCnt + "건\n";
        if (obj.deleteCnt > 0) message += "- 삭제: " + obj.deleteCnt + "건\n";
        if (obj.updateCnt > 0) message += "- 수정: " + obj.updateCnt + "건";
        
        alert(message);
        
        // 평가 결과코드가 없거나 새로 생성된 경우 결과코드 갱신
        if (!resultCd || isNew) {
            const params = {
                usrCd: usrCd,
                appCd: appCd
            };
            
            const resultObj = TimsUtil.getObject("${svnmGetResultCd}", params, false);
            if (resultObj && resultObj.resultCd) {
                // 결과코드 업데이트
                $('#resultCd').val(resultObj.resultCd);
                
                // 지원자 목록 데이터도 업데이트
                const $selectedRow = $('#app-tbody tr.active');
                if ($selectedRow.length > 0) {
                    const rowIndex = $selectedRow.index();
                    const appCd = $selectedRow.data('id');
                    
                    // 해당 행의 데이터 참조 업데이트
                    if (appCd && window.formattedAppData && window.formattedAppData.length > rowIndex) {
                        window.formattedAppData[rowIndex].resultCd = resultObj.resultCd;
                    }
                }
            }
        }
        
        // 지원자 목록 업데이트 - 현재 선택된 행 확정 상태로 변경
        const $selectedRow = $('#app-tbody tr.active');
        $selectedRow.find('td:eq(2)').text('O');
        
        // UI 상태 업데이트 - 버튼 토글 (확정 상태로 변경)
        $('#btn-confirm').hide();
        $('#btn-cancel-confirm').show();
        updateUiState($selectedRow);
        
        // 다음 미평가 지원자로 이동 (있다면)
        moveToNextUnconfirmedApplicant($selectedRow);
    } else {
        alert("평가 확정 중 오류가 발생했습니다.");
    }
}

// 평가 점수 수집
function collectScores() {
    const scores = [];
    
    $('#sheet-tbody tr').each(function() {
        const qstCd = $(this).data('id');
        const resultScore = $(this).find('input[name="resultScore"]').val();
        
        if (qstCd) {
            scores.push({
                qstCd: qstCd,
                resultScore: resultScore || 0
            });
        }
    });
    
    return scores;
}

// 다음 미평가 지원자로 이동
function moveToNextUnconfirmedApplicant($currentRow) {
    // 현재 행 이후에 미평가 지원자 찾기
    let $nextRow = $currentRow.nextAll().filter(function() {
        return $(this).find('td:eq(2)').text() === 'X';
    }).first();
    
    // 없으면 현재 행 이전에서 미평가 지원자 찾기
    if ($nextRow.length === 0) {
        $nextRow = $currentRow.prevAll().filter(function() {
            return $(this).find('td:eq(2)').text() === 'X';
        }).first();
    }
    
    // 미평가 지원자가 있으면 클릭
    if ($nextRow.length > 0) {
        $nextRow.trigger('click');
    }
}

// 평가 유효성 검사
function validateEvaluation(usrCd, appCd, sheetCd, opinion) {
    if (!usrCd || !appCd || !sheetCd) {
        alert("평가할 지원자와 평가위원을 먼저 선택해주세요.");
        return false;
    }
    
    // 모든 점수가 입력되었는지 확인
    let allMinScore = true;
    let hasEmptyScore = false;
    
    $('#sheet-tbody input[name="resultScore"]').each(function() {
        const score = $(this).val();
        
        if (!score) {
            hasEmptyScore = true;
            return false; // break
        }
        
        if (parseInt(score) > 0) {
            allMinScore = false;
        }
    });
    
    if (hasEmptyScore) {
        alert("모든 평가 점수를 입력해주세요.");
        return false;
    }
    
    // 모든 점수가 최저점인데 소견서가 없는 경우
    if (allMinScore && !opinion.trim()) {
        alert("모든 평가 점수가 최저점인 경우, 소견서에 반드시 사유를 기재해야 합니다.");
        $('#opinion').focus();
        return false;
    }
    
    return true;
}

// 평가 확정 취소
function cancelConfirmResult() {
    const usrCd = $('#usrCd').val();
    const appCd = $('#appCd').val();
    const resultCd = $('#resultCd').val();
    const stepCd = $('#stepCd').val();
    
    if (!usrCd || !appCd) {
        alert("평가위원과 지원자를 선택해주세요.");
        return;
    }
    
    if (!resultCd) {
        alert("취소할 평가 결과가 없습니다. 평가 결과 정보를 확인해주세요.");
        return;
    }
    
    if (!confirm("평가 확정을 취소하시겠습니까?")) {
        return;
    }
    
    // 확정취소 요청 데이터
    const data = {
        usrCd: usrCd,
        appCd: appCd,
        resultCd: resultCd,
        stepCd: stepCd
    };
    
    const obj = TimsUtil.getObject("${svnmCancelConfirmResult}", data, true);
    
    if (obj && obj.updateCnt > 0) {
        alert("평가 확정이 취소되었습니다.");
        
        // 지원자 목록 업데이트 - 현재 선택된 행 미확정 상태로 변경
        const $selectedRow = $('#app-tbody tr.active');
        $selectedRow.find('td:eq(2)').text('X');
        
        // 버튼 토글 (미확정 상태로 변경)
        $('#btn-confirm').show();
        $('#btn-cancel-confirm').hide();
        
        // UI 상태 업데이트
        updateUiState($selectedRow);
    } else {
        alert("이미 점수 집계가 완료되어 확정 취소가 불가능합니다.");
    }
}
</script>