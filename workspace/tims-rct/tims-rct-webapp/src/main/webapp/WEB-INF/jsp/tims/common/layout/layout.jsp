<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:include page="/WEB-INF/jsp/tims/common/globalVariables.jsp" />

<!DOCTYPE html>
<html>
<head>
	<c:choose>
		<c:when test="${sessionScope.userRole eq 'ADMIN'}">
			<jsp:include page="/WEB-INF/jsp/tims/common/layout/adm/head.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/jsp/tims/common/layout/front/head.jsp" />
		</c:otherwise>
	</c:choose> 
</head>
<body>
	<div class="modal fade" id="timsdlg" tabindex="-1">
		<div class="modal-dialog" id="modaldlg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><i class="fas fa-times"></i></button>
					<h4 class="modal-title" id="dialogTitle"></h4>
				</div>
				<div class="modal-body" id="dialogBody">
				</div>
			</div>
		</div>
	</div>
	<div class="layout-container">
		<div class="page-sidebar">
			<c:choose>
				<c:when test="${sessionScope.userRole eq 'ADMIN'}">
					<jsp:include page="/WEB-INF/jsp/tims/common/layout/adm/adminSidebar.jsp" />
				</c:when>
			</c:choose>
		</div> 
		<div class="page-wrapper">
			<div class="page-header">
				<c:choose>
					<c:when test="${sessionScope.userRole eq 'ADMIN'}">
						<jsp:include page="adm/adminHeader.jsp" />
					</c:when>
					<c:otherwise>
						<jsp:include page="front/userHeader.jsp" />
					</c:otherwise>
				</c:choose>
			</div>
			<div class="main-content">
				<jsp:include page="/WEB-INF/jsp/${body}.jsp" />
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${sessionScope.userRole eq 'USER'}">
			<jsp:include page="/WEB-INF/jsp/tims/common/layout/footer.jsp" />
		</c:when>
	</c:choose>
</body> 
</html>
