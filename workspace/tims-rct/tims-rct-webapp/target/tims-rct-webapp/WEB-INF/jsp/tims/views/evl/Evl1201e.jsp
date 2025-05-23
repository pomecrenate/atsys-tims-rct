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
<c:set var="svnmSearch" value="${svnm}/search" />
<c:set var="svnmAnnSelectPop" value="${svnm}/popup/annSelect"></c:set>    

 
 <div class="x_panel_wrap">  
	<div class="x_panel_btn">		
		<button class="n_btn btn_md btn_c02">최종합격처리</button>
		<button class="n_btn btn_md btn_c02" data-url="evl1200e" type="button">돌아가기</button>
	</div>
	<div>${ntcCd}</div>
	<div>${stepCd}</div>
	<div class="x_panel">		
		<form method="POST" class="form-horizontal form-label-left" id="search" name="search" >
			<div class="search_condition"> 
				<button data-action="search" type="button" class="n_btn btn_md btn_search"><i class="fas fa-search"></i>조회</button>
				<div class="condition_list">
					<dl>
						<dt><label for="qryCollegeCd">단과대학</label></dt>
						<dd><select class="form-control" id="qryCollegeCd" name="qryCollegeCd"></select></dd>
					</dl>
					<dl>
						<dt><label for="qryDeptCd">학과</label></dt>
						<dd><select class="form-control" id="qryDeptCd" name="qryDeptCd"></select></dd>
					</dl>
					<dl>
						<dt><label for="qryMajorNm">전공분야</label></dt>
						<dd>
							<select class="form-control" id="qryMajorNm" name="qryMajorNm" required></select>
						</dd>
					</dl>
					<dl>
						<dt><label for="requiredCnt">채용예정인원</label></dt>
						<dd>
							<div id="requiredCnt"></div>
						</dd>
					</dl> 
					<dl>
						<dt><label for="finalPassCnt">최종합격인원</label></dt>
						<dd>
							<div id="finalPassCnt"></div>
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
		</form>
	</div> 
	
	<div class="x_content">
		<div class="description"> ※ 지원자 성명을 클릭하시면 지원서를 확인할 수 있습니다.</div>
		<div class="content_list" id="content_list">
			<table class="table" id="final-table">
				<thead>
					<tr>
						<th>순위</th>
						<th>성명</th>
						<th>단과대</th>
						<th>학과</th>
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
				<tbody id="final-tbody">  
				</tbody>
			</table> 
		</div>
	</div>  
	<div class="x_pagination"> 
	</div>
</div>

<script>
const tbColNms = [
	  { type: "text", name: "rank", dataField : "rank" },
	  { type: "text", name: "nm", dataField : "nm" },
	  { type: "text", name: "collegeCd", dataField : "collegeCd" },
	  { type: "text", name: "deptCd" , dataField : "deptCd" },
	  { type: "text", name: "majorNm", dataField : "majorNm" },
	  { type: "text", name: "staffType", dataField : "staffType" },
	  { type: "text", name: "mobPhone", dataField : "mobPhone" },
	  { type: "text", name: "email", dataField : "email" },
	  { type: "text", name: "passYn", dataField : "passYn" },
	  { type: "text", name: "totalAvg", dataField : "totalAvg" },
	  { type: "button", name: "passToggleBtn", btnText: "합격", id : "passToggleBtn", event: appPass },
	  { type: "button", name: "sendBtn", btnText: "발송" }
]; 

$(document).ready(function() { 
	setMultiSelect("", {cls: "", id: "ccMajorNm", cdg : ""}, "search", "qryMajorNm", {type:"", selectAll:"Y", multiYn:"N"});
	/* setMultiSelect("", {cls: "FLD_2", id: "ccCollegeCd", cdg : ""}, "search", "qryCollegeCd", {type:"", selectAll:"Y", multiYn:"N"});
	setMultiSelect("", {cls: "FLD_3", id: "ccDeptCd", cdg : ""}, "search", "qryDeptCd", {type:"", selectAll:"Y", multiYn:"N"}); */
	
	selectRequiredCnt();
	const params = { 'ntcCd' : `${ntcCd}`, 'stepCd' : `${stepCd}` };
	RctUtil.loadPageData("${svnmSearch}", params, "appCd", "final-tbody", tbColNms); 
})  


/* 합격관리 페이지로 돌아가기 */
$(document).on('click', 'button[data-url]', function() {
	const url = $(this).data('url');
	if (url) {
		location.href = '${serviceBathPath}/' + url;
	}
});

/* 합격처리 */ 
function appPass() { 
    const btn = document.getElementById("passToggleBtn");
    
    if (btn.innerText === "합격") {
        btn.innerText = "취소";
        btn.classList.add("btn-cancel-mode");
    } else {
        btn.innerText = "합격";
        btn.classList.remove("btn-cancel-mode");
    }
}
	
/* 불합격 알림문구 설정팝업*/
$(document).on("click", "button[data-action='annSelectPop']", function () {
	var url = '${svnmAnnSelectPop}'; 
	RctUtil.urlDialog('불합격자 안내 문구 팝업', url, 800, 600);  
})

/* 채용공고 채용예정인원수 세팅 */
function selectRequiredCnt() {
	var url = "${serviceBathPath}/evl1201e/selectRequiredCnt";
	var params = { 'ntcCd' : '${ntcCd}' };
	
	const result = TimsUtil.getObject(url, params, true);  
	if (result) {
		$('#requiredCnt').text(result.requiredCnt);
	} else {
		console.log("채용예정인원 불러오기 실패 ! ");
	}
	
}


</script>