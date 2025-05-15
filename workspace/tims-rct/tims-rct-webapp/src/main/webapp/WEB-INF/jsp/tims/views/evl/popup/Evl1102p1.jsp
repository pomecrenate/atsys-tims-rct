<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1102p1.jsp
	// 설명 : 심사표 불러오기(팝업)
	// 작성자 : 이예찬
	// 일자 : 2025.05.13
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>

<c:set var="svnm" value="${serviceBathPath}/evl1102e"/>
<c:set var="svnmSearchSheetList" value="${svnm}/searchSheetList"/>
<c:set var="svnmDeleteSheet" value="${svnm}/deleteSheet"/>

<div>
    <div class="list_top">
        <div class="lft-area">
        	<form method="POST" class="form-horizontal form-label-left" id="search" name="search" >
	            <div class="search_condition">
	                <div class="condition_list">
	                    <dl style="margin-bottom: 10px;">
	                        <dt><label for="qryNtcYear">채용연도</label></dt>
	                        <dd>
	                            <select class="form-control" id="qryNtcYear" name="qryNtcYear"></select>
	                        </dd>
	                    </dl>
	                    <dl style="margin-bottom: 10px;">
	                        <dt><label for="qryNtcSmt">채용학기</label></dt>
	                        <dd>
	                            <select class="form-control" id="qryNtcSmt" name="qryNtcSmt"></select>
	                        </dd>
	                    </dl>
	                    <dl style="margin-bottom: 10px;">
	                        <dt><label for="qryNtcType">채용구분</label></dt>
	                        <dd>
	                            <select class="form-control" id="qryNtcType" name="qryNtcType"></select>
	                        </dd>
	                    </dl>
	                    <dl style="margin-bottom: 10px;">
	                        <dt><label for="qryStep">차수</label></dt>
	                        <dd>
	                            <select class="form-control" id="qryStep" name="qryStep"></select>
	                        </dd>
	                    </dl>
	                </div>
	            </div>
            </form>
        </div>
        <div class="rgt-area btn_group">
            <button type="button" class="n_btn btn_md btn_c05" data-action="delete">
                <i class="fas fa-trash"></i> 삭제
            </button>
        </div>
    </div>

    <div class="description">
        ※ 불러올 심사표를 더블클릭하여 선택하세요.
    </div>

    <table class="table" id="sheet-list-table">
        <thead>
            <tr>
                <th><input type="checkbox"></th>
                <th>공고명</th>
                <th>채용구분</th>
                <th>교직원구분</th>
                <th>차수</th>
            </tr>
        </thead>
        <tbody id="sheet-list-tbody">
        </tbody>
    </table>
</div>

<script>
// 테이블 컬럼 정의
const sheetListColumns = [
    { type: "hidden", name: "stepCd" },
	{ type: "hidden", name: "staffType" },
    { type: "checkbox", name: "check" },
    { type: "text", name: "ntcNm" },
    { type: "text", name: "ntcTypeNm" },
    { type: "text", name: "staffTypeNm" },
    { type: "text", name: "step" }
];

$(document).ready(function() {
    // 필터 설정
    initFilters();
    
    // 이벤트 리스너 초기화
    initEventListeners();
    
    // 초기 데이터 로드
    loadSheetList();
});

// 필터 초기화
function initFilters() {
    setMultiSelect("", {cls: "CMM_1", id: "ccNtcYear", cdg : "RCT081"}, "search", "qryNtcYear", {type:"", selectAll:"Y", multiYn:"N"});
    setMultiSelect("", {cls: "CMM_1", id: "ccNtcSmt", cdg : "RCT001"}, "search", "qryNtcSmt", {type:"", selectAll:"Y", multiYn:"N"});
    setMultiSelect("", {cls: "CMM_1", id: "ccNtcType", cdg : "RCT002"}, "search", "qryNtcType", {type:"", selectAll:"Y", multiYn:"N"});
    setMultiSelect("", {cls: "CMM_1", id: "ccStep", cdg : "RCT006"}, "search", "qryStep", {type:"", selectAll:"Y", multiYn:"N"});
}

// 이벤트 리스너 초기화
function initEventListeners() {
    // 버튼 이벤트
    $(document).on("click", "button[data-action]", function() {
        const action = $(this).data('action');
        
        switch(action) {
            case 'delete':
                deleteSelectedSheets();
                break;
        }
    });

    // 행 더블클릭 시 선택 처리
    $(document).on("dblclick", "#sheet-list-tbody tr", function() {
    	const tr = $(this)
        const stepCd = tr.find('td:eq(0)').text();
        const staffType = tr.find('td:eq(1)').text();
        
		loadSelectedSheet(stepCd, staffType);
    });
    
    // 필터 변경 시 자동 검색
    $('#qryNtcYear, #qryNtcSmt, #qryNtcType, #qryStep').change(function() {
        loadSheetList();
    });
}

// 심사표 목록 로드
function loadSheetList() {
    const params = {
        ntcYear: $('#qryNtcYear').val(),
        ntcSmt: $('#qryNtcSmt').val(),
        ntcType: $('#qryNtcType').val(),
        step: $('#qryStep').val()
    };
    
    const obj = TimsUtil.getObject("${svnmSearchSheetList}", params, false);
    if (obj) {
        RctUtil.renderTable("sheet-list-tbody", sheetListColumns, obj.list || [], null, null, "sheetCd");
    }
}

// 선택한 심사표 로드
function loadSelectedSheet(stepCd, staffType) {
    const params = {
        stepCd: stepCd,
        staffType: staffType
    };
    
    const obj = TimsUtil.getObject("${svnm}/getSheet", params, false);
    
    if (obj) {
		TimsUtil.closeDialog();
    	handleSelectedSheet(obj);
    } else {
        alert("심사표 불러오기 중 오류가 발생했습니다.");
    }
}

// 선택한 심사표 삭제
function deleteSelectedSheets() {
    const $checkedRows = $('#sheet-list-table .row-checkbox:checked');
    
    if ($checkedRows.length === 0) {
        alert("삭제할 심사표를 선택해주세요.");
        return;
    }
    
    if (!confirm("선택한 심사표를 삭제하시겠습니까? 삭제된 데이터는 복구할 수 없습니다.")) return;
    
    const sheetCds = [];
    $checkedRows.each(function() {
        const sheetCd = $(this).closest('tr').data('id');
        if (sheetCd) sheetCds.push(sheetCd);
    });
    
    const obj = TimsUtil.getObject("${svnmDeleteSheet}", sheetCds, true);
    
    if (obj) {
        if (obj.deleteCnt > 0) {
            alert(obj.deleteCnt + "건의 심사표가 삭제되었습니다.");
            loadSheetList();
        } else {
            alert("평가 결과가 있어 삭제할 수 없는 심사표가 있습니다.");
        }
    } else {
        alert("삭제 중 오류가 발생했습니다.");
    }
}
</script>