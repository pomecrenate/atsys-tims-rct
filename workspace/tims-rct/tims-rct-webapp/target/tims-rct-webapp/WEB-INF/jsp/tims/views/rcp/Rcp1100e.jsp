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

 <div class="x_panel_wrap">
	<div class="x_panel_title">지원서 관리</div> 
	<div class="x_panel">		
		<form method="POST" class="form-horizontal form-label-left" id="search" name="search" >
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
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>상태</th>
						<th>구분</th>
						<th>대학</th>
						<th>학과</th>
						<th>분야</th>
						<th>이름</th>
						<th>생년월일</th>
						<th>휴대폰</th>
						<th>제출일시</th>
						<th>자격미달여부</th>
						<th>경력산정(직원)</th>
						<th>첨부파일</th>
						<th>접수확인</th>
					</tr>
				</thead>
				<tbody> 
		            <tr>
		                <td><input type="checkbox" value=""></td>
						<td>상태</td>
						<td>구분</td>
						<td>대학</td>
						<td>학과</td>
						<td>분야</td>
						<td>이름</td>
						<td>생년월일</td>
						<td>휴대폰</td>
						<td>제출일시</td>
						<td><button class="n_btn btn_md btn_c02" data-action="qualSelectPop" type="button">자격미달관리</button></td>
						<td><button class="n_btn btn_md btn_c02" data-action="careerRecogPop" type="button">경력산정</button></td>
						<td><button class="n_btn btn_md btn_c02" data-action="upldFileListPop" type="button">첨부파일</button></td>
						<td><button class="n_btn btn_md btn_c02" id="appStatusBtn" onclick="saveAppStatus()">확인</button></td>
		            </tr> 
				</tbody>
			</table> 
		</div>
	</div> 
	
	<div class="x_pagination"> 
	</div>
</div>

<script>  
	$(document).ready(function() { 
		setMultiSelect("", {cls: "NTC_1", id: "ccNtcYear", cdg : ""}, "search", "qryNtcYear", {type:"", selectAll:"Y", multiYn:"N"});
		setMultiSelect("", {cls: "CMM_1", id: "ccNtcSmt", cdg : "RCT001"}, "search", "qryNtcSmt", {type:"", selectAll:"Y", multiYn:"N"});
		setMultiSelect("", {cls: "NTC_2", id: "ccNtcCd", cdg : ""}, "search", "qryNtcCd", {type:"", selectAll:"Y", multiYn:"N"});
		setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT004"}, "search", "qryStaffType", {type:"", selectAll:"Y", multiYn:"N"});
		setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT005"}, "search", "qryStaffType", {type:"", selectAll:"Y", multiYn:"N"});
/* 		setMultiSelect("", {cls: "FLD_2", id: "ccCollegeCd", cdg : ""}, "search", "qryCollegeCd", {type:"", selectAll:"Y", multiYn:"N"});
		setMultiSelect("", {cls: "FLD_3", id: "ccDeptCd", cdg : ""}, "search", "qryDeptCd", {type:"", selectAll:"Y", multiYn:"N"}); */
		setMultiSelect("", {cls: "CMM_1", id: "ccAppStatus", cdg : "RCT009"}, "search", "qryAppStatus", {type:"", selectAll:"Y", multiYn:"N"}); 
	})  
    
    /* 팝업창 조회 */
    $(document).on("click", "button[data-action]", function () {
    	const action = $(this).data("action");
    	
    	switch (action) {
    		case "qualSelectPop" :
	   			var url = '${svnmQualSelectPop}'; 
	   			TimsUtil.urlDialog('자격미달관리', url, 600, 300);  
	   			break;
	   			
    		case "careerRecogPop" :
    			var url = '${svnmCareerRecogPop}'; 
    			TimsUtil.urlDialog('경력산정(직원전용)', url, 800, 400);  
    			break;
    			
    		case "upldFileListPop" :
    			var url = '${svnmUpldFileListPop}'; 
    			TimsUtil.urlDialog('첨부파일 조회', url, 800, 400);   
    			break;
    		} 
    })
    
    /* 지원서상태 변경 */ 
	function saveAppStatus() {
	    const btn = document.getElementById("appStatusBtn");
	    
	    if (btn.innerText === "확인") {
	        btn.innerText = "취소";
	        btn.classList.add("btn-cancel-mode");
	    } else {
	        btn.innerText = "확인";
	        btn.classList.remove("btn-cancel-mode");
	    }
	}
	  

</script>

