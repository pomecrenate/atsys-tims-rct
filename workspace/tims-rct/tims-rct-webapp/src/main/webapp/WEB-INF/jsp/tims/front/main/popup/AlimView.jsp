<%--
 프로그램명 : AlimView.jsp
 설명 : 알림보기
 작성자 : KYW
 일자 : 2023.10.16
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>

<c:set var="svnm" value="${serviceBathPath}"></c:set>

<div class="popup-iframe">
	<div class="pop-contents is-alert">
	<c:if test="${empty list}">
		<div class="no-data">최근 알림이 없습니다.</div>
	</c:if>
	<!-- loop -->
	<c:forEach items="${list}" var="item" varStatus="status">
		<div class="pop-data alert-waiting" style="display:block" id="${item.nteRcvCd}">
			<c:out value="${item.ntfDivCd}" />
			<c:out value="${item.ntfDivCdNm}" />
			<c:out value="${item.nteCont}" />
			<div class="btn-bottom">
				<c:out value="${item.sndDtm}" />
				<button class="btn-icon btn-delete" data-action="delAlim" data-div="${item.nteRcvCd}"><span class="hidden">정보 삭제</span></button>
			</div>
		</div>
	</c:forEach>
	<!-- // loop -->
		<button data-action="closePopup" class="btn-close" title="레이어 팝업 닫기"></button>
	</div>
</div>


<script>
$(document).ready(function(){
	
});

function delAlim(obj) {
	
	var val = obj.attr("data-div");
	var url = "${serviceBathPath}"+"/deleteAlim";
	var obj = {nteRcvCd : val};
	var ret = TimsUtil.getObject(url, obj, false);
	
	if ( ret != null ) {
		var rCd = ret.resultCd;
		
		if ( rCd == 'S01' ) {
			// 삭제성공
			var id = '#'+val;
			$(id).remove();
		} else {
			alert("알림 삭제에 실패하였습니다.");	
		}
	} else {
		alert("알림 삭제에 실패하였습니다.");
	}
}

</script>
