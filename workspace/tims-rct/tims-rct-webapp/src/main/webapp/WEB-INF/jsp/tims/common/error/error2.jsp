<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>소셜클래스</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="Description" content="사회적기업 교육 플랫폼">
	<meta name=”robots” content=”noindex”>
	
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/tiles/adm/css/font.css' />"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/tiles/adm/css/common.css' />"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/tiles/adm/css/contents.css' />"/>
</head>
<body>

<div id="wrap">
	<div class="error_wrap" data-status-code="${requestScope['javax.servlet.error.status_code']}">
		<div class="d_t">
			<div class="d_c">
				<p class="text01">알려드립니다.<br />'일시적 오류가 발생하였습니다.'</p>
				<p class="text02">방문하시려는 페이지의 주소가 잘못 입력되었거나, <br />페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br />혹은 시스템 장애가 발생되어 현재 사이트를 이용하실 수 없습니다.</p>		
				<a href="javascript:serviceCall();" class="btn_md btn_c02 btn_main">로그인화면으로</a>
			</div>
		</div>		
	</div>
</div>
<!-- // wrap -->
</body>
</html>

<script>
function serviceCall() {
	location.href = "/service/logout";
}
</script>