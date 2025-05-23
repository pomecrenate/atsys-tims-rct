<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : rcp1100e.jsp
	// 설명 : 지원서관리
	// 작성자 : 백세진
	// 일자 : 2025.04.09
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/rcp1100e"></c:set>   
<c:set var="svnmQualSelectPop" value="${svnm}/popup/qualynSelect"/>
<c:set var="svnmCareerRecogPop" value="${svnm}/popup/careerRecog"/>
<c:set var="svnmUpldFileListPop" value="${svnm}/popup/upldFileList"/>
<c:set var="svnmAppDetailPop" value="${svnm}/popup/appDetail"/>
<c:set var="svnmSearch" value="${svnm}/search" />

 <div class="x_panel_wrap"> 
	<div class="x_panel">		
		<form method="POST" class="form-horizontal form-label-left" id="search" name="search" >
			<input type="hidden" name="${loginUser.getCsrfTokenId()}" value="${loginUser.getCsrfToken()}"/>  
			<div class="search_condition">
				<button data-action="search" type="button" class="n_btn btn_md btn_search"><i class="fas fa-search"></i> 조회</button>
				<div class="condition_list">
					<dl>
						<dt><label for="qryNtcYear">채용연도</label></dt>
						<dd>
							<select class="form-control" id="qryNtcYear" name="qryNtcYear" required></select>
						</dd>
					</dl>
					<dl>
						<dt><label for="qryNtcSmt">채용학기</label></dt>
						<dd>
							<select class="form-control" id="qryNtcSmt" name="qryNtcSmt" required></select>
						</dd>
					</dl>
					<dl>
						<dt><label for="qryNtcCd">채용공고</label></dt>
						<dd>
							<select class="form-control" id="qryNtcCd" name="qryNtcCd" required></select>
						</dd>
					</dl>
					<dl>
						<dt><label for="qryStaffType">교직원구분</label></dt>
						<dd>
							<select class="form-control" id="qryStaffType" name="qryStaffType"></select>
						</dd>
					</dl>
					<dl>
						<dt><label for="qryCollegeCd">단과대학</label></dt>
						<dd><select class="form-control" id="qryCollegeCd" name="qryCollegeCd"></select></dd>
					</dl>
					<dl>
						<dt><label for="qryDeptCd">학과</label></dt>
						<dd><select class="form-control" id="qryDeptCd" name="qryDeptCd"></select></dd>
					</dl>
					<dl>
						<dt><label for="qryAppStatus">지원서상태</label></dt>
						<dd>
							<select class="form-control" id="qryAppStatus" name="qryAppStatus"></select>
						</dd>
					</dl>
				</div>
			</div>
		</form>
	</div>
	
	<div class="x_content">
		<div class="description"> ※ 리스트를 클릭하시면 지원서를 상세히 확인할 수 있습니다.</div>
		<div class="content_list" id="content_list">
			<table class="table" id="app-table">
				<thead>
					<tr> 
						<th>상태</th>
						<th>구분</th>
						<th>대학</th>
						<th>학과</th>
						<th>분야</th>
						<th>이름</th>
						<th>생년월일</th>
						<th>휴대폰</th>
						<th>제출일시</th>
						<th>자격충족여부</th>
						<th>경력산정(직원)</th>
						<th>첨부파일</th>
						<th>접수확인</th>
					</tr>
				</thead>
				<tbody id="app-tbody"> 
			    </tbody>
			</table> 
		</div>
	</div>  
	<div class="x_pagination"> 
	</div> 
</div>	

<script>   
const tbColNms = [
	  { type: "hidden", name: "staffType", dataField : "staffType" },
	  { type: "hidden", name: "qualYn", dataField : "qualYn" },
	  { type: "text", name: "appStatusNm", dataField : "appStatus" },
	  { type: "text", name: "staffTypeNm", dataField : "staffType" },
	  { type: "text", name: "collegeCd", dataField : "collegeCd" },
	  { type: "text", name: "deptCd", dataField : "deptCd" },
	  { type: "text", name: "majorNm", dataField : "majorNm" },
	  { type: "text", name: "nm", dataField : "nm" }, 
	  { type: "text", name: "birthDate", dataField : "birthDate" },
	  { type: "text", name: "mobPhone", dataField : "mobPhone" },
	  { type: "text", name: "subDate", dataField : "subDate" },
	  { type: "button", name: "qualSelectBtn", btnText: "자격충족여부",id: "qualSelectBtn", dataAction: "qualSelectPop" },
	  { type: "button", name: "careerRecogBtn", btnText: "경력산정", id: "careerRecogBtn", dataAction: "careerRecogPop" },
	  { type: "button", name: "upldFileListBtn", btnText: "첨부파일", dataAction: "upldFileListPop" },
	  { type: "button", name: "appStatusBtn", btnText: "확인" , id: "appStatusBtn", event : saveAppStatus } 
];

$(document).ready(function() { 
	setMultiSelect("", {cls: "CMM_1", id: "ccNtcYear", cdg : "RCT081"}, "search", "qryNtcYear", {type:"", selectAll:"Y", multiYn:"N"});
	setMultiSelect("", {cls: "CMM_1", id: "ccNtcSmt", cdg : "RCT001"}, "search", "qryNtcSmt", {type:"", selectAll:"Y", multiYn:"N"});
	setMultiSelect("", {cls: "NTC_1", id: "ccNtcCd", cdg : ""}, "search", "qryNtcCd", {type:"", selectAll:"Y", multiYn:"N"});
	setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT004"}, "search", "qryStaffType", {type:"", selectAll:"Y", multiYn:"N"});
	setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT005"}, "search", "qryStaffType", {type:"", selectAll:"Y", multiYn:"N"});
	setMultiSelect("", {cls: "FLD_1", id: "ccCollegeCd", cdg : ""}, "search", "qryCollegeCd", {type:"", selectAll:"Y", multiYn:"N"});
	setMultiSelect("", {cls: "FLD_2", id: "ccDeptCd", cdg : ""}, "search", "qryDeptCd", {type:"", selectAll:"Y", multiYn:"N"}); 
	setMultiSelect("", {cls: "CMM_1", id: "ccAppStatus", cdg : "RCT009"}, "search", "qryAppStatus", {type:"", selectAll:"Y", multiYn:"N"}); 

	RctUtil.loadPageData("${svnmSearch}", {}, "appCd", "app-tbody", tbColNms, function() { 
	    setAppStatusBtn();
	    setCareerRecogBtn();
	    setQualYnBtn();
	});
})  
    
 /* 팝업창 조회 */
 $(document).on("click", "button[data-action]", function () {
 	
 	const saveAppCd = $(this).closest('tr').data('id');
 	const action = $(this).data("action");
 	
 	switch (action) {
 		case "qualSelectPop" :
 			var url = '${svnmQualSelectPop}/' + saveAppCd; 
 			RctUtil.urlDialog('자격미달관리', url, 600, 300);
 			break;
 			
 		case "careerRecogPop" :
 			var url = '${svnmCareerRecogPop}/' + saveAppCd; 
 			RctUtil.urlDialog('경력산정(직원전용)', url, 900, 500);  
 			break;
 			
 		case "upldFileListPop" :
 			var url = '${svnmUpldFileListPop}/' + saveAppCd; 
 			RctUtil.urlDialog('첨부파일 조회', url, 800, 400);   
 			break;
 		} 
 })
    
/* 지원서상태 변경 */ 
function saveAppStatus(event) {
    const btn = event.target;
    const tr = btn.closest('tr');
    const qualYn = tr.querySelector("td[data-field='qualYn']").innerText.trim();
    const appCd = tr.dataset.id; 

    if (qualYn) { 
        if (btn.innerText === "확인") {
        	
        	var url = "${serviceBathPath}/rcp1100e/saveAppstatus";
        	var params = { 'appCd' : appCd, 'appStatus' : '3' }; 
        	
        	const result = TimsUtil.getObject(url, params, true); 
        	
        	if (result) {
        		alert("접수 확인처리되었습니다.");
        		 btn.innerText = "취소";
                 btn.classList.add("btn-cancel-mode");
        	}  
        	
        } else { 
        	var url = "${serviceBathPath}/rcp1100e/saveAppstatus";
        	var params = { 'appCd' : appCd, 'appStatus' : '2' }; 
        	
        	const result = TimsUtil.getObject(url, params, true); 
        	
        	if (result) {
        		alert("접수 확인이 취소되었습니다.");
        		btn.innerText = "확인";
                btn.classList.remove("btn-cancel-mode");
        	}  
        	
        }
    } else {
    	alert("자격미달여부가 등록되지 않았을 경우 접수확인처리 불가합니다. 자격미달여부를 먼저 등록해주세요 !");
    }
}

	
/* 리스트 클릭시 해당 지원서 조회 */ 
$(document).on("click", "#app-tbody tr[data-id]", function (e) {
    if ($(e.target).closest("button").length > 0) return;

    const appCd = $(this).data("id");
    const url = '${svnmAppDetailPop}/' + appCd;

    window.open(url, 'appDetailPopUp', 'width=840,height=600,resizable=no');
});
	
/* 지원서 조회 */
$(document).on("click", "button[data-action='search']", function() { 
	const params = TimsUtil.formToMap('search'); 
	RctUtil.loadPageData("${svnmSearch}", params, "appCd", "app-tbody", tbColNms, function() { 
	    setAppStatusBtn(); 
	}); 
})
	
/* 지원서 상태가 접수반려일 경우 접수확인 버튼 세팅 */
function setAppStatusBtn() {   
    $("td[data-field='appStatus']").each(function() {
        const appStatus = $(this).text().trim();
        const $tr = $(this).closest('tr'); 
        const $btn = $tr.find("#appStatusBtn"); 

        if (appStatus === '접수반려') { 
        	$btn.text("접수반려");
            $btn.prop("disabled", true); 
        } else if (appStatus === '접수확인') {
            $btn.text("취소");
            $btn.addClass("btn-cancel-mode");
        }
    });
} 

/* 교직원 구분이 교원일 경우 경력산정 버튼 비활성화 */
function setCareerRecogBtn() {
	$("td[data-field='staffType']").each(function() {
        const staffType = $(this).text().trim();
        const $tr = $(this).closest('tr'); 
        const $btn = $tr.find("#careerRecogBtn"); 

        if (staffType.startsWith("10") || staffType.startsWith("20")) {
            $btn.prop("disabled", true);
        } 
    });
}

/* 자격충족여부 버튼에 값 세팅 */
function setQualYnBtn() {
	 $("td[data-field='qualYn']").each(function() {
		 const qualYn = $(this).text().trim();
	     const $tr = $(this).closest('tr'); 
	     const $btn = $tr.find("#qualSelectBtn"); 
	     
	     if (qualYn === "Y") {
	            $btn.text("충족");
	            $btn.removeClass("btn-cancel-mode");
	        } else if (qualYn === "N") {
	            $btn.text("미충족");
	            $btn.addClass("btn-cancel-mode");
	        } else {
	            $btn.text("자격충족여부");  
	        }
	 });
}

</script>

