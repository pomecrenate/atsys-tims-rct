<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Rct1100e.jsp
	// 설명 : 공고관리
	// 작성자 : 최연재
	// 일자 : 2025.04.18
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/rct1100e"></c:set>   

 <div class="x_panel_wrap">
	<div class="x_panel_title">공고 관리</div> 
	<div class="x_panel">		
		<form method="POST" class="form-horizontal form-label-left" id="search" name="search" >
			<div class="search_condition">
				<button data-action="search" type="button" class="n_btn btn_md btn_search"><i class="fas fa-search"></i> 조회</button>
				<div class="condition_list">
					<dl>
						<dt><label for="qryNtcYear">채용연도</label></dt>
						<dd>
							<select class="form-control" id="qryNtcYear" name="qryNtcYear" required></select>
						</dd>
					</dl>
					<dl>
						<dt><label for="qryNtcSmt">채용학기</label></dt>
						<dd>
							<select class="form-control" id="qryNtcSmt" name="qryNtcSmt" required></select>
						</dd>
					</dl>
				</div>
			</div>
		</form>
	</div>
	
	<div class="x_panel">
		<div class="x_content">
			<div class="content_list" id="content_list">
				<table class="detail_table" id="detail_table">
		    	<colgroup>
					<col style="width:120px">
					<col style="width:auto">
					<col style="width:120px">
					<col style="width:auto">
					<col style="width:120px">
					<col style="width:auto">
				</colgroup>
			      <tr>
			        <th>공고번호</th>
			        <td>
			        	자동생성
			        </td>
			        <th>채용구분</th>
			        <td>
			        	<select class="form-control" id="qryNtcYear" name="qryNtcYear" required></select>
			        </td>
			        <th>채용연도</th>
			        <td>
			        	<select class="form-control" required></select>
			        </td>
			      </tr>
			      <tr>
			        <th>채용학기</th>
			        <td>
			        	<select class="form-control" required></select>
			        </td>
			        <th>공고명</th>
			        <td>
			      	  자동생성
			        </td>
			        <th>공고게시일시</th>
			        <td style="position: relative;">
			        	<input type="text" class="datetimepicker form-control" placeholder="날짜를 선택헤주세요">
			        </td>
			      </tr>
			      <tr>
			        <th>접수일시</th>
			        <td>
			      	    <input type="text" name="daterange" id="daterange" class="daterange-picker form-control" placeholder="날짜를 선택해주세요" />
			        </td>
				    <th>연구실적기준일자</th>
			        <td>
			        	<input type="text" name="daterange" id="daterange" class="daterange-picker form-control" placeholder="날짜를 선택해주세요" />
			        </td>
			        <th>채용예정인원</th>
			        <td>
			      	    <input class="form-control" placeholder="ex) 10"/>
			        </td>
			      </tr>
			      <tr>
				    <th>1차평가일시</th>
			        <td>
			        	<input type="text" name="daterange" id="daterange" class="daterange-picker form-control" placeholder="날짜를 선택해주세요" />
			        </td>
			        <th>2차평가일시</th>
			        <td>
			        	<input type="text" name="daterange" id="daterange" class="daterange-picker form-control" placeholder="날짜를 선택해주세요" />
			        </td>
			        <th>3차평가일시</th>
			        <td>
			        	<input type="text" name="daterange" id="daterange" class="daterange-picker form-control" placeholder="날짜를 선택해주세요" />
			        </td>
			      </tr>
			      <tr>    
			        <th>1차평가발표일시</th>
			        <td>
			      	    <input class="datetimepicker form-control" placeholder="날짜를 선택헤주세요"/>
			        </td>
			        <th>2차평가발표일시</th>
			        <td>
			      	    <input class="datetimepicker form-control" placeholder="날짜를 선택헤주세요"/>
			        </td>
			        <th>3차평가발표일시</th>
			        <td>
			      	    <input class="datetimepicker form-control" placeholder="날짜를 선택헤주세요"/>
			        </td>
			      </tr>
			      <tr>
				    <th>담당부서</th>
			        <td>
			        	자연정보지원팀
			        </td>
			        <th>공고상태</th>
			        <td>
			      	    <select class="form-control" required></select>
			        </td>
			        <th>임용예정일</th>
			        <td>
			      	    <input type="date" class="form-control"/>
			        </td>
			      </tr>
			    </table>
			</div>
		</div> 
	</div>
	<div class="x_panel">
		<div class="description"> ※ 리스트를 클릭하시면 지원서를 상세히 확인할 수 있습니다.</div>
		<div class="content_list" id="content_list">
			<table class="table">
				<div class="list_top">
					<div class="rgt-area">
						<div class="btn-group">
							<button data-action="deleteLft" type="button" class="n_btn btn_md btn_c05">삭제</button>
						</div>
					</div>
				</div>
				<thead>
					<tr>
						<th></th>
						<th>공고상태</th>
						<th>공고번호</th>
						<th>공고명</th>
						<th>공고게시일시</th>
						<th>접수시작일시</th>
						<th>접수종료일시</th>
						<th>내용관리</th>		
					</tr>
				</thead>
				<tbody> 
		            <tr>
		                <td><input type="checkbox" value=""></td>
						<td>공고중</td>
						<td>2025-01-10-001</td>
						<td>전임교원 2025년도 1학기 채용공고</td>
						<td>2025-04-21 00:00:00</td>
						<td>2025-04-22 00:00:00</td>
						<td>2025-04-23 00:00:00</td>
						<td><button class="n_btn btn_md btn_c02" id="appStatusBtn" data-url="rct1101e">공고관리</button></td>
		            </tr> 
				</tbody>
			</table> 
		</div>
	</div> 
</div>


<script>  
	$(document).ready(function() { 
		setMultiSelect("", {cls: "NTC_1", id: "ccNtcYear", cdg : ""}, "search", "qryNtcYear", {type:"", selectAll:"Y", multiYn:"N"});
		setMultiSelect("", {cls: "CMM_1", id: "ccNtcSmt", cdg : "RCT001"}, "search", "qryNtcSmt", {type:"", selectAll:"Y", multiYn:"N"});
	})  
	
	$('.datetimepicker').datetimepicker({
	  format: 'YYYY-MM-DD HH:mm',     // 연도-월-일 형식만
	  locale: 'ko',             // 한국어
	  useCurrent: false,        // 기본값 선택 안 되게
	  showTodayButton: true,    // 오늘로 이동 버튼
	  stepping: 10,   
	  sideBySide: true,
	});
	
	$(function () {
	    $('.daterange-picker').daterangepicker({
	      autoUpdateInput: false,
	      timePicker: true,     
	      timePicker24Hour: true,
	      timePickerIncrement: 10, 
	      locale: {
	        format: 'YYYY-MM-DD HH:mm',       // 날짜 포맷
	        separator: ' ~ ',           // 구분자
	        applyLabel: '확인',
	        cancelLabel: '취소',
	        fromLabel: '시작',
	        toLabel: '종료',
	        customRangeLabel: '직접 선택',
	        daysOfWeek: ['일', '월', '화', '수', '목', '금', '토'],
	        monthNames: ['1월','2월','3월','4월','5월','6월',
	                     '7월','8월','9월','10월','11월','12월'],
	        firstDay: 0,
	      },
	      opens: 'right',               // 달력 열리는 방향
	      startDate: moment().startOf('month'), // 기본 시작일
	      endDate: moment().endOf('month')      // 기본 종료일
	    });
  	});
	
	$('.daterange-picker').on('apply.daterangepicker', function(ev, picker) {
		  $(this).val(picker.startDate.format('YYYY-MM-DD') + ' ~ ' + picker.endDate.format('YYYY-MM-DD'));
		});

	$('.daterange-picker').on('cancel.daterangepicker', function(ev, picker) {
	  $(this).val('');
	});

	$(document).on('click', 'button[data-url]', function() {
		const url = $(this).data('url');
		if (url) {
			location.href = '${serviceBathPath}/' + url;
		}
	}); 
</script>

