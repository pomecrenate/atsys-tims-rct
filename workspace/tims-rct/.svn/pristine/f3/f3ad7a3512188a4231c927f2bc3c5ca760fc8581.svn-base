<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<jsp:include page="/WEB-INF/jsp/tims/common/globalVariables.jsp" />

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/tims/common/layout/adm/head.jsp" />
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
			<jsp:include page="/WEB-INF/jsp/tims/common/layout/adm/adminSidebar.jsp" />
		</div>
		<div class="page-wrapper">
			<div class="page-header">
				<jsp:include page="adm/adminHeader.jsp" />
			</div>
			<div class="main-content">
				<jsp:include page="/WEB-INF/jsp/${body}.jsp" />
			</div>
		</div>
	</div>
</body>
</html>
