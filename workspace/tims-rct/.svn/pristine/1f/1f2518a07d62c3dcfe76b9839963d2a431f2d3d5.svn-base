<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1104e.jsp
	// 설명 : 사정관리
	// 작성자 : 이예찬
	// 일자 : 2025.05.22
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1104e"/>    
<c:set var="svnmSearch" value="${svnm}/search"/>    
<c:set var="svnmGetApplicants" value="${svnm}/getApplicants"/>  
<c:set var="svnmGetJudges" value="${svnm}/getJudges"/>
<c:set var="svnmCalcScores" value="${svnm}/calcScores"/>
<c:set var="svnmCancelCalcScores" value="${svnm}/cancelCalcScores"/>

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
					<dt><label>교직원구분</label></dt>
					<dd>
						<div class="radio-group"></div>
					</dd>
				</dl> 
				<dl style="margin-bottom: 0;">
					<dt><label>평가기간</label></dt>
					<dd><div id="stepPeriod"></div></dd>
				</dl>
				<dl style="margin-bottom: 0;">
					<dt><label>평가발표일</label></dt>
					<dd><div id="stepAnnDate"></div></dd>
				</dl>
				<button class="n_btn btn_md btn_c02" data-url="evl1100e" type="button" style="margin-left: auto;">돌아가기</button>
			</div>
		</div>
	</div> 
	
	<div class="x_content">
		<div class="description">※ 교직원 구분 라디오 버튼을 클릭하면 해당 채용구분 및 교직원구분의 지원서 목록이 로드됩니다.</div>
		<div class="description">※ 지원서를 클릭하면 평가위원 목록이 로드됩니다.</div>
		<div class="description">※ 사정 버튼 클릭 시 평가위원 별 점수를 합한 지원서의 평균, 최고/최저 제외 평균, 순위가 계산됩니다.</div>
		
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
								<th>생년월일</th>
								<th>전화번호</th>
								<th>평균</th>
								<th>최고,최저 제외 평균</th>
								<th>순위</th>
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
							<button id="btn-calc" data-action="calcScores" type="button" class="n_btn btn_md btn_c02">사정</button>
							<button id="btn-cancel-calc" data-action="cancelCalcScores" type="button" class="n_btn btn_md btn_c04" style="display:none;">사정취소</button>
						</div>
					</div>
				</div>
				<div class="content_list">
					<input type="hidden" id="ntcCd" name="ntcCd" />
					<input type="hidden" id="stepCd" name="stepCd" />
					<input type="hidden" id="appCd" name="appCd" />
					<input type="hidden" id="staffType" name="staffType" />
					<input type="hidden" id="totalCd" name="totalCd" />
					
					<table class="table" id="judges-table">
						<thead>
							<tr>
								<th>평가위원 구분</th>
								<th>위원장여부</th>
								<th>평가위원명</th>
								<th>확정여부</th>
								<th>총점</th>
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
    { type: "text", name: "nm", readonly: true },
    { type: "text", name: "birthDate", readonly: true },
    { type: "text", name: "mobPhone", readonly: true },
    { type: "text", name: "totalAvg", readonly: true },
    { type: "text", name: "exceptedAvg", readonly: true },
    { type: "text", name: "rank", readonly: true }
];

const judgeColumns = [
    { type: "text", name: "jdgDivNm", readonly: true },
    { type: "text", name: "chairYn", readonly: true },
    { type: "text", name: "nm", readonly: true },
    { type: "text", name: "confirmedYn", readonly: true },
    { type: "text", name: "totalScore", readonly: true }
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
    
    // 교직원 구분 라디오 버튼 선택 시 이벤트
    $(document).on('change', 'input[name="staffType"]', function() {
        loadApplicants($(this).val());
        clearJudges();
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
        $('#stepPeriod').text(stepInfo.stepStartDate + ' ~ ' + stepInfo.stepEndDate);
        $('#stepAnnDate').text(stepInfo.stepAnnDate);
        
        // 교직원구분 라디오 버튼 생성
        generateStaffTypeRadios(obj.list);
    } else {
        alert("평가 정보를 불러올 수 없습니다.");
        location.href = '${serviceBathPath}/' + 'evl1100e';
    }
}

// 교직원구분 라디오 버튼 생성
function generateStaffTypeRadios(staffTypeList) {
    const $radioGroup = $('.radio-group');
    $radioGroup.empty();
    
    if (staffTypeList && staffTypeList.length > 0) {
        staffTypeList.forEach((item, index) => {
            const $label = $('<label>').addClass('radio-inline');
            const $radio = $('<input>')
                .attr('type', 'radio')
                .attr('name', 'staffType')
                .attr('value', item.staffType);
            
            if (index === 0) {
                $radio.prop('checked', true);
                // 첫 번째 교직원구분으로 지원자 목록 로드
                setTimeout(() => loadApplicants(item.staffType), 100);
            }
            
            $label.append($radio).append(' ' + item.staffTypeNm);
            $radioGroup.append($label);
        });
    }
}

// 지원자 목록 로드
function loadApplicants(staffType) {
    if (!staffType) return;
    
    $('#staffType').val(staffType);
    
    const params = {
        stepCd: $('#stepCd').val(),
        staffType: staffType
    };
    
    const obj = TimsUtil.getObject("${svnmGetApplicants}", params, false);
    if (!obj) {
        clearApplicants();
        return;
    }
    
    // 클라이언트 측에서 순위 계산
    calculateRanks(obj.list);
    
    // 테이블 렌더링
    RctUtil.renderTable("app-tbody", applicantColumns, obj.list, null, null, "appCd");
    
    // 지원자 행 클릭 이벤트 추가
    $('#app-tbody tr').on('click', function() {
        const $tr = $(this);
        const appCd = $tr.data('id');
        if (!appCd) return;
        
        // 현재 행 선택 처리
        $tr.addClass('active').siblings().removeClass('active');
        $('#appCd').val(appCd);
        
        // 평가위원 목록 로드
        loadJudges(appCd);
        
        // 총점 존재 여부에 따라 UI 상태 변경
        const totalAvg = $tr.find('td:eq(3)').text();
        const isCalculated = totalAvg && totalAvg !== "0";
        
        if (isCalculated) {
            $('#btn-calc').hide();
            $('#btn-cancel-calc').show();
        } else {
            $('#btn-calc').show();
            $('#btn-cancel-calc').hide();
        }
    });
}

// 클라이언트 측에서 순위 계산
function calculateRanks(applicants) {
    if (!applicants || applicants.length === 0) return;
    
    // 최고,최저 제외 평균 기준으로 정렬
    applicants.sort((a, b) => {
        const scoreA = parseInt(a.exceptedAvg) || 0;
        const scoreB = parseInt(b.exceptedAvg) || 0;
        
        if (scoreA === scoreB) {
            // 동점인 경우 이름 순
            return a.nm.localeCompare(b.nm);
        }
        
        return scoreB - scoreA; // 내림차순 정렬
    });
    
    // 순위 부여
    let currentRank = 1;
    let prevScore = -1;
    let sameRankCount = 0;
    
    applicants.forEach((applicant, index) => {
        const score = parseInt(applicant.exceptedAvg) || 0;
        
        // 이전 점수와 같다면 같은 순위 부여
        if (score === prevScore) {
            applicant.rank = currentRank;
            sameRankCount++;
        } else {
            // 이전과 다른 점수라면 새로운 순위 부여
            currentRank = index + 1;
            applicant.rank = currentRank;
            sameRankCount = 0;
        }
        
        prevScore = score;
    });
}

// 지원자 목록 초기화
function clearApplicants() {
    $('#app-tbody').empty();
    $('#appCd').val('');
    clearJudges();
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
    
    // 총점코드 저장
    if (obj.totalCd) {
        $('#totalCd').val(obj.totalCd);
    } else {
        $('#totalCd').val('');
    }
    
    // 테이블 렌더링
    RctUtil.renderTable("judges-tbody", judgeColumns, obj.list, null, null, "jdgCd");
}

// 평가위원 목록 초기화
function clearJudges() {
    $('#judges-tbody').empty();
    $('#totalCd').val('');
}

// 점수 사정
function calcScores() {
    const appCd = $('#appCd').val();
    const staffType = $('#staffType').val();
    
    if (!appCd) {
        alert("지원자를 먼저 선택해주세요.");
        return;
    }
    
    // 모든 평가위원이 확정했는지 확인
    const allConfirmed = $("#judges-tbody tr").toArray().every(row => {
        const confirmedYn = $(row).find("td:eq(3)").text().trim();
        return confirmedYn === 'Y';
    });
    
    if (!allConfirmed) {
        alert("모든 평가위원의 확정여부가 'Y'여야 사정이 가능합니다.");
        return;
    }
    
    if (!confirm("선택한 지원자의 점수를 사정하시겠습니까?")) {
        return;
    }
    
    const params = {
        stepCd: $('#stepCd').val(),
        appCd: appCd
    };
    
    const obj = TimsUtil.getObject("${svnmCalcScores}", params, true);
    
    if (obj) {
		let message = "점수 사정이 완료되었습니다.\n";
        
        if (obj.insertCnt > 0) message += "- 추가: " + obj.insertCnt + "건\n";
        if (obj.updateCnt > 0) message += "- 수정: " + obj.updateCnt + "건";
        
        alert(message);
        
        // 총점코드 저장
        if (obj.totalCd) {
            $('#totalCd').val(obj.totalCd);
        }
        
        // 버튼 상태 변경
        $('#btn-calc').hide();
        $('#btn-cancel-calc').show();
        
        // 지원자 목록 새로고침
        loadApplicants(staffType);
    } else {
        alert("점수 사정 중 오류가 발생했습니다.");
    }
}

// 점수 사정 취소
function cancelCalcScores() {
    const appCd = $('#appCd').val();
    const totalCd = $('#totalCd').val();
    const staffType = $('#staffType').val();
    
    if (!appCd || !totalCd) {
        alert("취소할 사정 결과가 없습니다.");
        return;
    }
    
    if (!confirm("사정 결과를 취소하시겠습니까?")) {
        return;
    }
    
    const params = {
        totalCd: totalCd
    };
    
    const obj = TimsUtil.getObject("${svnmCancelCalcScores}", params, true);
    
    if (obj && obj.deleteCnt > 0) {
        alert("사정 결과가 취소되었습니다.");
        
        // 총점코드 초기화
        $('#totalCd').val('');
        
        // 버튼 상태 변경
        $('#btn-calc').show();
        $('#btn-cancel-calc').hide();
        
        // 지원자 목록 새로고침
        loadApplicants(staffType);
    } else {
        alert("사정 결과 취소 중 오류가 발생했습니다.");
    }
}
</script>