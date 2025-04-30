<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Bas1200e.jsp
	// 설명 : 알림문구관리
	// 작성자 : 최연재
	// 일자 : 2025.04.17
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>

<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/bas1200e"/>
<c:set var="svnmAnnPop" value="${svnm}/popup/annPop"/>

<div class="x_panel_wrap"> 
	<div class="x_panel">
		<form method="POST" class="form-horizontal form-label-left" id="search" name="search" >
	<%-- 	<input type="hidden" name="cpCd"		id="cpCd"		value="<c:out value="${loginUser.cpCd}"/>" /> 
			<input type="hidden" name="cpAdminYn"	id="cpAdminYn"	value="<c:out value="${loginUser.cpAdminYn}"/>" />
			<input type="hidden" name="${loginUser.getCsrfTokenId()}" value="${loginUser.getCsrfToken()}"/> --%>
		
			<div class="search_condition">
				<div class="condition_list">
					<dl>
						<dt><label for="qryAnnTitle">알림 제목</label></dt>
						<dd><input class="form-control" id="qryAnnTitle" name="qryAnnTitle" required></input></dd>
					</dl>				
					<button data-action="search"  type="button" class="n_btn btn_md btn_search"><i class="fa fa-search"></i> 조회</button>
				</div>			
			</div>
		</form>
	</div>
	<div class="list_top">
			<div class="rgt-area">
				<div class="btn-group">
					<button data-action="annPopIns" type="button" class="n_btn btn_md btn_c01"><i class="fas fa-plus"></i> 신규</button>
					<button data-action="deleteAnn" type="button" class="n_btn btn_md btn_c05">삭제</button>
				</div>
			</div>
		</div>
	<div class="x_content">
		<div class="description"> ※ 리스트를 클릭하시면 알림문구를 상세히 확인할 수 있습니다.</div>
		<div class="content_list" id="content_list">
			<table class="table">
				<thead>
					<tr>
						<th>No</th>
						<th>알림 제목</th>
						<th>알림 내용</th>	
						<th>수정</th>
					</tr>
				</thead>
				<tbody id="ann-table"> 
		            <tr>
		                <td>1</td>
						<td>상태</td>
						<td>구분</td>
						<td><button class="n_btn btn_md btn_c03" data-action="annPopUpd" type="button">수정</button></td>
		            </tr> 
				</tbody>
			</table> 
		</div>
	</div> 
	</div>

<script>
$(document).on("click", "button[data-action]", function () {
	const action = $(this).data("action");
	if(action === "annPopIns") {
		var url = '${svnmAnnPop}'; 
		RctUtil.urlDialog('알림 문구 등록/수정', url, 800, 500);  
	} else if(action === "annPopUpd"){
		var url = '${svnmAnnPop}'; 
		RctUtil.urlDialog('알림 문구 등록/수정', url, 800, 500);  
	}
})
</script>