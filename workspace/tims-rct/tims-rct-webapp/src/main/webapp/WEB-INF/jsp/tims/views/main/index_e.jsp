<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>

<c:set var="svnm" value="${serviceBathPath}/chart"/>
<c:set var="svnmCntSearch" value="${svnm}/cntSearch"></c:set>
<c:set var="svnmChartSearch" value="${svnm}/chartSearch"></c:set>

<c:set var="svnmNtf" value="${serviceBathPath}/notification"/>
<c:set var="svnmSendMsgPop" value="${svnmNtf}/sendMsgPop"/>

	<div class="list_top">
		<div class="lft-area">
			<p class="list_title"> Learnway 현황</p>
			<select class="form-control form_inline_block fs14" id="qryYear" name="qryYear" style="background-color:#fff;"></select>
			<select class="form-control form_inline_block fs14" id="qryMonth" name="qryMonth" style="background-color:#fff;"></select>
		</div>
		<div class="rgt-area">
			<select class="form-control form_inline_block fs14" style="background-color:#fff;" id="qryCrsOpt" name="qryCrsOpt" data-action="chartSet">
				<option value='1'>월간 클래스 수강현황</option>
				<option value='2'>월간 카테고리별 클래스 수강현황</option>
				<option value='3'>연간 클래스 수강현황</option>
				<option value='4'>연간 콘텐츠 운영 현황</option>
			</select>
		</div>
	</div>
	<div class="n_chart">
		<div class="overall_status">
			<div class="number_status">
				<div>
					<div class="x_panel">
						<p class="list_title">당일 접속자수
							<span class="chart_info_wrap">
								<a href="#" class="btn_info">?</a>
								<span class="info">실시간 DAU(Daily Active Users),<br />FO 일별 활동 사용자수</span>
							</span>
						</p>
						<div>
							<div>전일 ${acsVo.acsYesNCnt}<em id="acsEm">${acsVo.acsCnt}&nbsp;&nbsp;&nbsp;${acsVo.acsAvgCnt}%</em></div>
							<div class="number" data-num="${acsVo.acsToNCnt}" id="todayCntDiv"></div>
						</div>
					</div>
				</div>
				<div>
					<div class="x_panel">
						<p class="list_title"><span class="monSpan"></span>월 접속자수
							<span class="chart_info_wrap">
								<a href="#" class="btn_info">?</a>
								<span class="info">해당 월의 FO 활동 사용자수</span>
							</span>
						</p>
						<div>
							<div><span id="exMonSpan">전월 ${acsMonVo.acsYesNCnt}</span><em id="acsMonEm">${acsMonVo.acsCnt}&nbsp;&nbsp;&nbsp;${acsMonVo.acsAvgCnt}%</em></div>
							<div class="number" data-num="${acsMonVo.acsToNCnt}" id="acsToCntDiv"></div>
						</div>
					</div>
				</div>
				<div>
					<div class="x_panel">
						<p class="list_title"><span class="monSpan"></span>월 운영클래스
							<span class="chart_info_wrap">
								<a href="#" class="btn_info">?</a>
								<span class="info">교육시작일이 해당 월인 클래스</span>
							</span>
							<div class="rgt-area">
								<button type="button" class="n_btn btn_md btn_c04" onclick="menuMove(10);">클래스현황</button>
							</div>
						</p>
						<div>
							<div><span id="cowaySpan">자체 ${crsVo.crsCowayCnt}</span><span id="multiSpan">외부 ${crsVo.crsMultiCnt}</span><span id="ostrSpan">위탁 ${crsVo.ostrCnt}</span></div>
							<div class="number" data-num="${crsVo.crsTotCnt}" id="crsTotDiv"></div>
						</div>
					</div>
				</div>
				<div>
					<div class="x_panel">
						<p class="list_title"><span class="monSpan"></span>월 업로드 콘텐츠
							<div class="rgt-area">
								<button type="button" class="n_btn btn_md btn_c04" onclick="menuMove(11);">콘텐츠현황</button>
							</div>
						</p>
						<div>
							<div><span id="videoSpan">영상 ${crsVo.contVideoCnt}</span><span id="pdfSpan">리포트 ${crsVo.contPdfCnt}</span><span id="imgSpan">이미지 ${crsVo.contImgCnt}</span></div>
							<div class="number" data-num="${crsVo.contTotCnt}" id="contCntDiv"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="float-wrap">
				<div class="lft_wrap">
					<div class="x_panel" id="monthClassDiv">
						<div class="list_top">
							<p class="list_title"><span class="monSpan"></span>월 클래스 수강현황
								<span class="chart_info_wrap">	
									<a href="#" class="btn_info">?</a>
									<span class="info">월간 클래스 유형별 수료생/수강생 수 (클래스 학습시작일 기준)</span>
								</span>
							</p>
							<div class="rgt-area">
								<p class="n_btn btn_md2 btn_c11">수료생</p>
								<p class="n_btn btn_md2 btn_c12">수강생</p>
							</div>
						</div>
						<div style="position:relative;width:100%;height:400px;">
							<canvas id="Chart01"></canvas>
						</div>	
					</div>
 					<div class="x_panel" id="monthCatgDiv" style="display:none;">
 						<div class="list_top">
							<p class="list_title"><span class="monSpan"></span>월 카테고리별 클래스 수강현황
								<span class="chart_info_wrap">
									<a href="#" class="btn_info">?</a>
									<span class="info">월간 클래스 카테고리별 수료생/수강생 수 (클래스 학습시작일 기준)</span>
								</span>
							</p>
							<div class="rgt-area">
								<p class="n_btn btn_md2 btn_c11">수료생</p>
								<p class="n_btn btn_md2 btn_c12">수강생</p>
							</div>
						</div>
						<div style="position:relative;width:100%;height:400px;">
							<canvas id="Chart03"></canvas>
						</div>
					</div>
					<div class="x_panel" id="yearClassDiv" style="display:none;">
						<div class="list_top">
							<p class="list_title">연간 클래스 수강생현황
								<span class="chart_info_wrap">
									<a href="#" class="btn_info">?</a>
									<span class="info">연간 클래스 유형별 수료생/수강생 수 (클래스 학습시작일 기준)</span>
								</span>
							</p>
							<div class="rgt-area">
							<input type="hidden" name="tabDiv" id="tabDiv" value="all"/>
								<div class="btn_tab">
									<ul class="tab_type02 tab_chart">
										<li class="active" id="allTab"><button type="button" onclick="yearChartTabSet('all')">전체</button></li>
										<li><button	type="button" onclick="yearChartTabSet('coway')">자체</button></li>
										<li><button type="button" onclick="yearChartTabSet('multi')">외부클래스</button></li>
										<li><button type="button" onclick="yearChartTabSet('otsr')">외부위탁</button></li>
									</ul>
								</div>
							</div>
						</div>
						<ul class="chart_list">
							<li class="open" style="position:relative;width:100%;height:400px;"><canvas id="ChartAll"></canvas></li>
							<li style="position:relative;width:100%;height:400px;"><canvas id="ChartCoway"></canvas></li>
							<li style="position:relative;width:100%;height:400px;"><canvas id="ChartMulti"></canvas></li>
							<li style="position:relative;width:100%;height:400px;"><canvas id="ChartOtsr"></canvas></li>
						</ul>
					</div>
					<div class="x_panel" id="yearContDiv" style="display:none;">
						<div class="list_top">
							<p class="list_title">연간 콘텐츠 운영현황
								<span class="chart_info_wrap">
									<a href="#" class="btn_info">?</a>
									<span class="info">해당 월에 업로드된 콘텐츠 수와 해당 누적 조회수 (콘텐츠 편성시작일 기준)</span>
								</span>
							</p>
							<div class="rgt-area">
								<p class="n_btn btn_md2 btn_c13">콘텐츠수</p>
								<p class="n_btn btn_md2 btn_c14">누적 조회수</p>
							</div>
						</div>
						<div style="position:relative;width:100%;height:400px;">
							<canvas id="Chart08"></canvas>
						</div>
					</div> 
				</div>
				<div class="rgt_wrap">
					<div class="x_panel">
						<p class="list_title">업무 바로가기
							<div class="rgt-area">
								<button type="button" class="n_btn btn_md btn_c04" data-action="eduAdmin2">운영자매뉴얼</button>
							</div>
						</p>
						
						<ul class="work_menu">
							<li class="menu01"><a href="#" onclick="menuMove(1);">수강신청<br />관리</a></li> 
							<li class="menu02"><a href="#" onclick="menuMove(2);">외부위탁<br />수강관리</a></li>
							<li class="menu03"><a href="#" onclick="menuMove(3);">교육프로그램<br />안내</a></li>
							<li class="menu04"><a href="#" onclick="menuMove(4);">공지사항</a></li>
							<li class="menu05"><a href="#" onclick="menuMove(5);">팝업관리</a></li>
							<li class="menu06"><a href="#" onclick="menuMove(6);">알림발송</a></li>
							<li class="menu07"><a href="#" onclick="menuMove(7);">설문현황</a></li>
							<li class="menu08"><a href="#" onclick="menuMove(8);">1:1문의<span id="qnaCnt">${crsVo.qnaCnt}</span></a></li>
							<li class="menu09"><a href="#" onclick="menuMove(9);">클래스 Q&amp;A<span id="crsQnaCnt">${crsVo.crsQnaCnt}</span></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="chartLoading" style="position:fixed;left:0;top:0;width:100%;height:100%;background:rgba(255, 255, 255, 0.3);display:none">
		<p style="text-align:center;position:absolute;top:50%;width:100%;margin-top:-20px;"><img src="<c:url value='/images/common/process.gif"'/>"></p>
	</div>

<!-- 추가 -->
<script type="text/javascript" src="<c:url value='${gentelellaUrl}/vendors/Chart.js/dist/Chart.min.js'/>"></script>
<script type="text/javascript">
$(function(){
	todayNumCnt();
	numCnt();
	$('.tab_chart > li').on('click', function(){
		var _i = $(this).index();
		$(this).addClass('active').siblings('li').removeClass('active');
	});
});

function popGuide(){
	if($('.pop_guide').hasClass('open')){
		$('body').css('overflow-y', 'auto');
		$('.pop_guide').removeClass('open');
	}else{
		$('body').css('overflow-y', 'hidden');
		$('.pop_guide').addClass('open');
	}
}

function todayNumCnt(){
	$({ todayNum: $('#todayCntDiv').text()
	  }).animate({
		todayNum: $('#todayCntDiv').attr('data-num')
		},
		{
 			duration: 2000, // 애니메이션이 완료될때까지 걸리는 시간
			easing:'linear', // 애니메이션 효과 방식
			step: function() {
			$('#todayCntDiv').text(Math.floor(this.todayNum));
		},
			complete: function() {
			$('#todayCntDiv').text(this.todayNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
		}
	});
}

function numCnt(){
	$({ acsToNum: $('#acsToCntDiv').text(),
		crsTotNum: $('#crsTotDiv').text(),
		contTotNum: $('#contCntDiv').text()
	  }).animate({
		acsToNum: $('#acsToCntDiv').attr('data-num'),
		crsTotNum: $('#crsTotDiv').attr('data-num'),
		contTotNum: $('#contCntDiv').attr('data-num')
		},
		{
 			duration: 2000, // 애니메이션이 완료될때까지 걸리는 시간
			easing:'linear', // 애니메이션 효과 방식
			step: function() {
			$('#acsToCntDiv').text(Math.floor(this.acsToNum));
			$('#crsTotDiv').text(Math.floor(this.crsTotNum));
			$('#contCntDiv').text(Math.floor(this.contTotNum));
		},
			complete: function() {
			$('#acsToCntDiv').text(this.acsToNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
			$('#crsTotDiv').text(this.crsTotNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
			$('#contCntDiv').text(this.contTotNum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
		}
	});
}

// 월간 클래스 수강현황
const ctx01 = document.getElementById('Chart01').getContext('2d');
const mChart01 = new Chart(ctx01, {
	type: 'bar',
	data: {
		labels: ['전체 클래스', '자체 온라인', '자체 오프라인', '외부클래스', '외부위탁직무과정'],
		datasets: [
			{
			   label : '수료생',
			   data: ['${crsMonthVo.atndCmptAllCnt}', '${crsMonthVo.atndCmptOnlineCnt}', '${crsMonthVo.atndCmptOfflineCnt}', '${crsMonthVo.atndCmptMultiCnt}', '${crsMonthVo.atndCmptOtsrCnt}'],
			   backgroundColor: '#c2e41b',
			},
			{
			   label : '수강생',
			   data: ['${crsMonthVo.atndAllCnt}', '${crsMonthVo.atndOnlineCnt}', '${crsMonthVo.atndOfflineCnt}', '${crsMonthVo.atndMultiCnt}', '${crsMonthVo.atndOtsrCnt}'],
			   backgroundColor: '#59b1e0',
			},
		]
	},
	options: {
		responsive:true,
		maintainAspectRatio:false,
		legend: {
			display:false,
		},
	}
});

//월간 카테고리별 클래스 수강현황
const ctx03 = document.getElementById('Chart03').getContext('2d');
const mChart03 = new Chart(ctx03, {
	type: 'bar',
	data: {
		labels: [
			<c:forEach items="${cntsDivList}" var="cnts" varStatus="status">
				<c:if test="${fn:length(cntsDivList) ne status.index + 1}">
				'${cnts.cntsDivNm}',
				</c:if>
				<c:if test="${fn:length(cntsDivList) eq status.index + 1}">
				'${cnts.cntsDivNm}'
				</c:if>
			</c:forEach>
			],
		datasets: [
			{
				label : '수료생',
				data: [],
				backgroundColor: '#c2e41b',
			},
			{
				label : '수강생',
				data: [],
				backgroundColor: '#59b1e0',
			},
		]
	},
	options: {
		responsive:true,
		maintainAspectRatio:false,
		legend: {
			display:false,
		},
		scales: {
			xAxes: [{
				stacked: true,
				ticks:{
					fontSize : 12
				},
			}],
		}
	}
});

//연간 클래스 수강생현황(전체)
const ctx04 = document.getElementById('ChartAll').getContext('2d');
const mChart04 = new Chart(ctx04, {
	type: 'bar',
	data: {
		labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		datasets: [
			{
			   label : '수료생',
			   data: [],
			   backgroundColor: '#c2e41b',
			},
			{
			   label : '수강생',
			   data: [],
			   backgroundColor: '#59b1e0',
			},

		]
	},
	options: {
		responsive:true,
		maintainAspectRatio:false,
		legend: {
			display:false,
		},
		scales: {
			xAxes: [{ stacked: true }]
		},
	}
});


//연간 콘텐츠 운영현황
const ctx08 = document.getElementById('Chart08').getContext('2d');
const mChart08 = new Chart(ctx08, {
	type: 'bar',
	data: {
		labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		datasets: [
			{
				label : '콘텐츠수',
			    data: [],
			    backgroundColor: '#ffd801',
			},
			{
				label : '누적 조회수',
			    data: [],
			    backgroundColor: '#50d0bc',
			},
		]
	},
	options: {
		responsive:true,
		maintainAspectRatio:false,
		legend: {
			display:false,
		},
		scales: {
			xAxes: [{ stacked: true }]
		},
	}
});

$(document).ready(function() {
	setCombo("", {cls: "YEAR_01", id: "ccQryYear", cdg : "YEAR_01"}, "F", "", "qryYear", "N", "N");
	setCombo("", {cls: "MONTH_01", id: "ccQryMonth", cdg : "COM530"}, "F", "", "qryMonth", "N", "N");
	$('#qryYear').val('${qryYear}');
	$('#qryMonth').val('${qryMonth}');
	$('.monSpan').text(Number('${qryMonth}'));
	
// 	<!-- 년도 change -->
	$("#qryYear").change(function(){
		avgCntSet("Y"); 
	});
// 	<!-- 월 change -->
	$("#qryMonth").change(function(){
		avgCntSet("M"); 
		var value = $(this).val();
		$('.monSpan').text(Number(value));
	});
	
// 	<!-- 차트 오픈 여부 change 이벤트 -->
	$("#qryCrsOpt").change(function(){
		var value = $(this).val();
		if(value == '1'){
			$("#monthClassDiv").show();
			$("#monthCatgDiv").hide();
			$("#yearClassDiv").hide();
			$("#yearContDiv").hide();
		}else if(value == '2'){
			$("#monthClassDiv").hide();
			$("#monthCatgDiv").show();
			$("#yearClassDiv").hide();
			$("#yearContDiv").hide();
		}else if(value == '3'){
			$("#monthClassDiv").hide();
			$("#monthCatgDiv").hide();
			$("#yearClassDiv").show();
			$("#yearContDiv").hide();
		}else{
			$("#monthClassDiv").hide();
			$("#monthCatgDiv").hide();
			$("#yearClassDiv").hide();
			$("#yearContDiv").show();
		}
	});
	
	acsCntSet(); 
	openPopup();
});

// <!-- 당일 접속자수, 월평균 접속자수 현황 셋팅(화살표) -->
function acsCntSet() {
	var ansDiv = '${acsVo.acsDiv}';
	if(ansDiv == 'DOWN'){
		$("#acsEm").attr('class','down');
	}else if(ansDiv == 'UP'){
		$("#acsEm").attr('class','up');
	}else{
		$("#acsEm").attr('class','same');
	}
	
	ansDiv = '${acsMonVo.acsDiv}';
	if(ansDiv == 'DOWN'){
		$("#acsMonEm").attr('class','down');
	}else if(ansDiv == 'UP'){
		$("#acsMonEm").attr('class','up');
	}else{
		$("#acsMonEm").attr('class','same');
	}
}

// <!-- 년월 change시 값 세팅 -->
function avgCntSet(yearMonth) {
// 	<!-- 월간클래스현황, 월간카테고리클래스현황에만 그래프 다시 셋팅하기 위해-->
	var loadingOpenDiv = false;
	if((($("#qryCrsOpt").val() == "1" || $("#qryCrsOpt").val() == "2") && yearMonth == "M") || yearMonth == "Y"){
		loadingOpenDiv = true;
	}
	var param = {qryYearMonth : $("#qryYear").val()+'-'+$("#qryMonth").val()+'-'+'01', 
				 qryCrsOpt : $("#qryCrsOpt").val(), 
				 tabDiv : $("#tabDiv").val(),
				 qryYear : $("#qryYear").val(),
				 qryMonth : $("#qryMonth").val(),
				 yearMonth : yearMonth
				};
	mainAjax('${svnmCntSearch}', param, loadingOpenDiv, true);
}

// 차트 selectBox change시 값 세팅
function chartSet(){
	$("#allTab").addClass('active').siblings('li').removeClass('active');
	var qryCrsOpt = $("#qryCrsOpt").val();
	var param = {qryYearMonth : $("#qryYear").val()+'-'+$("#qryMonth").val()+'-'+'01', qryCrsOpt : qryCrsOpt, tabDiv : "all"};
	mainAjax('${svnmChartSearch}', param, true, false);
}


// 연간 클래스 수강생현황 탭 차트  값 세팅
function yearChartTabSet(tabDiv){
	$("#tabDiv").val(tabDiv);
	var qryCrsOpt = $("#qryCrsOpt").val();
	var param = {qryYearMonth : $("#qryYear").val()+'-'+$("#qryMonth").val()+'-'+'01', qryCrsOpt : qryCrsOpt, tabDiv : tabDiv};
	mainAjax('${svnmChartSearch}', param, true, false);
}

// 비동기식으로 처리해야 로딩바 및 차트값 불러올수 있어서 mainAjax 따로 구현 (url, 넘길값, 로딩바오픈여부, 년월체인지여부)
function mainAjax(url, param, loadingOpenDiv, ymChange){
	var qryCrsOpt = $("#qryCrsOpt").val();
	$.ajax({
		url : url,
		dataType : 'json',
		data: TimsUtil.toJSON(param),
		type: 'POST',
		cache : false,
		contentType: 'application/json; charset=utf-8',
		async: true, // false : 동기식, true : 비동기식
		beforeSend: function() {
			if(loadingOpenDiv){
				chartLoadingStart();
			}
	    },
	    complete: function() {
	    	if(loadingOpenDiv){
	    		chartLoadingEnd();
			}
	    },
		success : function(data){
			if(ymChange){
				<!-- 월 평균 접속자수/일 세팅 -->
				$("#exMonSpan").text("전월 " + data.acsMonVo.acsYesNCnt);
				$("#acsMonEm").text(data.acsMonVo.acsCnt+"\u00a0\u00a0\u00a0"+data.acsMonVo.acsAvgCnt+'%');
				if(data.acsMonVo.acsDiv == 'DOWN'){
					$("#acsMonEm").attr('class','down');
				}else if(data.acsMonVo.acsDiv == 'UP'){
					$("#acsMonEm").attr('class','up');
				}else{
					$("#acsMonEm").attr('class','same');
				}
				$('#acsToCntDiv').attr("data-num", data.acsMonVo.acsToNCnt);
				
				<!-- 월 운영클래스 세팅 -->
				$("#cowaySpan").text("자체 " + data.crsVo.crsCowayCnt);
				$("#multiSpan").text("외부 " + data.crsVo.crsMultiCnt);
				$("#ostrSpan").text("위탁 " + data.crsVo.ostrCnt);
				$('#crsTotDiv').attr("data-num", data.crsVo.crsTotCnt);
				
				<!-- 월 업로드 콘텐츠 세팅 -->
				$("#videoSpan").text("영상 " + data.crsVo.contVideoCnt);
				$("#pdfSpan").text("리포트 " + data.crsVo.contPdfCnt);
				$("#imgSpan").text("이미지 " + data.crsVo.contImgCnt);
				$('#contCntDiv').attr("data-num", data.crsVo.contTotCnt);
	
				<!-- 듀레이션 실행 -->
				numCnt();
				
				<!-- 1:1문의 미답변수, 클래스 Qna 미답변수 -->
				$("#qnaCnt").text(data.crsVo.qnaCnt);
				$("#crsQnaCnt").text(data.crsVo.crsQnaCnt);
			}
			<!-- 차트 그래프 셋팅 : 월간클래스현황, 월간카테고리클래스현황에만 그래프 다시 셋팅-->
			if(loadingOpenDiv){
				graphSet(data, qryCrsOpt);
			}
		}
	});
}

// 차트 그래프 셋팅	
function graphSet(data, qryCrsOpt){
	if(qryCrsOpt == '1'){ <!-- 월간 클래스 수강현황 그래프 값 세팅 --> 
		var v_cmptAtndArr = [data.crsMonthVo.atndCmptAllCnt, data.crsMonthVo.atndCmptOnlineCnt, data.crsMonthVo.atndCmptOfflineCnt, data.crsMonthVo.atndCmptMultiCnt, data.crsMonthVo.atndCmptOtsrCnt];
		var v_atndArr = [data.crsMonthVo.atndAllCnt, data.crsMonthVo.atndOnlineCnt, data.crsMonthVo.atndOfflineCnt, data.crsMonthVo.atndMultiCnt, data.crsMonthVo.atndOtsrCnt];
		mChart01.data.datasets[0].data = v_cmptAtndArr;
		mChart01.data.datasets[1].data = v_atndArr;
		mChart01.update();
	}else if(qryCrsOpt == '2'){ <!-- 월간 카테고리별 클래스 그래프 값 세팅 -->
		var v_cmptAtndArr = [];
		var v_atndArr = [];
	
		const dataArr = Object.entries(data.crsCateVo[0]);
		for(i=0; i<dataArr.length; i++){
			if(dataArr[i][0].includes('cmptCntsDivCnt')){
				v_cmptAtndArr.push(dataArr[i][1]);
			}
			if(dataArr[i][0].includes('cntsDivCnt')){
				v_atndArr.push(dataArr[i][1]);
			}
		}
		mChart03.data.datasets[0].data = v_cmptAtndArr;
		mChart03.data.datasets[1].data = v_atndArr;
		mChart03.update();
	}else if(qryCrsOpt == '3'){ <!-- 연간 클래스 수강현황 그래프 값 세팅 -->
		var v_cmptAtndArr = [
			data.crsYearVo.cmptAtndMonth1, 
			data.crsYearVo.cmptAtndMonth2, 
			data.crsYearVo.cmptAtndMonth3, 
			data.crsYearVo.cmptAtndMonth4, 
			data.crsYearVo.cmptAtndMonth5, 
			data.crsYearVo.cmptAtndMonth6, 
			data.crsYearVo.cmptAtndMonth7, 
			data.crsYearVo.cmptAtndMonth8, 
			data.crsYearVo.cmptAtndMonth9, 
			data.crsYearVo.cmptAtndMonth10, 
			data.crsYearVo.cmptAtndMonth11, 
			data.crsYearVo.cmptAtndMonth12 
			];
		var v_atndArr = [
			data.crsYearVo.atndMonth1, 
			data.crsYearVo.atndMonth2, 
			data.crsYearVo.atndMonth3, 
			data.crsYearVo.atndMonth4, 
			data.crsYearVo.atndMonth5, 
			data.crsYearVo.atndMonth6, 
			data.crsYearVo.atndMonth7, 
			data.crsYearVo.atndMonth8, 
			data.crsYearVo.atndMonth9, 
			data.crsYearVo.atndMonth10, 
			data.crsYearVo.atndMonth11, 
			data.crsYearVo.atndMonth12 
			];
		mChart04.data.datasets[0].data = v_cmptAtndArr;
		mChart04.data.datasets[1].data = v_atndArr;
		mChart04.update();
	}else{ <!-- 연간 콘텐츠 운영현황 그래프 값 세팅 -->
		var v_contArr = [
			data.contYearVo.contMonth1, 
			data.contYearVo.contMonth2, 
			data.contYearVo.contMonth3, 
			data.contYearVo.contMonth4, 
			data.contYearVo.contMonth5, 
			data.contYearVo.contMonth6, 
			data.contYearVo.contMonth7, 
			data.contYearVo.contMonth8, 
			data.contYearVo.contMonth9, 
			data.contYearVo.contMonth10, 
			data.contYearVo.contMonth11, 
			data.contYearVo.contMonth12 
		];
		var v_viewContArr = [
			data.contYearVo.contViewMonth1, 
			data.contYearVo.contViewMonth2, 
			data.contYearVo.contViewMonth3, 
			data.contYearVo.contViewMonth4, 
			data.contYearVo.contViewMonth5, 
			data.contYearVo.contViewMonth6, 
			data.contYearVo.contViewMonth7, 
			data.contYearVo.contViewMonth8, 
			data.contYearVo.contViewMonth9, 
			data.contYearVo.contViewMonth10, 
			data.contYearVo.contViewMonth11, 
			data.contYearVo.contViewMonth12
		];
		mChart08.data.datasets[0].data = v_contArr;
		mChart08.data.datasets[1].data = v_viewContArr;
		mChart08.update();
	}
}

<!-- 차트 로딩바 열기 -->
function chartLoadingStart(){
	$("#chartLoading").show();
}

<!-- 차트 로딩바 닫기 -->
function chartLoadingEnd(){
	$("#chartLoading").hide();
}

<!-- 알림발송 팝업 오픈 오류방지용 -->
function getUsrCdList(){
	var usrCds = "";
	return usrCds;
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

// <!-- 메뉴 새창 이동 -->
function menuMove(temp){
	var url = '';
	var winName = '';
	if(temp == 1){ //<!-- 수강신청관리 -->
	    winName = '수강신청관리';
		url = "${serviceBathPath}/reg1200e";
	}else if(temp == 2){ //<!-- 수강신청관리(외부위탁) -->
		winName = '수강신청관리';
		url = "${serviceBathPath}/cms1200e";
	}else if(temp == 3){ //<!-- 교육프로그램안내 -->
		winName = '교육프로그램안내';
		url = "${serviceBathPath}/hom2200e";
	}else if(temp == 4){ //<!-- 공지사항 -->
		winName = '공지사항';
		url = "${serviceBathPath}/hom1700e";
	}else if(temp == 5){ //<!-- 팝업관리 -->
		winName = '팝업관리';
		url = "${serviceBathPath}/hom1100e";
	}else if(temp == 6){ //<!-- 알림발송팝업 오픈 -->
		TimsUtil.urlDialog('알림발송', '${svnmSendMsgPop}', 1300, 750);
		return;
	}else if(temp == 7){ //!-- 설문참여현황(통계) -->
		winName = '설문참여현황';
		url = "${serviceBathPath}/sta1500e";
	}else if(temp == 8){ //<!-- 1:1문의 -->
		winName = '1:1문의';
		url = "${serviceBathPath}/hom1600e";
	}else if(temp == 9){ //<!-- Q&A 수강 -->
		winName = 'Q&A관리';
		var qryYearMonth = $('#qryYear').val() + $('#qryMonth').val() + "01";
		url = "${serviceBathPath}/reg1900e?qryYearMonth=" + qryYearMonth + "&qryAnsDiv=NON";
	}else if(temp == 10){ //<!-- 클래스현황 -->
		winName = '클래스수료율현황';
		var qryYearMonth = $('#qryYear').val() + $('#qryMonth').val() + "01";
		url = "${serviceBathPath}/sta1700e?qryYearMonth=" + qryYearMonth;
	}else if(temp == 11){ //<!-- 콘텐츠현황 -->
		winName = '콘텐츠통계';
		var qryYearMonth = $('#qryYear').val() + $('#qryMonth').val() + "01";
		url = "${serviceBathPath}/sta2200e?qryYearMonth=" + qryYearMonth;
	}
// 	location.href = url;
	TimsUtil.openTabWindow(url, winName);
}
//운영자매뉴얼 다운로드
function eduAdmin2(){
	window.open('<c:url value="/template/LW_DP_운영자매뉴얼_v1.1.pdf" />');
}
</script>
