<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : evl1201e.jsp
	// 설명 : 최종합격 관리
	// 작성자 : 백세진
	// 일자 : 2025.04.11
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1201e"></c:set>    
<c:set var="svnmAnnSelectPop" value="${svnm}/popup/annSelect"></c:set>    

 
 <div class="x_panel_wrap">  
	<div class="x_panel_btn">		
		<button class="n_btn btn_md btn_c02">최종합격처리</button>
		<button class="n_btn btn_md btn_c02" data-url="evl1200e" type="button">돌아가기</button>
	</div>
	<div class="x_panel">		
		<div class="search_condition"> 
			<button data-action="search" type="button" class="n_btn btn_md btn_search"><i class="fas fa-search"></i>조회</button>
			<div class="condition_list">
				<dl>
					<dt><label for="">초빙분야</label></dt>
					<dd>
						<select class="form-control" id="" name="" required></select>
					</dd>
				</dl>
				<dl>
					<dt><label for="">채용예정인원</label></dt>
					<dd>
						<div>8</div>
					</dd>
				</dl> 
				<dl>
					<dt><label for="">최종합격인원</label></dt>
					<dd>
						<div>5</div>
					</dd>
				</dl>
				<dl>
					<dt><label for="">불합격문구</label></dt>
					<dd>
						<button class="n_btn btn_md btn_c01" data-action="annSelectPop" type="button">문구 설정</button>
					</dd>
				</dl> 
			</div>
		</div>
	</div> 
	
	<div class="x_content">
		<div class="description"> ※ 지원자 성명을 클릭하시면 지원서를 확인할 수 있습니다.</div>
		<div class="content_list" id="content_list">
			<table class="table">
				<thead>
					<tr>
						<th>순위</th>
						<th>성명</th>
						<th>단과대/학과</th>
						<th>전공분야</th>
						<th>교원구분</th>
						<th>전화</th>
						<th>이메일</th> 
						<th>적격여부</th>
						<th>점수</th> 
						<th>합격처리</th> 
						<th>발송</th>
					</tr>
				</thead>
				<tbody> 
		            <tr> 
						<td>1</td>
						<td>백세진</td>
						<td>공과대학/컴퓨터공학과</td>
						<td>컴퓨터</td>
						<td>연구전담</td> 
						<td>01012345678</td>
						<td>email@email.com</td>
						<td>적격</td>
						<td>점수</td>
						<td><button class="n_btn btn_md btn_c02" id="appPassBtn" onclick="appPass()">합격</button></td>
						<td><button class="n_btn btn_md btn_c02" type="button">발송</button></td> 
		            </tr> 
				</tbody>
			</table> 
		</div>
	</div> 
</div>

<script>
$(document).on('click', 'button[data-url]', function() {
	const url = $(this).data('url');
	if (url) {
		location.href = '${serviceBathPath}/' + url;
	}
});

	/* 합격처리 */ 
	function appPass() {
	    const btn = document.getElementById("appPassBtn");
	    
	    if (btn.innerText === "합격") {
	        btn.innerText = "취소";
	        btn.classList.add("btn-cancel-mode");
	    } else {
	        btn.innerText = "합격";
	        btn.classList.remove("btn-cancel-mode");
	    }
	}
	
	/*불합격 알림문구 설정팝업*/
	$(document).on("click", "button[data-action='annSelectPop']", function () {
		var url = '${svnmAnnSelectPop}'; 
		TimsUtil.urlDialog('(불)합격자 안내 문구 팝업', url, 800, 600);  
	})


</script>