<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<c:choose>
        <c:when test="${sessionScope.userRole eq 'ADMIN'}">
             <jsp:include page="/WEB-INF/jsp/tims/common/layout/adm/head.jsp" />
        </c:when>
        <c:otherwise>
            <%--  <jsp:include page="/WEB-INF/jsp/tims/common/layout/front/head.jsp" /> --%>
             <jsp:include page="/WEB-INF/jsp/tims/common/layout/adm/head.jsp" />
        </c:otherwise>
    </c:choose> 
</head>
<body>
	<div class="main-content" style="flex: 1; padding: 20px;">
	   <jsp:include page="/WEB-INF/jsp/${body}.jsp" /> 
	</div>
</body>
</html>