<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<form id="topform" name="topform" action="/servlet/rpm/main" method="post">
	<input type="hidden" name="bbsId" value="">
	<input type="hidden" name="div" value="">
	<input type="hidden" name="main_prof_div" value="">
	<div>
	<input type="hidden" name="_csrf" value="6079941c-e2cf-4985-a796-8dacc7e87499">
	</div>
</form>

<div class="inner">
	<h1 class="logo">
		<a href="javascript:goHome('');">
			<img src="/images/common/logo.png" height="56" alt="앳시스">
			<span>교원/직원 채용 시스템</span>
		</a>
	</h1>
	<nav id="Gnb" class="gnb-wrap">
		<ul class="gnb-list">
			<li>
				<a href="javascript:move_hire();">채용 공고</a>
			</li>
			<li>
				<a href="javascript:move_program_application();">지원서 작성</a>
			</li>
			<li>
				<a href="javascript:move_program_pass();">합격자 조회</a>
			</li>
			<li>
				<a href="#">평가위원</a>
				<ul class="sub">  
					<li><a href="javascript:move_eval();">기초,전공심사</a></li>
				</ul>
			</li>
			<li> 
				<a href="#">서비스 안내</a>
				<ul class="sub">
					<li><a href="javascript:move_program('BBSMSTR_000000000151');">공지사항</a></li>
					<li><a href="javascript:move_inquiry();">문의하기</a></li>	
					<li><a href="javascript:move_program('BBSMSTR_000000000153');">자료실</a></li>
					<li><a href="javascript:move_manual();">사용자매뉴얼</a></li>
					<li><a href="javascript:move_process();">채용절차</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	<a href="javascript:;" onclick="commonJs.gnbControl();" title="전체 메뉴 보기" class="btn-list btn-m-menu t-con-b">
		<span></span>
		<span></span>
		<span></span>
		<span></span>
	</a>
</div>