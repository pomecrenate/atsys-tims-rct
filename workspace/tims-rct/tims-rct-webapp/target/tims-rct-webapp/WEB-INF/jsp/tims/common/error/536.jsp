<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %> 

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
	<head>
		<title>소셜클래스</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="Description" content="사회적기업 교육 플랫폼">
		<meta name=”robots” content=”noindex”>
		
		<link rel="shortcut icon"  >
		<link rel="icon" type="image/png" sizes="16x16"  >
		<%--link rel="canonical" href="" />
		<link itemprop="url" href="" /--%>
		<meta name="format-detection" content="telephone=no">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.2, user-scalable=yes,target-densitydpi=device-dpi">
		<link rel="stylesheet" type="text/css" media="all">
	</head>
	
	<body>
		<!-- wrap -->
		<div id="Wrap" class="error">
			<div class="inner">
				<p class="icon"> </p>
				<p class="text">
					<em>등록되지 않은 사용자입니다</em>
					동일한 문제가 지속적으로 발생할 경우 <br class="m-br">관리자에게 문의해주시기 바랍니다.<br>TEL : 031-697-7700
				</p>
				<div class="btn-bottom">
					<%--a href="#" class="btn btn-white">이전 페이지로 이동</a--%>
					<a href="javascript:serviceCall();" class="btn btn-blue">홈으로 이동</a>
				</div>
			</div>
		</div>
		<!-- //wrap -->
		<script type="text/javascript" ></script>
		<script type="text/javascript" ></script>
	</body>
</html>

<script>
function serviceCall() {
	location.href = "/front";
// 	var url = location.href;
// 	if( url.indexOf("/front") >= 0 ){
// 		location.href = "/front";
// 	}else{
// 		location.href = "/service";
// 	}
}
</script>
