<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
 
<!-- 	<div class="skip-nev">			
		<a href="#Gnb" class="hidden">주메뉴 바로가기</a>
		<a href="#Util" class="hidden">유틸메뉴 바로가기</a>
		<a href="#Container" class="hidden">본문 바로가기</a>
		<a href="#Footer" class="hidden">풋터 바로가기</a>
	</div> -->

<div id="Wrap">
	<main id="Container" class="container">
	
	<form id="frm" name="frm" action="/servlet/rpm/main" method="post">
		<input type="hidden" name="bbsId" value="" />
		<input type="hidden" name="nttId"  value="0" />
		<input type="hidden" name="bbsTyCode" value="" />
		<input type="hidden" name="bbsAttrbCode" value="" />
		<input type="hidden" name="authFlag" value="" />
		<input type="hidden" name="pageIndex"  value=""/>
		<input type="hidden" name="view_mode"  value="Y"/>
		<input type="hidden" name="detail_key"  value="" />
		<input type="hidden" name="detail_div"  value="" />
		<div>
		<input type="hidden" name="_csrf" value="d2730d91-a788-489b-b417-6fcc6e0be0f6" />
		</div>
	</form>

	<div class="main-visual">
		<div class="inner">
	   		<p class="text">교직원과 함께<br>더 큰 명지의 미래를 바라 봅니다.</p>
	     	<ul class="quick-recruit">
	      		<li><a href="javascript:goHome('10');">전임</a></li>
	           	<li><a href="javascript:goHome('20');">비전임</a></li>
	           	<li><a href="javascript:goHome('30');">강사</a></li>
	           	<li><a href="javascript:goHome('40');">일반직원</a></li>
	      	</ul>
	      	
	      	<!-- 231121_수정 및 추가 -->
			<!-- <div class="slide-wrap">
				<div id="recruitmentSwiper" class="recruitment-swiper">
					<div class="swiper-wrapper">
						
					</div>									
				</div>
				<div class="btn-wrap">
					<div class="recruitment-pagination"></div>
					<div class="btn-swiper-prev btn-slide recruitment-slide-prev" title="이전 슬라이드 보기"></div>							
					<button onclick="slideControl(this, recruitmentSwiper);" class="btn-slide btn-swiper-control" title="자동 슬라이드 중지"></button>							
					<div class="btn-swiper-next btn-slide recruitment-slide-next" title="다음 슬라이드 보기"></div>														
				</div>							
			</div> -->
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
					<li>	 
						<p class="title">
							<a href="javascript:f_notice_detail('BBSMSTR_000000000151',5,'','','','');">2024학년도 1학기 강사 및 비전임교원 합격자 제출 서류</a>
						</p>                																
						<p class="info">
							<span class="date">2024-01-31</span>										
						</p>
					</li> 
				</ul>							  
	        </div>
	        
	    	<div class="data02">
	       		<div>
	            	<h2 class="data-title">전임교원초빙</h2>
	           		<div class="btn-bottom">
						<a href="javascript:move_program_application('10');">지원서 작성</a>
	           			<a href="javascript:move_program_pass('10');">합격결과조회</a>
					</div>																
	          	</div> 
	          	<div>
	            	<h2 class="data-title">비전임교원 및<br>강사초빙</h2> 
					<div class="btn-bottom">
						<a href="javascript:move_program_application('20');">지원서 작성</a>
	            		<a href="javascript:move_program_pass('20');">합격결과조회</a>
					</div>								
	          	</div> 
	          	<div>
	            	<h2 class="data-title">일반직원</h2> 
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
  	</div>  