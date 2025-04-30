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
    <c:choose>
        <c:when test="${sessionScope.userRole eq 'ADMIN'}">
            <jsp:include page="adm/adminHeader.jsp" /> 
        </c:when>
        <c:otherwise>
            <jsp:include page="front/userHeader.jsp" />
        </c:otherwise>
    </c:choose>
    <div style="display: flex; min-height: 100vh;">
        <c:choose>
            <c:when test="${sessionScope.userRole eq 'ADMIN'}">
                <jsp:include page="/WEB-INF/jsp/tims/common/layout/adm/adminSidebar.jsp" />
            </c:when>
        </c:choose>

        <div class="main-content" style="flex: 1; padding: 20px;">
            <jsp:include page="/WEB-INF/jsp/${body}.jsp" />
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>

</html>
