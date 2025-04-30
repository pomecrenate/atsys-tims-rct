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
		<link rel="icon" type="image/png" sizes="16x16">
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
				<p class="icon"></p>
				<p class="text">
					<em>요청하신 페이지는 <br class="m-br">로그인이 필요한 페이지 입니다</em>
					아래의 '홈으로 이동'을 클릭하신 후 온락인학습 홈페이지 우측 상단의 '로그인'을 클릭하여 로그인 해주세요.
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
	var url = location.href;
	if( url.indexOf("/front") >= 0 ){
		location.href = "/front";
	}else{
		location.href = "/service";
	}
}
</script>