<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>

<c:set var="svnmNtf" value="${serviceBathPath}/notification"/>
<c:set var="svnmSendMsgPop" value="${svnmNtf}/sendMsgPop"/>

<c:set var="svnmAvgSearch" value="${svnm}/avgSearch"></c:set>
<c:set var="svnmChartSearch" value="${svnm}/chartSearch"></c:set>
<c:set var="svnmNumberSearch" value="${svnm}/chartSearch2"></c:set>
<c:set var="svnmAvgToday" value="${svnm}/avgToday"></c:set>
<c:set var="svnmSearchYear" value="${svnm}/yearVisitNum"></c:set>
<c:set var="svnmAvgYear" value="${svnm}/avgYear"></c:set>
<c:set var="svnmSearchMonth" value="${svnm}/monthVisitNum"></c:set>
<c:set var="svnmAvgMonth" value="${svnm}/avgMonth"></c:set>
<c:set var="svnmSelectWeek" value="${svnm}/selectWeek"></c:set>
<c:set var="svnmAvgWeek" value="${svnm}/avgWeek"></c:set>
<c:set var="svnmBtnDate" value="${svnm}/btnDate"></c:set>
<%--<c:set var="svnmMenuFileZip" value="${svnm}/menurDownloadZip"></c:set>
<c:set var="svnmDateStEd" value="${svnm}/selectDateStEd"></c:set>
<c:set var="svnmSelectMonth" value="${svnm}/selectMonth"></c:set>
<c:set var="svnmSelectYear" value="${svnm}/selectYear"></c:set>
<c:set var="svnmWeekDivdeNum" value="${svnm}/weekDivdeNum"></c:set>--%>

	<div class="list_top">
		<div class="lft-area" style="margin-top:12px;">
			<p class="list_title">관리자 업무 현황</p>
		</div>
		<div class="rgt-area" style="font-size:18px; font-weight:700;">
 			<c:if test="${groupDiv eq '1'}"> <!-- 시스템관리자 -->
				<span style="padding-right:20px;" data-action="menurDown" data-div="${groupDiv}"><img src="/images/content/main_quick_icon04.png" alt="관리자메뉴얼">&nbsp;관리자메뉴얼</span>
			</c:if>
 			<c:if test="${groupDiv eq '2'}"> <!-- 인재교육개발팀 -->
				<span style="padding-right:20px;" data-action="menurDown" data-div="${groupDiv}"><img src="/images/content/main_quick_icon04.png" alt="관리자메뉴얼">&nbsp;인재교육개발팀메뉴얼</span>
			</c:if>
 			<c:if test="${groupDiv eq '3'}"> <!-- 교육운영기관 -->
				<span style="padding-right:20px;" data-action="menurDown" data-div="${groupDiv}"><img src="/images/content/main_quick_icon04.png" alt="운영기관메뉴얼">&nbsp;운영기관메뉴얼</span>
			</c:if>
	 			<c:if test="${groupDiv eq '4'}"> <!-- 교육유관부서 -->
				<span style="padding-right:20px;" data-action="menurDown" data-div="${groupDiv}"><img src="/images/content/main_quick_icon04.png" alt="교육유관부서메뉴얼">&nbsp;교육유관부서메뉴얼</span>
			</c:if>
			<span style="padding-right:10px;" data-action="menurDown" data-div="5"><img src="/images/content/main_quick_icon01.png" alt="사용자메뉴얼">&nbsp;사용자메뉴얼</span>
		</div>
	</div>
	<div class="n_chart">
		<div class="overall_status">
			<div class="float-wrap">
				<div class="lft_wrap">
					<div class="number_status">
						<div>
							<div class="x_panel">
								<p class="list_title">수강생 대기인원</p>
								<div>
									<div>
										<span>수강대기&nbsp;-&nbsp; 
											<span class="chart_info_wrap">
												<span>온라인 <c:out value="${reqVo.atndWaitOnCnt}"/>, 오프라인 <c:out value="${reqVo.atndWaitOffCnt}"/></span>
											</span>
										</span>
										<span>승인대기&nbsp;-&nbsp; 
											<span class="chart_info_wrap">
												<span>온라인 <c:out value="${reqVo.atndOnCntWait}"/>, 오프라인 <c:out value="${reqVo.atndOffCntWait}"/></span>
											</span>
										</span>
									</div>
									<div class="number" data-num="<c:out value="${reqVo.waitTotCnt}"/>" id="atndWaitDiv"></div>
								</div>
							</div>
						</div>
						<div>
							<div class="x_panel">
								<p class="list_title">강사 공개요청 건수
									<div class="rgt-area">
										<button type="button" class="n_btn btn_md btn_c04" onclick="menuMove(11);">공개요청</button>
									</div>
								</p>
								<div>
									<div></div>
									<div class="number" data-num="<c:out value="${reqVo.ttrReqCnt}"/>" id="ttrReqDiv"></div>
								</div>
							</div>
						</div>
						<div>
							<div class="x_panel">
								<p class="list_title">지역별교육과정 공개요청
									<div class="rgt-area">
										<button type="button" class="n_btn btn_md btn_c04" onclick="menuMove(12);">공개요청</button>
									</div>
								</p>
								<div>
									<div>
										<span>온라인 <c:out value="${reqVo.eduOnCnt}"/></span>
										<span>오프라인 <c:out value="${reqVo.eduOffCnt}"/></span>
										<span>온/오프라인 <c:out value="${reqVo.eduOnoffCnt}"/></span>
									</div>
									<div class="number" data-num="<c:out value="${reqVo.eduReqCnt}"/>" id="eduReqDiv" data-action="menuMove"></div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="lft_wrap">
						<div class="list_top">
							<div class="lft-area">
								<form id="searchForm" name="searchForm" method="POST">
									<select class="form-control form_inline_block fs14" id="qryPeriod" name="qryPeriod" style="background-color:#fff;" onchange="chart2PeriodChange(this.value);">
										<option value="1">일간</option>
										<option value="2">주간</option>
										<option value="3">월간</option>
										<option value="4">연간</option>
									</select>
								<div class="period_wrap">
									<div id="startDateToday" class="date_cell start_date"><input type="text" id="qryApplnStartDt" name="qryApplnStartDt" data-format="dfDateYmd" class="form-control" value="${qryApplnStartDt}"/></div>
									<div id="endDateToday" class="date_cell end_date"><input type="text" id="qryApplnEndDt" name="qryApplnEndDt" data-format="dfDateYmd" class="form-control" value="${qryApplnEndDt}"/></div>
									<div id="startDateWeek" class="date_cell start_date" style="width:118px;display:none;"><input type="text" id="qryApplnStartDt2" name="qryApplnStartDt2" data-format="dfDateYm" class="form-control" value="${qryApplnStartDt}"/></div>
									<div id="weekDiv1" class="date_cell start_date" style="width:100px;display:none;">
										<select class="form-control form_inline_block" id="qryWeek1" name="qryWeek1" style="background-color:#fff;">
											<option value="1">첫째 주</option>
											<option value="2">둘째 주</option>
											<option value="3">셋째 주</option>
											<option value="4">넷째 주</option>
											<option value="5">다섯째 주</option>
										</select>
									</div>
									<div id="endDateWeek" class="date_cell end_date" style="width:118px;display:none;"><input type="text" id="qryApplnEndDt2" name="qryApplnEndDt2" data-format="dfDateYm" class="form-control" value="${qryApplnEndDt}"/></div>
									<div id="weekDiv2" class="date_cell end_date" style="width:100px;display:none;">
										<select class="form-control form_inline_block" id="qryWeek2" name="qryWeek2" style="background-color:#fff;" >
											<option value="1">첫째 주</option>
											<option value="2">둘째 주</option>
											<option value="3">셋째 주</option>
											<option value="4">넷째 주</option>
											<option value="5">다섯째 주</option>
										</select>
									</div>
									
									<div id="startDateMonth" class="date_cell start_date" style="display:none;"><input type="text" id="qryApplnStartDt3" name="qryApplnStartDt3" data-format="dfDateYm" class="form-control" value="${qryApplnStartDt}"/></div>
									<div id="endDateMonth" class="date_cell end_date" style="display:none;"><input type="text" id="qryApplnEndDt3" name="qryApplnEndDt3" data-format="dfDateYm" class="form-control" value="${qryApplnEndDt}"/></div>
									<div id="startDateYear" class="date_cell start_date" style="display:none;"><input type="text" id="qryApplnStartDt4" name="qryApplnStartDt4" data-format="dfDateYy" class="form-control" value="${qryApplnStartDt}"/></div>
									<div id="endDateYear" class="date_cell end_date" style="display:none;"><input type="text" id="qryApplnEndDt4" name="qryApplnEndDt4" data-format="dfDateYy" class="form-control" value="${qryApplnEndDt}"/></div>
								</div>
								<div class="rgt-area">
									<div class="period_btn_wrap">
										<div class="btn_wrap">
											<input type="hidden" id="qryBtnVal" name="qryBtnVal" value=""/>
											<button type="button" id="btn1" class="n_btn btn_md btn_c08" value="1" onclick="chart2ShotButtonClick('1')">이번달</button>
											<button type="button" id="btn2" class="n_btn btn_md btn_c08" value="2" onclick="chart2ShotButtonClick('2')">지난달</button>
											<button type="button" id="btn3" class="n_btn btn_md btn_c08" value="3" onclick="chart2ShotButtonClick('3')">다음달</button> 
										</div>
										<button data-action="chart2Search" type="button" class="n_btn btn_md btn_search"><i class="fa fa-search" ></i> 조회</button>
									</div>
								</div>
								</form>
							</div>
						</div>
						<div class="number_status">
							<div>
								<div class="x_panel">
									<p class="list_title" id="avg_title">일간 평균 접속자 수</p>
									<div>
										<div></div>
										<div class="number" data-num="<c:out value="${avgTd.totalUsrNumber}"/>" id="totalUsrNumberDiv"></div>
									</div>
								</div>
							</div>
							<div>
								<div class="x_panel">
									<p class="list_title" id="sum_title">일간 전체 접속자 수</p>
									<div>
										<div></div>
										<div class="number" data-num="<c:out value="${avgTd.sumUsrNumber}"/>" id="sumUsrNumberDiv"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- x_panel 크기 조정으로 rgt_wrap에서 rgt_wrap2로 변경 -->
				<div class="rgt_wrap2">
					<div class="x_panel">
						<p class="dash_title">관리자 업무 바로가기</p>
						<ul class="work_menu">
							<li class="menu01"><a href="javascript:void(0);" onclick="menuMove(1);">과정관리</a></li>
							<li class="menu01"><a href="javascript:void(0);" onclick="menuMove(3);">차수관리</a></li>
							<li class="menu01"><a href="javascript:void(0);" onclick="menuMove(6);">지역별교육<br>과정관리</a></li>
							
							<li class="menu02"><a href="javascript:void(0);" onclick="menuMove(2);">강사관리</a></li>
							<li class="menu04"><a href="javascript:void(0);" onclick="menuMove(4);">수강신청관리</a></li>
							<li class="menu03"><a href="javascript:void(0);" onclick="menuMove(5);">수강현황관리</a></li>
							
							<li class="menu05"><a href="javascript:void(0);" onclick="menuMove(7);">콘텐츠관리</a></li>
							<li class="menu08"><a href="javascript:void(0);" onclick="menuMove(8);">1:1문의<span id="qnaCnt">${reqVo.qnaCnt}</span></a></li>
							<li class="menu09"><a href="javascript:void(0);" onclick="menuMove(9);">학습질문<span id="crsQnaCnt">${reqVo.crsQnaCnt}</span></a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- 접속자 수 챠트 -->
			<div>
				<div class="x_panel">
					<div class="list_top">
						<p class="list_title">접속자 수
						</p>
					</div>
					<div style="position:relative;width:100%;height:300px;">
						<canvas id="Chart02"></canvas>
					</div>
					<div class="list_top"></div>
				</div>
			</div>
			<div class="list_top">
				<div class="lft-area">
					<select class="form-control form_inline_block fs14" id="qryYears" name="qryYears" style="background-color:#fff;"></select>
					&nbsp;
					<p class="list_title">학습현황	</p>
				</div>
			</div>
			<div class="number_status">
				<div>
					<div class="x_panel">
						<p class="list_title"><span class="monYear"></span>년도 평균 접속자수/일
							<span class="chart_info_wrap">
								<a href="javascript:void(0);" class="btn_info">?</a>
								<span class="info">해당 연도에 홈페이지 로그인한 사용자 수(연간 중복 제외)</span>
							</span>
						</p>
						<div>
							<div><span id="exYearSpan">전년도 <c:out value="${acsMonVo.acsYesNCnt}"/></span><em id="acsYearEm"><c:out value="${acsMonVo.acsCnt}"/>&nbsp;&nbsp;&nbsp;<c:out value="${acsMonVo.acsAvgCnt}"/>%</em></div>
							<div class="number" data-num="<c:out value="${acsMonVo.acsToNCnt}"/>" id="acsToCntDiv"></div>
						</div>
					</div>
				</div>
				<div>
					<div class="x_panel">
						<p class="list_title"><span class="monYear"></span>년도 운영과정
							<div class="rgt-area">
								<button type="button" class="n_btn btn_md btn_c04" onclick="menuMove(10);">과정현황</button>
							</div>
						</p>
						<div>
							<div>
								<span id="crsOnSpan">온라인학습 <c:out value="${crsVo.crsOnCnt}"/></span>
								<span id="crsOffSpan">오프라인 <c:out value="${crsVo.crsOffCnt}"/></span>
							</div>
							<div class="number" data-num="<c:out value="${crsVo.crsTotCnt}"/>" id="crsTotDiv"></div>
						</div>
					</div>
				</div>
				<div>
					<div class="x_panel">
						<p class="list_title"><span class="monYear"></span>년도 지역별교육정보</p>
						<div>
							<div>
								<span id="eduInfoSpan">지역별교육과정 <c:out value="${eduVo.eduInfoCnt}"/></span>
								<span id="eduPstSpan">교육자료실 <c:out value="${eduVo.eduPstCnt}"/></span>
							</div>
							<div class="number" data-num="<c:out value="${eduVo.eduTotCnt}"/>" id="eduTotDiv"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="list_top">
				<div class="lft-area">
					<select class="form-control form_inline_block fs14" id="qryYear" name="qryYear" style="background-color:#fff;"></select>
					<select class="form-control form_inline_block fs14" id="qryMonth" name="qryMonth" style="background-color:#fff;"></select>
					&nbsp;
					<p class="list_title">수강현황</p>
				</div>
			</div>
			<div class="number_status">
				<div>
					<div class="x_panel">
						<div class="list_top">
							<p class="list_title"><span class="monSpan"></span>월 수강현황
								<span class="chart_info_wrap">
									<a href="javascript:void(0);" class="btn_info">?</a>
									<span class="info">월간 클래스 유형별 수료생/수강생 수 (클래스 수강생 학습기간 기준)</span>
								</span>
							</p>
								<div class="rgt-area">
								<p class="n_btn btn_md2 btn_c10">수료생</p>
								<p class="n_btn btn_md2 btn_c11">수강생</p>
							</div>
						</div>
						<div style="position:relative;width:100%;height:120px;">
							<canvas id="Chart01"></canvas>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="chartLoading" style="position:fixed;left:0;top:0;width:100%;height:100%;background:rgba(255, 255, 255, 0.3);display:none">
		<p style="text-align:center;position:absolute;top:73%;width:100%;margin-top:-20px;"><img src="<c:url value='/images/common/process.gif"'/>"></p>
	</div>
	
<script type="text/javascript" src="<c:url value='${gentelellaUrl}/vendors/chartjs/chart.js'/>"></script>
<script type="text/javascript" src="<c:url value='${gentelellaUrl}/vendors/chartjs/chartjs-plugin-datalabels@2.0.0'/>"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>--%>
<script type="text/javascript">

$(document).ready(function() {
	
	setCombo("", {cls: "YEAR_01", id: "ccQryYear", cdg : "YEAR_01"}, "F", "", "qryYear", "N", "N");
	setCombo("", {cls: "MONTH_01", id: "ccQryMonth", cdg : "COM530"}, "F", "", "qryMonth", "N", "N");
	setCombo("", {cls: "YEAR_01", id: "ccQryYears", cdg : "YEAR_01"}, "F", "", "qryYears", "N", "N");
	
	var qryYear = Number('${acsVo.acsYear}');
	var qryMonth = '${acsVo.acsMonth}';
	
	$('#qryYears').val(qryYear);  //연도별 학습현황
	$('#qryYear').val(qryYear);   //연도-월별 수강현황
	$('#qryMonth').val(qryMonth); //연도-월별 수강현황
	
	$('#qryWeekYearSt').val(qryYear);   //접속자수-주간 시작연도
	$('#qryWeekYearEd').val(qryYear);   //접속자수-주간 종료연도
	$('#qryWeekMonthSt').val(qryMonth); //접속자수-주간 시작주차
	$('#qryWeekMonthEd').val(qryMonth); //접속자수-주간 종료주차
	
	$('.monYear').text(qryYear);          //연도별 학습현황의 년도표시
	$('.monSpan').text(Number(qryMonth)); //연도-월별 수강현황의 월표시
	
 	<!-- 년도 change -->
	$("#qryYear").change(function(){
		avgCntSet(); 
	});
	$("#qryYears").change(function(){
		var value = $(this).val();
		$('.monYear').text(Number(value));
		avgYearsCntSet(); 
	});
	
 	<!-- 월 change -->
	$("#qryMonth").change(function(){
		avgCntSet(); 
		var value = $(this).val();
		$('.monSpan').text(Number(value));
	});
	
	//월평균 접속자수
	acsCntSet();
	
	//관리자 공지사항
	openPopup();
	
	//연도별 학습현황-당일 접속자수
	//todayNumCnt();
	
	//연도별 학습현황-평균 접속자수/일, 운영과정, 지역별교육정보
	numCnt();
	
	// 평균/전체 접속자 수
	chart2ShotButtonClick('1');
});


function numCnt(){
	$({ acsToNum: $('#acsToCntDiv').text(),
		crsTotNum: $('#crsTotDiv').text(),
		eduTotNum: $('#eduTotDiv').text(),
		ttrReqNum: $('#ttrReqDiv').text(),
		eduReqNum: $('#eduReqDiv').text(),
		atndWaitNum: $('#atndWaitDiv').text()
	  }).animate({
		acsToNum: $('#acsToCntDiv').attr('data-num'),
		crsTotNum: $('#crsTotDiv').attr('data-num'),
		eduTotNum: $('#eduTotDiv').attr('data-num'),
		ttrReqNum: $('#ttrReqDiv').attr('data-num'),
		eduReqNum: $('#eduReqDiv').attr('data-num'),
		atndWaitNum: $('#atndWaitDiv').attr('data-num')
		},
		{
 			duration: 1000, // 애니메이션이 완료될때까지 걸리는 시간
			easing:'linear', // 애니메이션 효과 방식
			step: function() {
			$('#acsToCntDiv').text(Math.floor(this.acsToNum));
			$('#crsTotDiv').text(Math.floor(this.crsTotNum));
			$('#eduTotDiv').text(Math.floor(this.eduTotNum));
			$('#ttrReqDiv').text(Math.floor(this.ttrReqNum));
			$('#eduReqDiv').text(Math.floor(this.eduReqNum));
			$('#atndWaitDiv').text(Math.floor(this.atndWaitNum));
		},
			complete: function() {
			$('#acsToCntDiv').text(this.acsToNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
			$('#crsTotDiv').text(this.crsTotNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
			$('#eduTotDiv').text(this.eduTotNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
			$('#ttrReqDiv').text(this.ttrReqNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
			$('#eduReqDiv').text(this.eduReqNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
			$('#atndWaitDiv').text(this.atndWaitNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
		}
	});
}

// 월간 클래스 수강현황
const ctx01 = document.getElementById('Chart01').getContext('2d');
const mChart01 = new Chart(ctx01, {
	type: 'bar',
	data: {
		labels: ['전체 학습자', '온라인 학습자', '오프라인 학습자',],
		datasets: [
			{
			   label : '수료생',
			   data: ['${atndVo.atndCmptAllCnt}', '${atndVo.atndCmptOnlineCnt}', '${atndVo.atndCmptOfflineCnt}'],
			   backgroundColor: '#c2e41b',
			   label : '수료생',
			   data: ['${atndVo.atndCmptAllCnt}', '${atndVo.atndCmptOnlineCnt}', '${atndVo.atndCmptOfflineCnt}'],
			   backgroundColor: '#c2e41b',
			},
			{
			   label : '수강생',
			   data: ['${atndVo.atndAllCnt}', '${atndVo.atndOnlineCnt}', '${atndVo.atndOfflineCnt}','11'],
			   backgroundColor: '#59b1e0',
			},
		]
	},
	options: {
		responsive:true,
		maintainAspectRatio:false,
		scales: {
			y: {
				beginAtZero: true,
			}
		},
		plugins: {
			legend: {
				display: false,
			},
			tooltip: {
				enabled: true
			},
			datalabels: {
				color: 'white',
				align: 'center',
				anchor: 'center',
				font: {
					weight: 'bold'
				},
				formatter: Math.round,
				display : function(ctx01){
					return ctx01.dataset.data[ctx01.dataIndex] > 0;
				}
			}
		},
		hover: {
			animationDuration: 0
		},
//		events: []
	},
	plugins: [ChartDataLabels]
});


<!-- 당일 접속자수, 월평균 접속자수 현황 셋팅(화살표) -->
function acsCntSet() {
	var ansDiv = '${acsMonVo.acsDiv}';
	if(ansDiv == 'DOWN'){
		$("#acsYearEm").attr('class','down');
	}else if(ansDiv == 'UP'){
		$("#acsYearEm").attr('class','up');
	}else{
		$("#acsYearEm").attr('class','same');
	}
}

<!-- 년월 change시 값 세팅 -->
function avgCntSet() {
	var param = {qryYearMonth : $("#qryYear").val()+$("#qryMonth").val()};
	mainAjax("${svnmChartSearch}", param);
}

<!-- 년 change시 값 세팅 -->
function avgYearsCntSet() {
	var param = {qryYears : $("#qryYears").val()};
	var ret = TimsUtil.getObject("${svnmAvgSearch}", param, true);
	
	if(ret){
		<!-- 월 평균 접속자수/일 -->
		$("#exYearSpan").text("전년도 " + ret.acsMonVo.acsYesNCnt);
		$("#acsYearEm").text(ret.acsMonVo.acsCnt+"\u00a0\u00a0\u00a0"+ret.acsMonVo.acsAvgCnt+'%');
		if(ret.acsMonVo.acsDiv == 'DOWN'){
			$("#acsYearEm").attr('class','down');
		}else if(ret.acsMonVo.acsDiv == 'UP'){
			$("#acsYearEm").attr('class','up');
		}else{
			$("#acsYearEm").attr('class','same');
		}
		$('#acsToCntDiv').attr("data-num", ret.acsMonVo.acsToNCnt);
		
		<!-- 월 운영과정 -->
		$("#crsOnSpan").text("온라인 " + ret.crsVo.crsOnCnt);
		$("#crsOffSpan").text("오프라인 " + ret.crsVo.crsOffCnt);
		$('#crsTotDiv').attr("data-num", ret.crsVo.crsTotCnt);
		
		<!-- 월 교욱정보-->
		$("#eduInfoSpan").text("교육정보 " + ret.eduVo.eduInfoCnt);
		$("#eduPstSpan").text("교육자료실 " + ret.eduVo.eduPstCnt);
		$('#eduTotDiv').attr("data-num", ret.eduVo.eduTotCnt);
		
		<!-- 듀레이션 실행 -->
		numCnt();
	}
}

//비동기식으로 처리해야 로딩바 및 차트값 불러올수 있어서 mainAjax 따로 구현 (url, 넘길값)
function mainAjax(url, param){
	$.ajax({
		url : url,
		dataType : 'json',
		data: TimsUtil.toJSON(param),
		type: 'POST',
		cache : false,
		contentType: 'application/json; charset=utf-8',
		async: true, // false : 동기식, true : 비동기식
		beforeSend: function() {
			chartLoadingStart(); //로딩바 열기
		},
		success : function(ret){
			<!-- 그래프차트 셋팅 -->
			graphSet(ret);
		},
		complete: function() {
			chartLoadingEnd();//로딩바 닫기
		}
	});
}

<!-- 그래프차트 셋팅 -->
function graphSet(data){
	var v_cmptAtndArr = [data.atndVo.atndCmptAllCnt, data.atndVo.atndCmptOnlineCnt, data.atndVo.atndCmptOfflineCnt];
	var v_atndArr = [data.atndVo.atndAllCnt, data.atndVo.atndOnlineCnt, data.atndVo.atndOfflineCnt];
	mChart01.data.datasets[0].data = v_cmptAtndArr;
	mChart01.data.datasets[1].data = v_atndArr;
	mChart01.update();
}

<!-- 팝업 오픈 (운영관리-전시관리-팝업관리) -->
function openPopup() {
	<c:forEach var="list" items="${popList}" varStatus="status">
		var styleNum = "style3";
		var param = {
			title : '${list.popNm}',
			contents : '${fn:replace(list.popCnts, "\'", "\\\'")}',
			templateType : styleNum,
			width : '${list.popWidth}',
			height : '${list.popHgt}',
			top : '${list.popXLoc}',
			left : '${list.popYLoc}',
			target : '${list.popCd}',
		};
		
		var popCookie = TimsUtil.getCookie(param.target);
		if(popCookie != 'Y') {
			var popCookieYn = true;
			TimsUtil.showNoticeLayerPopup(param,popCookieYn);
		}
	</c:forEach>
}

<!-- 메뉴 새창 이동 -->
function menuMove(temp){
	var url = '';
	var winName = '';
	if(temp == 1){ 
		winName = '과정관리';
		url = "${serviceBathPath}/cou1100e";
	}else if(temp == 2){ 
		winName = '강사관리';
		url = "${serviceBathPath}/cont2100e";
	}else if(temp == 3){ 
		winName = '차수관리';
		url = "${serviceBathPath}/cou1300e";
	}else if(temp == 4){ 
		winName = '수강신청관리';
		url = "${serviceBathPath}/reg1200e";
	}else if(temp == 5){ 
		winName = '수강현황관리';
		url = "${serviceBathPath}/reg1100e";
	}else if(temp == 6){ 
		winName = '지역별교육과정관리';
		url = "${serviceBathPath}/cont2300e";
	}else if(temp == 7){ <!--운영자메뉴얼-->
		winName = '콘텐츠관리';
		url = "${serviceBathPath}/cont1100e";
		<!-- 		window.open('<c:url value="/template/LW_DP_사용자매뉴얼_v1.0.pdf" />'); -->
	}else if(temp == 8){ 
		winName = '1:1문의';
		url = "${serviceBathPath}/hom1600e?qryAnsDiv=N";
	}else if(temp == 9){ 
		winName = '학습관리';
		url = "${serviceBathPath}/reg1900e?qryAnsDiv=N";
	}else if(temp == 10){
		winName = '과정운영현황';
		var qryYear = $('#qryYear').val();
		url = "${serviceBathPath}/sta1000e";
	}else if(temp == 11){
		winName = '강사관리';
		var ttrReqCnt = $("#ttrReqDiv").text();
		if(ttrReqCnt > 0){
			url = "${serviceBathPath}/cont2100e?qryTtrStatsCd=2";
		}else{
			url = "${serviceBathPath}/cont2100e";
		}
	}else if(temp == 12){
		winName = '지역별교육과정관리';
		var eduReqDiv = $("#eduReqDiv").text();
		if(eduReqDiv > 0){
			url = "${serviceBathPath}/cont2300e?qryAppvlCd=2";
		}else{
			url = "${serviceBathPath}/cont2300e";
		}
	}
	location.href = url;
}

<!-- 메뉴얼 다운로드 기능 -->
function menurDown(val){
	var data = val.data();
	if(data.div == '1') { // 시스템관리자
		window.open('<c:url value="/template/시스템관리자매뉴얼_v1.0.pdf" />');
	}  else if(data.div == '2') { // 인재교육개발팀
		window.open('<c:url value="/template/인재교육개발팀매뉴얼_v1.0.pdf" />');
	} else if(data.div == '3') { // 운영기관
		window.open('<c:url value="/template/운영기관매뉴얼_v1.0.pdf" />');
	} else if(data.div == '4') { // 교육유관부서
		window.open('<c:url value="/template/교육유관부서매뉴얼_v1.0.pdf" />');
	} else { // 사용자
		window.open('<c:url value="/template/사용자매뉴얼_v1.0.pdf" />');
	}
}

<!-- 알림발송 팝업 오픈 오류방지용 -->
function getUsrCdList(){
	var usrCds = "";
	return usrCds;
}

<!-- 차트 로딩바 열기 -->
function chartLoadingStart(){
	$("#chartLoading").show();
}

<!-- 차트 로딩바 닫기 -->
function chartLoadingEnd(){
	$("#chartLoading").hide();
}


<!-- 일별/주간/월별/연도별 접속자 챠트 -->
const ctx02 = document.getElementById('Chart02').getContext('2d');
const mChart02 = new Chart(ctx02, {
	type: 'bar',
	data: {
		labels: [],
		datasets: [
			{
				label : '접속자 수',
				data: [],
				backgroundColor: '#5CD1E5',
				borderWidth: 1,
			},
		]
	},
	options: {
		responsive: true,
		maintainAspectRatio: false,
		scales: {
			y: {
				beginAtZero: true,
			}
		},
		plugins: {
			legend: {
				display: false,
			},
			tooltip: {
				enabled: true,
			},
			datalabels: {
				color: 'white',
				align: 'center',
				anchor: 'center',
				font: {
					weight: 'bold'
				},
				formatter: Math.round,
				display : function(ctx02){
					return ctx02.dataset.data[ctx02.dataIndex] !== 0;
				}
			}
		},
		hover: {
			animationDuration: 0
		},
//		events: []
	},
	plugins: [ChartDataLabels]
});

<!-- 일/주/월/연 변경 이벤트 -->
function chart2PeriodChange(val) {
	
	//1. From~To 변경 및 shotButton 명칭 변경
	var rtn = chart2FromToRebuild(val);
	
	//2. shotButton Click Event Call
	if( rtn ){
		chart2ShotButtonClick('1');
	}
}


<!-- From~To 변경 및 shotButton 명칭 변경 -->
function chart2FromToRebuild(val) {
	//전체 조건 숨김
	$(".period_wrap").find("div").hide();
	
	//전체 조건 clear
	$("#searchForm").find("input").val('');
	//$("#weekDiv").find("select").val('');
	
	//특정 조건 보임
	if( val == '1' ){
		//일간
		$("#startDateToday").show().find("div").show();
		$("#endDateToday").show().find("div").show();
		
		$("#btn1").text("이번달");
		$("#btn2").text("지난달");
		$("#btn3").text("다음달");
	}else if( val == '2' ){
		//주간
		//$("#weekDiv").show();
		
		$("#startDateWeek").show().find("div").show();
		$("#weekDiv1").show();
		$("#endDateWeek").show().find("div").show();
		$("#weekDiv2").show();
		
		$("#btn1").text("이번달");
		$("#btn2").text("지난달");
		$("#btn3").text("다음달");
	}else if( val == '3' ){
		//월간
		$("#startDateMonth").show().find("div").show();
		$("#endDateMonth").show().find("div").show();
		
		$("#btn1").text("이번연도");
		$("#btn2").text("지난연도");
		$("#btn3").text("다음연도");
	}else if( val == '4' ){
		//연간
		$("#startDateYear").show().find("div").show();
		$("#endDateYear").show().find("div").show();
		
		$("#btn1").text("최근3년");
		$("#btn2").text("최근5년");
		$("#btn3").text("최근7년");
	}
	
	return true;
}


<!-- 이번달 / 지난달 / 다음달 버튼 클릭 이벤트 -->
function chart2ShotButtonClick(btnVal) {
	
	//1. param값 설정
	$("#qryBtnVal").val(btnVal);
	
	//2. From~To 기간 조회
	var param = TimsUtil.formToMap("searchForm");
	var ret = TimsUtil.getObject("${svnmBtnDate}", param, false);
	
	if ( ret ) {
		var startDate = ret.btnDate.startDate;
		var endDate   = ret.btnDate.endDate;
		
		//3. From~To 기간 셋팅
		if( $("#qryPeriod").val() == '1' ){
			//일간
			$("#qryApplnStartDt").val(startDate);
			$("#qryApplnEndDt").val(endDate);
		}else if( $("#qryPeriod").val() == '2' ){
			//주간
			$("#qryApplnStartDt2").val(startDate.substring(0,6));
			$("#qryApplnEndDt2").val(endDate.substring(0,6));
			$("#qryWeek1").val('1');
			$("#qryWeek2").val('5');
		}else if( $("#qryPeriod").val() == '3' ){
			//월간
			$("#qryApplnStartDt3").val(startDate);
			$("#qryApplnEndDt3").val(endDate);
		}else if( $("#qryPeriod").val() == '4' ){
			//연간
			$("#qryApplnStartDt4").val(startDate);
			$("#qryApplnEndDt4").val(endDate);
		}
	}
	
	//3. 데이터 조회
	chart2Search();
	
}


<!-- 데이터 조회 -->
function chart2Search() {
	
	var qryPeriod = $("#qryPeriod").val();  //일간/주간/월간/연간
	var param     = null;
	var url       = null;
	var cUrl      = null;
	
	//기간 체크
	if( qryPeriod == '1' ){
		//일간 from~to
		var startDt = $("#qryApplnStartDt").val();
		var endDt   = $("#qryApplnEndDt").val();
		
		if( startDt.length != 8 || endDt.length != 8 ){
			alert('조회 기간을 입력해 주세요.\n(조회 기간은 31일을 초과할 수 없습니다.)');
			return;
		}
		
		if( Number(startDt) > Number(endDt) ){
			alert("조회 기간의 시작일자가 종료일 보다 이후 입니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 31일을 초과할 수 없습니다.)");
			return;
		}
		
		var days = calDays(startDt, endDt, "dd");
		
		if( days > 31 ){
			alert("입력하신 조회 기간(" + days + "일)이 31일을 초과 합니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 31일을 초과할 수 없습니다.)");
			return;
		}
		
		//타이틀 변경
		$("#avg_title").text("일간 평균 접속자 수");
		$("#sum_title").text("일간 전체 접속자 수");
		
		param = {qryApplnStartDt : startDt, qryApplnEndDt : endDt};
		url   = "${svnmAvgToday}";
		cUrl  = "${svnmNumberSearch}";
		
	}else if( qryPeriod == '2' ){
		//주간 from~to
		var startDt = $("#qryApplnStartDt2").val();
		var endDt   = $("#qryApplnEndDt2").val();
		
		if( Number(startDt) > Number(endDt) ){
			alert("조회 기간의 시작(연-월-주)이 종료(연-월-주) 보다 이후 입니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 3개월을 초과할 수 없습니다.)");
			return;
		}
		
		var days = calDays(startDt, endDt, "mm");
		
		if( days > 3 ){
			alert("입력하신 조회 기간(" + days + "개월)이 3개월을 초과 합니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 3개월을 초과할 수 없습니다.)");
			return;
		}
		
		//타이틀 변경
		$("#avg_title").text("주간 평균 접속자 수");
		$("#sum_title").text("주간 전체 접속자 수");
		
		param = {qryApplnStartDt : startDt, qryApplnEndDt : endDt, qryWeek1 : $("#qryWeek1").val(), qryWeek2 : $("#qryWeek2").val()};
		url   = "${svnmAvgWeek}";
		cUrl  = "${svnmSelectWeek}";
		
	}else if( qryPeriod == '3' ){
		//월간 from~to
		var startDt = $("#qryApplnStartDt3").val();
		var endDt   = $("#qryApplnEndDt3").val();
		
		if( startDt.length != 6 || endDt.length != 6 ){
			alert('조회 기간을 입력해 주세요.\n(조회 기간은 24개월을 초과할 수 없습니다.)');
			return;
		}
		
		if( Number(startDt) > Number(endDt) ){
			alert("조회 기간의 시작월이 종료월 보다 이후 입니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 24개월을 초과할 수 없습니다.)");
			return;
		}
		
		var days = calDays(startDt, endDt, "mm");
		
		if( days > 24 ){
			alert("입력하신 조회 기간(" + days + "개월)이 24개월을 초과 합니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 24개월을 초과할 수 없습니다.)");
			return;
		}
		
		//타이틀 변경
		$("#avg_title").text("월간 평균 접속자 수");
		$("#sum_title").text("월간 전체 접속자 수");
		
		param = {qryApplnStartDt : startDt, qryApplnEndDt : endDt, divideNum : days};
		url   = "${svnmAvgMonth}";
		cUrl  = "${svnmSearchMonth}";
		
	}else if( qryPeriod == '4' ){
		//연간 from~to
		var startDt = $("#qryApplnStartDt4").val();
		var endDt   = $("#qryApplnEndDt4").val();
		
		if( startDt.length != 4 || endDt.length != 4 ){
			alert('조회 기간을 입력해 주세요.\n(조회 기간은 20년을 초과할 수 없습니다.)');
			return;
		}
		
		if( Number(startDt) > Number(endDt) ){
			alert("조회 기간의 시작연도가 종료연도 보다 이후 입니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 20년을 초과할 수 없습니다.)");
			return;
		}
		
		var days = calDays(startDt, endDt, "yyyy");
		
		if( days > 20 ){
			alert("입력하신 조회 기간(" + days + "년)이 20년을 초과 합니다.\n조회 기간을 다시 입력해 주세요.\n(조회 기간은 20년을 초과할 수 없습니다.)");
			return;
		}
		
		//타이틀 변경
		$("#avg_title").text("연간 평균 접속자 수");
		$("#sum_title").text("연간 전체 접속자 수");
		
		param = {qryApplnStartDt : startDt, qryApplnEndDt : endDt, divideNum : days};
		url   = "${svnmAvgYear}";
		cUrl  = "${svnmSearchYear}";
	}
	
	//평균 접속자 수, 전체 접속자 수 조회
	var ret = TimsUtil.getObject(url, param, true);
	
	$('#totalUsrNumberDiv').attr("data-num", ret.avgSum.totalUsrNumber);  //평균 접속자 수
	$('#sumUsrNumberDiv').attr("data-num", ret.avgSum.sumUsrNumber);      //전체 접속자 수
	
	//그래프 조회
	chart2Graph(cUrl, param);
	
	// 평균 접속자 수, 전체 접속자 수 애니메이션 효과
	$({ totalUsrNumberToNum: $('#totalUsrNumberDiv').text()}).animate(
		{
			totalUsrNumberToNum: $('#totalUsrNumberDiv').attr('data-num'),
		},
		{
			duration: 1000,  // 애니메이션이 완료될때까지 걸리는 시간
			easing:'linear', // 애니메이션 효과 방식
			step: function() {
			$('#totalUsrNumberDiv').text(Math.floor(this.totalUsrNumberToNum));
		},
		complete: function() {
			$('#totalUsrNumberDiv').text(this.totalUsrNumberToNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
		}
	});
	$({ sumUsrNumberToNum: $('#sumUsrNumberDiv').text()}).animate(
		{
			sumUsrNumberToNum: $('#sumUsrNumberDiv').attr('data-num'),
		},
		{
			duration: 1000,  // 애니메이션이 완료될때까지 걸리는 시간
			easing:'linear', // 애니메이션 효과 방식
			step: function() {
			$('#sumUsrNumberDiv').text(Math.floor(this.sumUsrNumberToNum));
		},
			complete: function() {
			$('#sumUsrNumberDiv').text(this.sumUsrNumberToNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
		}
	});
}


<!-- 그래프 데이터 조회 -->
function chart2Graph(url, param){
	$.ajax({
		url : url,
		dataType : 'json',
		data: TimsUtil.toJSON(param),
		type: 'POST',
		cache : false,
		contentType: 'application/json; charset=utf-8',
		async: true, // false : 동기식, true : 비동기식
		beforeSend: function() {
			chartLoadingStart(); //로딩바 열기
		},
		success : function(ret){
			<!-- 그래프차트 셋팅 -->
			graphSet2(ret);
		},
		complete: function() {
			chartLoadingEnd();//로딩바 닫기
		}
	});
}


<!-- 그래프차트 셋팅 -->
function graphSet2(data){
	var dateList = [];
	var numberList = [];
	
	if($("#qryPeriod").val() == "1"){
		for(var i=0; i<data.tdVo.length; i++){
			dateList.push(data.tdVo[i].acsDate.substr(6)+"일");
			numberList.push(data.tdVo[i].usrNumber);
		}
	}else if($("#qryPeriod").val() == "2"){
		for(var i=0; i<data.selectWeek.length; i++){
			dateList.push(data.selectWeek[i].weekNumber+"주");
			numberList.push(data.selectWeek[i].usrNumber);
		}
	}else if($("#qryPeriod").val() == "3"){
		for(var i=0; i<data.monthVo.length; i++){
			dateList.push(data.monthVo[i].acsDate.substr(4)+"월");
			numberList.push(data.monthVo[i].usrNumber);
		}
	}
	else if($("#qryPeriod").val() == "4"){
		for(var i=0; i<data.yearVo.length; i++){
			dateList.push(data.yearVo[i].acsDate+"년");
			numberList.push(data.yearVo[i].usrNumber);
		}
	}
	mChart02.data.datasets[0].data = numberList;
	mChart02.data.labels = dateList;
	mChart02.update();
}


<!-- 일자계산 -->
function calDays(sVal, eVal, div) {
	
	if( div == "yyyy" ){
		var sYear = parseInt(sVal.substring(0, 4));
		var eYear = parseInt(eVal.substring(0, 4));
		
		return (eYear - sYear + 1);
	}else if( div == "mm" ){
		var sMonth = parseInt(sVal.substring(0, 4)) * 12 + parseInt(sVal.substring(4, 6));
		var eMonth = parseInt(eVal.substring(0, 4)) * 12 + parseInt(eVal.substring(4, 6));
		
		return (eMonth - sMonth + 1);
	}else{
		var sYear  = parseInt(sVal.substring(0, 4));
		var sMonth = parseInt(sVal.substring(4, 6)) - 1;
		var sDay   = parseInt(sVal.substring(6, 8));
		
		var eYear  = parseInt(eVal.substring(0, 4));
		var eMonth = parseInt(eVal.substring(4, 6)) - 1;
		var eDay   = parseInt(eVal.substring(6, 8));
		
		var da1 = new Date(sYear, sMonth, sDay);
		var da2 = new Date(eYear, eMonth, eDay);
		
		var dif    = da2 - da1 + 86400000;  // 1일을 더한다.
		var cDay   = 24 * 60 * 60 * 1000;   // 시 * 분 * 초 * 밀리세컨
		var cMonth = cDay * 30;             // 월 만듬
		var cYear  = cMonth * 12;           // 년 만듬
		
		return parseInt(dif/cDay);
	}
}

</script>