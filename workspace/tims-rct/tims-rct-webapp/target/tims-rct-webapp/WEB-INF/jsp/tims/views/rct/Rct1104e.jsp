<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Rct1104e.jsp
	// 설명 : 공고관리-지원방법
	// 작성자 : 최연재
	// 일자 : 2025.04.28
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/rct1100e"></c:set>   

 <div class="x_panel_wrap">	
	<div class="x_panel_title">전임교원 2025년도 2학기 채용공고ˑ공고관리</div> 	
	<div class="list_top">
		<div class="rgt-area">
			<button class="n_btn btn_md btn_c02" data-action="moveTab" data-url="/rct1100e" type="button">돌아가기</button>
		</div>
	</div>
	<div class="btn_tab">
		<ul>			
			<li><button data-action="moveTab" type="button" data-url="/rct1101e">초빙(임용)분야</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1102e">지원자격</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1103e">유의사항</button></li>
			<li class="active"><button data-action="moveTab" type="button" data-url="/rct1104e">지원방법</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1105e">제출서류</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1106e">전형절차</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1107e">기타및서식</button></li>
		</ul>			
	</div>	
	
	<div class="x_panel">
		<div class="x_content">
			<div class="content_list" id="content_list">
				
			</div>
		</div> 
	</div>

</div>


<script>  
$(document).on('click', 'button[data-url]', function() {
	const url = $(this).data('url');
	if (url) {
		location.href = '${serviceBathPath}' + url;
	}
}); 
</script>

