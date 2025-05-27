<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1105e.jsp
	// 설명 : 결과관리
	// 작성자 : 이예찬
	// 일자 : 2025.05.23
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1105e"/>    
<c:set var="svnmSearch" value="${svnm}/search"/>    
<c:set var="svnmGetApplicants" value="${svnm}/getApplicants"/>  
<c:set var="svnmGetJudges" value="${svnm}/getJudges"/>
<c:set var="svnmUpdatePassStep" value="${svnm}/updatePassStep"/>
<c:set var="svnmResultView" value="${svnm}/popup/resultView"/>
 
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
					<dt><label>지원분야</label></dt>
					<dd><select class="form-control" id="fieldCd" name="fieldCd"></select></dd>
				</dl>
				<dl style="margin-bottom: 0;">
					<dt><label>모집인원</label></dt>
					<dd><div id="requiredCnt"></div></dd>
				</dl>
				<dl style="margin-bottom: 0;">
					<dt><label>적격인원</label></dt>
					<dd><div id="qualifiedCnt"></div></dd>
				</dl>
				<dl style="margin-bottom: 0;">
					<dt><label>부적격인원</label></dt>
					<dd><div id="unqualifiedCnt"></div></dd>
				</dl> 
				<button class="n_btn btn_md btn_c02" data-url="evl1100e" type="button" style="margin-left: auto;">돌아가기</button>
			</div>
		</div>
	</div> 
	
	<div class="x_content">
		<div class="description">※ 지원분야를 선택하면 해당 지원분야의 지원자 목록이 로드됩니다.</div>
		<div class="description">※ 지원자를 클릭하면 평가위원 목록이 로드됩니다.</div>
		<div class="description">※ 적격 버튼을 클릭하면 해당 지원자를 다음 단계로 진행시킵니다.</div>
		
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
								<th>평균</th>
								<th>최고,최저 제외 평균</th>
						<th>순위</th>
						<th>적격여부</th>
					</tr>
				</thead>
						<tbody id="app-tbody"></tbody>
					</table>
				</div>
			</div>
			
			<div class="col-md-7">
				<div class="list_top">
					<div class="lft-area">
						<p class="list_title">평가위원 목록</p>
					</div>
					<div class="rgt-area">
						<div class="btn-group">
							<button id="btn-qualify" data-action="qualify" type="button" class="n_btn btn_md btn_c02" style="display:none;">적격</button>
							<button id="btn-cancel-qualify" data-action="cancelQualify" type="button" class="n_btn btn_md btn_c04" style="display:none;">적격취소</button>
						</div>
					</div>
				</div>
				<div class="content_list">
					<input type="hidden" id="ntcCd" name="ntcCd" />
					<input type="hidden" id="stepCd" name="stepCd" />
					<input type="hidden" id="appCd" name="appCd" />
					<input type="hidden" id="staffType" name="staffType" />
					
					<table class="table" id="judges-table">
						<thead>
							<tr>
								<th>성명</th>
								<th>위원장여부</th>
								<th>내외부</th>
								<th>총점</th>
								<th>결과표 확인</th>
		            </tr> 
						</thead>
						<tbody id="judges-tbody"></tbody>
			</table> 
				</div>
			</div>
		</div>
	</div> 
</div>

<script>
// 테이블 컬럼 정의
const applicantColumns = [
    { type: "text", name: "nm" },
    { type: "text", name: "fieldInfo" },
    { type: "text", name: "totalAvg" },
    { type: "text", name: "exceptedAvg" },
    { type: "text", name: "rank" },
    { type: "text", name: "qualifiedStatus" }
];

const judgeColumns = [
    { type: "text", name: "nm" },
    { type: "text", name: "chairYn" },
    { type: "text", name: "jdgDivNm" },
    { type: "text", name: "totalScore" },
    { type: "button", name: "viewResult", btnText: "결과표", showCondition: function(row) {
        return row.resultCd != null && row.resultCd !== '';
    }, event: function(e) {
        const resultCd = this.closest('tr').getAttribute('data-result-cd');
        openResultViewPopup(resultCd);
        e.stopPropagation();
    }}
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
    
    // 지원분야 선택 시 지원자 목록 로드
    $(document).on('change', '#fieldCd', function() {
    	if ($(this).val()) {
        	loadApplicants($(this).val());
        	clearJudges();
    	} else {
    		clearApplicants();
    		clearJudges();
    	}
    });
    
    // 적격 버튼 클릭 이벤트
    $(document).on('click', '#btn-qualify', function() {
        qualifyApplicant();
    });
    
    // 적격취소 버튼 클릭 이벤트
    $(document).on('click', '#btn-cancel-qualify', function() {
        cancelQualifyApplicant();
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
        const info = obj.list[0];
        $('#step').text(info.step);
        $('#ntcTypeNm').text(info.ntcTypeNm);
        $('#requiredCnt').text(info.requiredCnt);
        $('#qualifiedCnt').text(info.qualifiedCnt);
        $('#unqualifiedCnt').text(info.unqualifiedCnt);
        
        // 지원분야 셀렉트 박스 로드
        loadFieldsSelectBox(obj.list);
    } else {
        alert("평가 정보를 불러올 수 없습니다.");
        location.href = '${serviceBathPath}/' + 'evl1100e';
    }
}

// 지원분야 셀렉트 박스 로드
function loadFieldsSelectBox(fields) {
    const $fieldSelect = $('#fieldCd');
    $fieldSelect.empty();
    $fieldSelect.append('<option value="">선택</option>');
    
    if (!fields || !Array.isArray(fields)) return;
    
    fields.forEach(field => {
		$fieldSelect.append(`<option value="\${field.fieldCd}" data-staff-type="\${field.staffType}">\${field.collegeNm} \${field.deptNm} \${field.majorNm} \${field.staffTypeNm}</option>`);
    });
}

// 지원자 목록 로드
function loadApplicants(fieldCd) {
    if (!fieldCd) return;
    
    const params = {
        stepCd: $('#stepCd').val(),
        fieldCd: fieldCd,
    	step: $('#step').text()
    };
    
    const obj = TimsUtil.getObject("${svnmGetApplicants}", params, false);
    if (!obj) {
        clearApplicants();
        return;
    }
    
    // 데이터 가공
    const formattedData = obj.list.map(app => {
        const fieldInfo = `\${app.collegeNm} \${app.deptNm} \${app.majorNm} \${app.staffTypeNm}`;
        const currentStep = $('#step').text();
        const nextStep = (parseInt(currentStep) + 1).toString();
        
        return {
            appCd: app.appCd,
            nm: app.nm,
            fieldInfo: fieldInfo,
            totalAvg: parseFloat(app.totalAvg).toFixed(2),
            exceptedAvg: parseFloat(app.exceptedAvg).toFixed(2),
            rank: app.rank,
            passStep: app.passStep,
            qualifiedStatus: app.passStep === nextStep ? 'O' : 'X'
        };
    });
    
    // 테이블 렌더링
    RctUtil.renderTable("app-tbody", applicantColumns, formattedData, applicantRowClickHandler, null, "appCd");
    
    // 데이터 속성 설정
    $('#app-tbody tr').each(function(index) {
        const row = formattedData[index];
        if (row && row.passStep) {
            $(this).attr('data-pass-step', row.passStep);
        }
    });
}

// 지원자 행 클릭 핸들러
function applicantRowClickHandler(event) {
    const $tr = $(event.currentTarget);
    const appCd = $tr.data('id');
    if (!appCd) return;
    
    // 현재 행 선택 처리
    $tr.addClass('active').siblings().removeClass('active');
    $('#appCd').val(appCd);
    
    // 평가위원 목록 로드
    loadJudges(appCd);
    
    // 적격 버튼 상태 업데이트
    updateQualifyButtonState($tr);
}

// 적격 버튼 상태 업데이트
function updateQualifyButtonState($tr) {
    const passStep = $tr.attr('data-pass-step');
    const currentStep = $('#step').text();
    const nextStep = (parseInt(currentStep) + 1).toString();
    
    toggleQualifyButtons(true);
    
    if (passStep === nextStep) {
        // 이미 적격 상태인 경우
        $('#btn-qualify').hide();
        $('#btn-cancel-qualify').show();
    } else {
        // 부적격 상태인 경우
        $('#btn-qualify').show();
        $('#btn-cancel-qualify').hide();
    }
}

// 적격 버튼 토글
function toggleQualifyButtons(show) {
    if (show) {
        $('#btn-qualify, #btn-cancel-qualify').not(':visible').show();
    } else {
        $('#btn-qualify, #btn-cancel-qualify').hide();
    }
}

// 적격 처리
function qualifyApplicant() {
    const appCd = $('#appCd').val();
    if (!appCd) {
        alert("지원자를 먼저 선택해주세요.");
        return;
    }
    
    const currentStep = $('#step').text();
    const nextStep = (parseInt(currentStep) + 1).toString();
    
    if (!confirm("해당 지원자를 적격 처리하시겠습니까?")) {
        return;
    }
    
    updatePassStep(appCd, nextStep);
}

// 적격 취소
function cancelQualifyApplicant() {
    const appCd = $('#appCd').val();
    if (!appCd) {
        alert("지원자를 먼저 선택해주세요.");
        return;
    }
    
    const currentStep = $('#step').text();
    
    if (!confirm("해당 지원자의 적격 상태를 취소하시겠습니까?")) {
        return;
    }
    
    updatePassStep(appCd, currentStep);
}

// 합격 단계 업데이트
function updatePassStep(appCd, newPassStep) {
    const params = {
        appCd: appCd,
        passStep: newPassStep
    };
    
    const obj = TimsUtil.getObject("${svnmUpdatePassStep}", params, true);
    
    if (obj && obj.updateCnt > 0) {
        alert("처리가 완료되었습니다.");
        
        const currentStep = $('#step').text();
        const nextStep = (parseInt(currentStep) + 1).toString();
        
        // 버튼 상태 변경
        if (newPassStep === nextStep) {
            $('#btn-qualify').hide();
            $('#btn-cancel-qualify').show();
        } else {
            $('#btn-qualify').show();
            $('#btn-cancel-qualify').hide();
        }
        
        // 선택된 행의 데이터 속성과 적격여부 텍스트 업데이트
        const $selectedRow = $('#app-tbody tr.active');
        $selectedRow.attr('data-pass-step', newPassStep);
        $selectedRow.find('td:eq(5)').text(newPassStep === nextStep ? 'O' : 'X');
        
        // 상단 적격/부적격 인원 수 갱신
        loadData();
        
        // 현재 지원분야 재로드
        const fieldCd = $('#fieldCd').val();
        if (fieldCd) {
            loadApplicants(fieldCd);
            
            // 선택 상태 복원
            setTimeout(function() {
                const $newRow = $(`#app-tbody tr[data-id="${appCd}"]`);
                if ($newRow.length > 0) {
                    $newRow.addClass('active');
                    updateQualifyButtonState($newRow);
                }
            }, 100);
        }
    } else {
        alert("처리 중 오류가 발생했습니다.");
    }
}

// 지원자 목록 초기화
function clearApplicants() {
    $('#app-tbody').empty();
    $('#appCd').val('');
    clearJudges();
    toggleQualifyButtons(false);
}

// 평가위원 목록 로드
function loadJudges(appCd) {
    if (!appCd) return;
    
    const params = {
        stepCd: $('#stepCd').val(),
        appCd: appCd
    };
    
    const obj = TimsUtil.getObject("${svnmGetJudges}", params, false);
    if (!obj) {
        clearJudges();
        return;
    }
    
    // 데이터 가공
    const formattedData = obj.list.map(judge => ({
    	usrCd: judge.usrCd,
        nm: judge.nm,
        chairYn: judge.chairYn === 'Y' ? 'O' : 'X',
        jdgDivNm: judge.jdgDivNm,
        totalScore: judge.totalScore,
        resultCd: judge.resultCd
    }));
    
    // 테이블 렌더링
    RctUtil.renderTable("judges-tbody", judgeColumns, formattedData, null, null, "usrCd");
    
    // 결과표 버튼 속성 추가
    $('#judges-tbody tr').each(function(index) {
        const row = formattedData[index];
        if (row && row.resultCd) {
            $(this).attr('data-result-cd', row.resultCd);
        }
    });
}

// 평가위원 목록 초기화
function clearJudges() {
    $('#judges-tbody').empty();
}

// 평가 결과표 팝업 열기
function openResultViewPopup(resultCd) {
    if (!resultCd) {
        alert("결과표 정보가 없습니다.");
        return;
    }
    
    // 전역 변수로 resultCd 저장 (팝업에서 접근할 수 있도록)
    window.selectedResultCd = resultCd;
    
    RctUtil.urlDialog('평가 결과표', '${svnmResultView}', 800, 600);
}
</script>