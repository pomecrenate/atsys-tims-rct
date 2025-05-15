<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1101e.jsp
	// 설명 : 평가위원관리
	// 작성자 : 이예찬
	// 일자 : 2025.04.29
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1101e"/>    
<c:set var="svnmSearch" value="${svnm}/search"/>
<c:set var="svnmSearchFields" value="${svnm}/searchFields"/>    
<c:set var="svnmSearchJudges" value="${svnm}/searchJudges"/>    
<c:set var="svnmSaveJudges" value="${svnm}/saveJudges"/>    
<c:set var="svnmConfirmJudges" value="${svnm}/confirmJudges"/>
<c:set var="svnmCancelConfirmJudges" value="${svnm}/cancelConfirmJudges"/>    

<div class="x_panel_wrap">  
	<div class="x_panel">		
		<div class="search_condition"> 
			<div class="condition_list">
				<dl>
					<dt><label>채용공고 차수</label></dt>
					<dd><div id="step"></div></dd>
				</dl>
				<dl>
					<dt><label>채용구분</label></dt>
					<dd><div id="ntcType"></div></dd>
				</dl> 
				<dl>
					<dt><label>모집분야 수</label></dt>
					<dd><div id="totalFields"></div></dd>
				</dl>
				<dl>
					<dt><label>확정 분야 수</label></dt>
					<dd><div id="confirmedFields"></div></dd>
				</dl> 
				<dl>
					<dt><label>미확정 분야 수</label></dt>
					<dd><div id="unconfirmedFields"></div></dd>
				</dl> 
			</div>
			<button class="n_btn btn_md btn_c02" data-url="evl1100e" type="button">돌아가기</button>
		</div>
	</div> 
	
	<div class="x_content">
		<div class="description">※ 분야 별로 평가위원을 등록하고 반드시 [확정]하셔야 합니다.</div>
		<div class="description">※ 위원장은 반드시 지정하셔야 합니다.</div>
		<div class="description">※ 1차 평가 전에 평가위원 등록을 완료해주시기 바랍니다.</div>
		
		<div class="row">
			<div class="col-md-5">
				<div class="list_top">
					<div class="lft-area">
						<p class="list_title">모집분야</p>
					</div>
				</div>
				<div class="content_list">
					<table class="table" id="fields-table">
						<thead>
							<tr>
								<th>단과대</th>
								<th>학부(과)</th>
								<th>분야</th>
								<th>구분</th>
								<th>확정여부</th>
							</tr>
						</thead>
						<tbody id="fields-tbody"></tbody>
					</table> 
				</div>
			</div>
			
			<div class="col-md-7">
				<div class="list_top">
					<div class="lft-area">
						<p class="list_title">평가위원</p>
					</div> 
					<div class="rgt-area">
						<div class="btn-group">
							<button data-action="addJudge" type="button" class="n_btn btn_md btn_c01"><i class="fas fa-plus"></i> 추가</button>
							<button data-action="deleteJudge" type="button" class="n_btn btn_md btn_c05"><i class="fas fa-minus"></i> 삭제</button>
							<button data-action="saveJudges" type="button" class="n_btn btn_md btn_c03"><i class="fas fa-check"></i> 저장</button>
							<button data-action="confirmJudges" type="button" class="n_btn btn_md btn_c02">확정</button>
							<button data-action="cancelConfirmJudges" type="button" class="n_btn btn_md btn_c04">확정취소</button>
						</div>
					</div>
				</div>
				<div class="content_list">
					<input type="hidden" id="fieldCd" name="fieldCd" />
					<input type="hidden" id="ntcCd" name="ntcCd" />
					<input type="hidden" id="stepCd" name="stepCd" />
					<table class="table" id="judges-table">
						<thead>
							<tr>
								<th><input type="checkbox" /></th>
								<th>내외부</th>
								<th>위원장</th>
								<th>성명</th>
								<th>소속</th>
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
const fieldColumns = [
	{ type: "hidden", name: "fieldCd" },
    { type: "text", name: "collegeCd" },
    { type: "text", name: "deptCd" },
    { type: "text", name: "majorNm" },
    { type: "text", name: "staffTypeNm" },
    { type: "text", name: "confirmedYn" }
];

const judgeColumns = [
    { type: "checkbox", name: "check" },
    { type: "select", name: "jdgDiv", options: ["내부", "외부"] },
    { type: "radio", name: "chairYn", value: "Y" },
    { type: "select", name: "nm", options: [], onChange: "judgeSelected" },
    { type: "text", name: "org", readonly: true }
];

// 페이지 초기화
$(document).ready(function() {
    // URL 파라미터 설정
    setParameters();
    
    // 이벤트 리스너 등록
    initEventListeners();
    
    // 데이터 로드
    loadData();
});

// URL 파라미터 설정
function setParameters() {
    // TimsUtil.paramToMap을 사용하여 URL 파라미터 가져오기
    var params = TimsUtil.paramToMap(location.search);
    $('#ntcCd').val(params.ntcCd || '');
    $('#stepCd').val(params.stepCd || '');
}

// 이벤트 리스너 초기화
function initEventListeners() {
    // 돌아가기 버튼
    $(document).on('click', 'button[data-url]', function() {
        const url = $(this).data('url');
        if (url) {
            location.href = '${serviceBathPath}/' + url;
        }
    });
    
    // 평가위원 추가 버튼
    $(document).on("click", "button[data-action='addJudge']", function() {
        if (!$('#fieldCd').val()) {
            alert("모집분야를 먼저 선택해주세요.");
            return;
        }
        addJudgeRow();
    });
    
    // 평가위원 삭제 버튼
    $(document).on("click", "button[data-action='deleteJudge']", function() {
        RctUtil.deleteSelectedRows('#judges-table');
    });
    
    // 저장 버튼
    $(document).on("click", "button[data-action='saveJudges']", saveJudges);
    
    // 확정 버튼
    $(document).on("click", "button[data-action='confirmJudges']", confirmJudges);
    
    // 확정 취소 버튼
    $(document).on("click", "button[data-action='cancelConfirmJudges']", cancelConfirmJudges);
}

// 초기 데이터 로드
function loadData() {
    loadStepInfo();
    loadJudgeOptions();
}

// 차수 정보 로드
function loadStepInfo() {
    const params = {
        ntcCd: $('#ntcCd').val(),
        stepCd: $('#stepCd').val()
    };
    
    const obj = TimsUtil.getObject("${svnmSearch}", params, false);
    if (obj) {
        // 상단 패널 정보 설정
        $('#step').text(obj.info.step);
        $('#ntcType').text(obj.info.ntcTypeNm);
        $('#totalFields').text(obj.info.totalDepts);
        $('#confirmedFields').text(obj.info.confirmedFields);
        $('#unconfirmedFields').text(obj.info.unconfirmedFields);
        
        // 모집분야 목록 조회
        loadFields();
    }
}

// 모집분야 목록 로드
function loadFields() {
    const params = { 
        ntcCd: $('#ntcCd').val(),
        stepCd: $('#stepCd').val() 
    };
    
    const obj = TimsUtil.getObject("${svnmSearchFields}", params, false);
    if (obj) {
        RctUtil.renderTable("fields-tbody", fieldColumns, obj.list, (event) => {
            const clickedTr = event.currentTarget;
            const fieldCd = $(clickedTr).data('fieldCd');
            
            // 선택 스타일 및 현재 필드 설정
            $(clickedTr).addClass('selected').siblings().removeClass('selected');
            $('#fieldCd').val(fieldCd);
            
            // 평가위원 목록 조회
            loadJudges(fieldCd);
        }, null, "fieldCd");
        
        // 첫 번째 행 선택
        $('#fields-tbody tr:first').trigger('click');
    }
}

// 평가위원 목록 조회
function loadJudges(fieldCd) {
    if (!fieldCd) return;
    
    const params = {
        ntcCd: $('#ntcCd').val(),
        stepCd: $('#stepCd').val(),
        fieldCd: fieldCd
    };
    
    const obj = TimsUtil.getObject("${svnmSearchJudges}", params, false);
    if (obj) {
        RctUtil.renderTable("judges-tbody", judgeColumns, obj.list, null, null, "usrCd");
        
        // 평가위원이 없는 경우 빈 행 추가
        if (obj.list.length === 0) {
            addJudgeRow();
        }
    }
}

// 평가위원 목록 조회 (select box 옵션)
function loadJudgeOptions() {
    const response = TimsUtil.getObject("${serviceBathPath}/common/judgeList", {}, false);
    
    if (obj) {
        const judgeOptions = [''];
        window.judgeData = { '': { org: '', jdgDiv: '' } };
        
        obj.list.forEach(function(judge) {
            judgeOptions.push(judge.nm);
            window.judgeData[judge.nm] = {
                usrId: judge.usrId,
                org: judge.org,
                jdgDiv: judge.jdgDiv === '1' ? '내부' : '외부'
            };
        });
        
        // select box 옵션 업데이트
        judgeColumns.find(col => col.name === 'nm').options = judgeOptions;
    }
}

// 평가위원 행 추가
function addJudgeRow() {
    RctUtil.insertRow(judgeColumns, '#judges-table');
}

// 평가위원 선택 이벤트
function judgeSelected(event) {
    const select = event.currentTarget;
    const selectedValue = $(select).val();
    const row = $(select).closest('tr');
    
    if (selectedValue && window.judgeData && window.judgeData[selectedValue]) {
        const judgeInfo = window.judgeData[selectedValue];
        row.data('usrId', judgeInfo.usrId);
        row.find('td:eq(4) input').val(judgeInfo.org);
        row.find('td:eq(1) select').val(judgeInfo.jdgDiv);
    } else {
        row.removeData('usrId');
        row.find('td:eq(4) input').val('');
    }
}

// 테이블 데이터 수집 및 가공
function getJudgeDataForSave() {
    const judges = [];
    
    $('#judges-tbody tr').each(function() {
        const $tr = $(this);
        if ($tr.find('select[name="nm"]').val()) {
            const judge = {
                stepCd: $('#stepCd').val(),
                fieldCd: $('#fieldCd').val(),
                jdgDiv: $tr.find('select[name="jdgDiv"]').val() === '내부' ? '1' : '2',
                usrId: $tr.data('usrId'),
                nm: $tr.find('select[name="nm"]').val(),
                chairYn: $tr.find('input[name="chairYn"]:checked').length > 0 ? 'Y' : 'N',
                org: $tr.find('input[name="org"]').val()
            };
            judges.push(judge);
        }
    });
    
    return judges;
}

// 평가위원 저장
function saveJudges() {
    // 필수 조건 확인
    if (!validateJudges()) return;
    
    const judges = getJudgeDataForSave();
    
    const obj = TimsUtil.getObject("${svnmSaveJudges}", judges, true);
    
    if (obj) {
        let message = "평가위원 정보가 저장되었습니다.\n";
        
        if (obj.insertCnt > 0) {
            message += "- 추가: " + response.insertCnt + "건\n";
        }
        
        if (obj.deleteCnt > 0) {
            message += "- 삭제: " + response.deleteCnt + "건\n";
        }
        
        if (obj.updateCnt > 0) {
            message += "- 복구: " + response.updateCnt + "건";
        }
        
        alert(message);
        loadJudges($('#fieldCd').val());
    } else {
        alert("저장 중 오류가 발생했습니다.");
    }
}

// 평가위원 확정
function confirmJudges() {
    // 필수 조건 확인
    if (!validateJudges()) return;
    
    if (!confirm("평가위원을 확정하시겠습니까? 확정 후에는 수정이 제한됩니다.")) {
        return;
    }
    
    // 현재 필드의 평가위원 ID 목록 수집
    const usrCds = [];
    $('#judges-tbody tr').each(function() {
        const usrCd = $(this).data('usrCd');
        if (usrCd) {
            usrCds.push(usrCd);
        }
    });
    
    if (usrCds.length === 0) {
        alert("확정할 평가위원이 없습니다.");
        return;
    }
    
    const obj = TimsUtil.getObject("${svnmConfirmJudges}", usrCds, true);
    
    if (obj) {
        alert(obj.updateCnt + "건의 평가위원이 확정되었습니다.");
        loadJudges($('#fieldCd').val());
        loadFields(); // 필드 목록 새로고침 (확정 여부 갱신)
    } else {
        alert("확정 중 오류가 발생했습니다.");
    }
}

// 평가위원 확정 취소
function cancelConfirmJudges() {
    if (!confirm("평가위원 확정을 취소하시겠습니까?")) {
        return;
    }
    
    // 현재 필드의 평가위원 ID 목록 수집
    const usrCds = [];
    $('#judges-tbody tr').each(function() {
        const usrCd = $(this).data('usrCd');
        if (usrCd) {
            usrCds.push(usrCd);
        }
    });
    
    if (usrCds.length === 0) {
        alert("취소할 평가위원이 없습니다.");
        return;
    }
    
    const obj = TimsUtil.getObject("${svnmCancelConfirmJudges}", usrCds, true);
    
    if (obj) {
        if (obj.updateCnt > 0) {
            alert(obj.updateCnt + "건의 평가위원 확정이 취소되었습니다.");
            loadJudges($('#fieldCd').val());
            loadFields(); // 필드 목록 새로고침 (확정 여부 갱신)
        } else {
            alert("이미 평가가 시작되어 평가위원 확정을 취소할 수 없습니다.");
        }
    } else {
        alert("확정 취소 중 오류가 발생했습니다.");
    }
}

// 평가위원 유효성 검사
function validateJudges() {
    if (!$('#fieldCd').val()) {
        alert("모집분야를 먼저 선택해주세요.");
        return false;
    }
    
    // 평가위원이 있는지 확인
    const judgeCount = $('#judges-tbody tr').length;
    if (judgeCount === 0) {
        alert("평가위원을 먼저 등록해주세요.");
        return false;
    }
    
    // 위원장 지정 확인
    const chairChecked = $('#judges-tbody input[name="chairYn"]:checked').length;
    if (chairChecked === 0) {
        alert("위원장을 지정해주세요.");
        return false;
    }
    
    // 모든 평가위원 정보가 완전한지 확인
    let isValid = true;
    $('#judges-tbody tr').each(function() {
        const $tr = $(this);
        if (!$tr.data('usrId') || !$tr.find('select[name="nm"]').val()) {
            alert("모든 평가위원의 정보를 완성해주세요.");
            isValid = false;
            return false; // break
        }
    });
    
    return isValid;
}
</script>