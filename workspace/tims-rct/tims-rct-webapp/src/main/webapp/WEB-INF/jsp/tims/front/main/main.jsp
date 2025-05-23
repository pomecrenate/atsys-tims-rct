<%--
프로그램명 : main.jsp
설명 : 메인 페이지
작성자 : 백세진 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="main-visual">
	<div class="inner">
		<p class="text">교직원과 함께<br>더 큰 미래를 바라 봅니다.</p>
		<ul class="quick-recruit">
			<li><a href="javascript:goHome('10');">전임</a></li>
			<li><a href="javascript:goHome('20');">비전임</a></li>
			<li><a href="javascript:goHome('30');">강사</a></li>
			<li><a href="javascript:goHome('40');">일반직원</a></li>
		</ul>
		<div class="slide-wrap">
			<div id="recruitmentSwiper" class="recruitment-swiper swiper-container-initialized swiper-container-horizontal">
				<div class="swiper-wrapper">
					<div class="swiper-slide item-recruitment proceeding swiper-slide-active" style="width: 440px; margin-right: 40px;">
						<a href="javascript:f_detail('2025-20-10-001','10');">
							<span class="target btn btn-v-sm btn-t-blue">전임교원</span>
							<span class="info">
								<span>
									<span class="title">전임교원 2025년도 2학기 채용공고</span>
									<span class="college">미디어·휴먼라이프대학,스마트시스템공과대학,반도체·ICT대학,화학·생명과학대학,사회과학대학,경영대학,미래융합대학,스포츠·예술대학,건축대학,인공지능·소프트웨어융합대학,(일반)대학원,기록정보과학전문대학원,통합치료대학원</span>
								</span>
								<span class="d-day"><span><em>0</em>D-DAY</span></span>
							</span>
							<span class="period">접수기간 <span>2025-04-28 10:00 ~ 2025-05-07  17:00</span></span>
							<span class="status">
									<span>진행중</span>
								<span class="more"><span class="hidden">상세보기 아이콘</span></span>
							</span>
						</a>
					</div>
				</div>
				<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
			</div>
			<div class="btn-wrap">
				<div class="recruitment-pagination swiper-pagination-fraction"><span class="swiper-pagination-current">1</span> / <span class="swiper-pagination-total">1</span></div>
				<div class="btn-swiper-prev btn-slide recruitment-slide-prev swiper-button-disabled" title="이전 슬라이드 보기" tabindex="0" role="button" aria-label="Previous slide" aria-disabled="true"></div>
				<button onclick="slideControl(this, recruitmentSwiper);" class="btn-slide btn-swiper-control" title="자동 슬라이드 중지"></button>
				<div class="btn-swiper-next btn-slide recruitment-slide-next swiper-button-disabled" title="다음 슬라이드 보기" tabindex="0" role="button" aria-label="Next slide" aria-disabled="true"></div>
			</div>
		</div>
	</div>
</div>

<div id="Contents" class="contents">
	<div class="main-data">
		<div class="data01">
			<div class="data-title-wrap">
				<h2 class="data-title">공지사항</h2>
				<button class="btn-more" onclick="javascript:move_program('BBSMSTR_000000000151');" title="공지사항 더보기"><span class="hidden">공지사항 페이지로 이동</span></button>
			</div>
			<ul class="board-list">
				<li class="is-new">
					<p class="title">
						<a href="javascript:f_notice_detail('BBSMSTR_000000000151',14,'','','','');">2025-2학기 명지대학교 전임교원 초빙 공고</a>
						<span class="icon-new"><span class="hidden">새 글</span></span>
					</p>
					<p class="info">
						<span class="date">2025-04-28</span>
					</p>
				</li>
				<li>
					<p class="title">
						<a href="javascript:f_notice_detail('BBSMSTR_000000000151',13,'','','','');">2025.3.1자 일반직원 신규채용 1차 합격자 안내</a>
					</p>
					<p class="info">
						<span class="date">2025-02-10</span>
					</p>
				</li>
				<li>
					<p class="title">
						<a href="javascript:f_notice_detail('BBSMSTR_000000000151',12,'','','','');">2025-1학기 명지대학교 전임교원 초빙 공고</a>
					</p>
					<p class="info">
						<span class="date">2024-10-21</span>
					</p>
				</li>
				<li>
					<p class="title">
						<a href="javascript:f_notice_detail('BBSMSTR_000000000151',11,'','','','');">2024.9.1.자 일반직원 신규채용 1차 합격자(추가사항-필독)</a>
					</p>
					<p class="info">
						<span class="date">2024-08-13</span>
					</p>
				</li>
				<li>
					<p class="title">
						<a href="javascript:f_notice_detail('BBSMSTR_000000000151',10,'','','','');">2024.3.1자 일반직원 신규채용 1차 합격자 안내(추가사항-필독)</a>
					</p>
					<p class="info">
						<span class="date">2024-02-08</span>
					</p>
				</li>
			</ul>
		</div>
		<div class="data02">
			<div>
				<h2 class="data-title">전임교원초빙</h2>
				<img src="/images/content/main-data02-obj01.png" alt="전임교원초빙 아이콘" width="65">
				<div class="btn-bottom">
					<a href="javascript:move_program_application('10');">지원서 작성</a>
					<a href="javascript:move_program_pass('10');">합격결과조회</a>
				</div>
			</div>
			<div>
				<h2 class="data-title">비전임교원 및<br>강사초빙</h2>
				<img src="/images/content/main-data02-obj02.png" alt="비전임교원 및 강사초빙 아이콘" width="65">
				<div class="btn-bottom">
					<a href="javascript:move_program_application('20');">지원서 작성</a>
					<a href="javascript:move_program_pass('20');">합격결과조회</a>
				</div>
			</div>
			<div>
				<h2 class="data-title">일반직원</h2>
				<img src="/images/content/main-data02-obj03.png" alt="일반직원 아이콘" width="65">
				<div class="btn-bottom">
					<a href="javascript:move_program_application('40');">지원서 작성</a>
					<a href="javascript:move_program_pass('40');">합격결과조회</a>
				</div>
			</div>
		</div>
		<div class="data03">
			<div class="inner">
				<div class="data-title-wrap">
					<h2 class="data-title">채용 문의</h2>
					<button class="btn btn-v-sm btn-t-dark" onclick="javascript:f_admin_list();">학부 채용 담당자</button>
				</div>
				<div class="contact-list">
					<dl>
						<dt>전임교원 (교원인사팀)</dt>
						<dd>TEL 031-330-6021 <span>FAX 330-4008</span></dd>
					</dl>
					<dl>
						<dt>강사·비전임교원 (교원인사팀)</dt>
						<dd>TEL 031-330-6705 <span>FAX 330-4008</span></dd>
					</dl>
					<dl>
						<dt>일반직원 (총무인사팀)</dt>
						<dd>TEL 031-330-6048 <span>FAX 332-2459</span></dd>
					</dl>
				</div>
			</div>
		</div>
	</div>
</div>