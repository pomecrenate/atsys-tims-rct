<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1100e.jsp
	// 설명 : 평가관리
	// 작성자 : 이예찬
	// 일자 : 2025.04.29
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1100e"/>  
<c:set var="svnmSearch" value="${svnm}/search"/>

<div class="x_panel_wrap"> 
	<div class="x_panel">		  
		<form method="POST" class="form-horizontal form-label-left" id="search" name="search" >
			<input type="hidden" id="ntcYear" name="ntcYear" />
			<input type="hidden" id="ntcSmt" name="ntcSmt" />
		
			<div class="search_condition">
				<div class="condition_list" style="display: flex; align-items: center; gap: 10px;">
					<dl style="margin-bottom: 0;">
						<dt><label for="qryNtcYear">채용연도</label></dt>
						<dd>
							<select class="form-control" id="qryNtcYear" name="qryNtcYear" required></select>
						</dd>
					</dl>
					<dl style="margin-bottom: 0;">
						<dt><label for="qryNtcSmt">채용학기</label></dt>
						<dd>
							<select class="form-control" id="qryNtcSmt" name="qryNtcSmt" required></select>
						</dd>
					</dl>
					<button data-action="search" type="button" class="n_btn btn_md btn_search" style="margin-left: auto;">
						<i class="fas fa-search"></i> 조회
					</button>
				</div>
			</div>
		</form>
	</div>
		
	<div class="x_content">
		<div class="description"> ※ 리스트를 클릭하시면 상세내역 확인 및 수정 가능합니다.</div>
		<div class="description"> ※ 상태단계(입력중 > 공고중 > 심사평가중 > 합격자발표)</div>
		<div class="content_list" id="content_list">
			<table class="table" id="eval-table">
				<thead>
					<tr>
						<th>공고상태</th>
						<th>공고명</th>
						<th>차수</th>
						<th>평가시작일</th>
						<th>평가종료일</th>
						<th>평가위원관리</th>
						<th>평가표관리</th>
						<th>심사관리</th>
						<th>사정관리</th>
						<th>결과관리</th>
					</tr>
				</thead>
				<tbody id="eval-tbody"> 
			    </tbody>
			</table> 
		</div>
	</div> 
	
	<div class="x_pagination"> 
	</div>
		
</div>

<script>
const btnUrlMap = {
	jdgMgtBtn: "evl1101e",
	shtMgtBtn: "evl1102e",
	evalMgtBtn: "evl1103e",
	inspMgtBtn: "evl1104e",
	resMgtBtn: "evl1105e"
};

const evalColumns = [
	{ type: "hidden", name: "stepCd" },
    { type: "text", name: "ntcStatusNm" },
    { type: "text", name: "ntcNm" },
    { type: "text", name: "step" },
    { type: "text", name: "stepStartDate" },
    { type: "text", name: "stepEndDate" },
    { type: "button", name: "jdgMgtBtn", btnText: "평가위원관리", btnUrl: btnUrlMap.jdgMgtBtn },
    { type: "button", name: "shtMgtBtn", btnText: "평가표관리", btnUrl: btnUrlMap.shtMgtBtn },
    { 
        type: "button", 
        name: "evalMgtBtn", 
        btnText: "심사관리", 
        btnUrl: btnUrlMap.evalMgtBtn,
/*         showCondition: function(row) {
            return row.ntcStatusNm === '심사평가중' || row.ntcStatusNm === '합격자발표';
        } */
    },
    { 
        type: "button", 
        name: "inspMgtBtn", 
        btnText: "사정관리", 
        btnUrl: btnUrlMap.inspMgtBtn,
        showCondition: function(row) {
            return row.ntcStatusNm === '심사평가중' || row.ntcStatusNm === '합격자발표';
        }
    },
    { 
        type: "button", 
        name: "resMgtBtn", 
        btnText: "결과관리", 
        btnUrl: btnUrlMap.resMgtBtn,
        showCondition: function(row) {
            return row.ntcStatusNm === '심사평가중' || row.ntcStatusNm === '합격자발표';
        }
    }
];

$(document).ready(function() { 
    setMultiSelect("", {cls: "NTC_1", id: "ccNtcYear", cdg : ""}, "search", "qryNtcYear", {type:"", selectAll:"Y", multiYn:"N"});
    setMultiSelect("", {cls: "CMM_1", id: "ccNtcSmt", cdg : "RCT001"}, "search", "qryNtcSmt", {type:"", selectAll:"Y", multiYn:"N"});
 
    // 연도 select 옵션 가공
    setTimeout(function() {
        const $select = $('#qryNtcYear');
        const yearSet = new Set();
        
        $select.find('option').each(function() {
            const text = $(this).text();
            const year = text.match(/\d{4}/)?.[0];
            if (year) {
                yearSet.add(year);
            }
        });
        
        $select.empty().append('<option value="">전체</option>');
        Array.from(yearSet).sort((a, b) => b - a).forEach(year => {
            $select.append(`<option value="\${year}">\${year}</option>`);
        });
        
        // multiselect 플러그인 재초기화
        $select.multiselect('rebuild');
    }, 100);
 
    // 초기 데이터 로드
    loadEvalList(1);
    
 	// 조회 버튼 클릭 이벤트
    $(document).on("click", "button[data-action='search']", function() {
        loadEvalList(1);
    });

    // 페이지네이션 클릭 이벤트
    $(document).on('click', '.x_pagination a', function(e) {
        e.preventDefault();
        const pageNo = $(this).data('page');
        loadEvalList(pageNo);
    });

	 // 버튼 클릭시 페이지 이동
	 $(document).on('click', 'button[data-url]', function() {
	     const url = $(this).data('url');
	     if (url) {
	         const tr = $(this).closest('tr');
	         const ntcCd = tr.attr('data-id');
	         const stepCd = tr.find('td:first').text();
	         
	         location.href = '${serviceBathPath}/' + url + '?ntcCd=' + ntcCd + '&stepCd=' + stepCd;
	     }
	 });
});

// 리스트 로드
function loadEvalList(pageNo) {
    const params = {
        qryNtcYear: $('#qryNtcYear').val(),
        qryNtcSmt: $('#qryNtcSmt').val(),
        pageIndex: pageNo || 1
    };

	RctUtil.loadPageData("${svnmSearch}", params, "ntcCd", "eval-tbody", evalColumns);
}
</script> 