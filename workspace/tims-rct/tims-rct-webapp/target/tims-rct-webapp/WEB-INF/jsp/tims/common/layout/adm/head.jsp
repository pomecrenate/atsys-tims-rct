<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>

	<title>${programNm}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="title" content="대학채용시스템">
	
	<link rel="stylesheet" type="text/css" href="/css/service/style.css" media="all"> 
 	<link rel="stylesheet" type="text/css" href="<c:url value='/css/service/solid.css' />"/>
 	<link rel="stylesheet" type="text/css" href="<c:url value='/js/vendors/bootstrap-daterangepicker/daterangepicker.css' />"/>
 	<link rel="stylesheet" type="text/css" href="<c:url value='/js/vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css' />"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/plugins/bootstrap-treeview-master/src/css/bootstrap-treeview.css'/>">
	
	<script type="text/javascript">
		var sysLang = "<c:out value='${sysLang}'/>";
		var svnm = "<c:out value='${svnm}'/>";
		var svnmCodeColls = "<c:out value='${svnmCodeColls}'/>";
		var svnmFileUpload = "<c:out value='${svnmFileUpload}'/>";
		var svnmFileDownload = "<c:out value='${svnmFileDownload}'/>";
		var contextPath = "<c:out value='${contextPath}'/>";
		var serviceBathPath = "<c:out value='${serviceBathPath}'/>";
		var _PROGRAM_ID = "${programId}".toUpperCase();
		var loginYn = "<c:out value='${loginUser.isLogin()}'/>";
		var csrfTokenId = "<c:out value='${loginUser.getCsrfTokenId()}'/>";
		var csrfToken = "<c:out value='${loginUser.getCsrfToken()}'/>";
		var maxInactiveInterval = "<c:out value='${pageContext.session.maxInactiveInterval}'/>"; // 자동 로그아웃에서 사용
		var sessionExpiryPreTime = "<c:out value='${loginUser.sessionExpiryPreTime}'/>"; // 자동 로그아웃에서 사용
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="<c:url value='/js/vendors/moment/min/moment.min.js'/>"></script>
	
	<!-- jQuery -->
	<script src="<c:url value='/js/vendors/jquery/dist/jquery.min.js'/>"></script>
	<script src="<c:url value='/plugins/jquery/ui/jquery-ui-1.12.1/jquery-ui.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/plugins/jquery/jquery-cookie-master/src/jquery.cookie.js'/>"></script>
	
	<!-- $.toJSON 사용 -->
	<script type="text/javascript" src="<c:url value='/plugins/jquery/jquery-json-2.6.0/jquery.json.js'/>"></script>  
	
	<!-- jquery.inputmask -->
	<script src="<c:url value='/js/vendors/jquery.inputmask/dist/jquery.inputmask.bundle.js'/>"></script>
	
	<!-- Bootstrap -->
	<script src="<c:url value='/js/vendors/bootstrap/dist/js/bootstrap.min.js'/>"></script> 
	
	<!-- Bootstrap -->
	<link href="<c:url value='/js/vendors/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">	
	<link href="<c:url value='/js/vendors/jstree-bootstrap-theme-master/dist/themes/proton/style.css'/>" rel="stylesheet">
	<!-- Bootstrap-multi-select -->
	<link href="<c:url value='/js/vendors/jquery-multi-select-for-bootstrap/dist/css/bootstrap-multiselect.min.css'/>" rel="stylesheet"> 
	<!-- Bootstrap-multi-select -->
	<script src="<c:url value='/js/vendors/jquery-multi-select-for-bootstrap/dist/js/bootstrap-multiselect.min.js'/>"></script>
	
	<!-- bootstrap-daterangepicker -->
	<script type="text/javascript" src="<c:url value='/js/vendors/moment/min/moment-with-locales.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/vendors/bootstrap-daterangepicker/daterangepicker.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/vendors/jstree-bootstrap-theme-master/dist/jstree.min.js'/>"></script>
	
	<!-- bootstrap-datetimepicker -->
	<script type="text/javascript" src="<c:url value='/js/vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js'/>"></script>
	
	<!-- bootstrap-treeview -->
	<script type="text/javascript" src="<c:url value='/plugins/bootstrap-treeview-master/src/js/bootstrap-treeview.js'/>"></script>
	
	<script type="text/javascript" src="<c:url value='/js/tims/tims-util.js?v=202312221221'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/tims/rct-util.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/tims/tims-data-format.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/tims/tims-tree.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/tims/tims-code.js?v=20240618'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/tims/tims-file.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/js/tims/LMSFunction.js'/>"></script>
	
	<!-- pdf -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
