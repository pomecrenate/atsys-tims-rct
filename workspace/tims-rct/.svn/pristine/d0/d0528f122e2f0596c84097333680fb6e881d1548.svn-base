<%--
////////////////////////////////////////////////////////////
// 프로그램명 : Rcp1100p4.jsp
// 설명 : 지원서상세보기(팝업)
// 작성자 : 백세진
// 일자 : 2025.04.23
////////////////////////////////////////////////////////////
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<html>
<head>
    <title>${app.nm} 지원서 상세보기</title> 
    <%@ include file="/WEB-INF/jsp/tims/common/layout/adm/head.jsp" %>     
    <style>
		.hide-scrollbar::-webkit-scrollbar {display: none;}
		.hide-scrollbar {scrollbar-width: none;}
    </style>
</head>
<body>	
	<div class="toolbar"> 
	  <div class="toolbar-left">
	   <button id="zoom-out-btn"><i class="fa fa-search-minus"></i></button>
	    <span id="zoomValue">100%</span>
	    <button id="zoom-in-btn"><i class="fa fa-search-plus"></i></button> 
	    <button id="zoom-reset-btn"><i class="fa fa-sync-alt"></i></button> 
	  </div> 
	  <div class="toolbar-center">
	    <span id="pageNumber">1</span> / <span id="totalPages">1</span>
	  </div> 
	  <div class="toolbar-right"> 
	    <button onclick="printPDF()"><i class="fa fa-print"></i></button>
	    <button onclick="downloadPDF()"><i class="fa fa-download"></i></button> 
	  </div>
	</div> 
	<div class="zoom-wrapper" id="zoom-area">	
		<div class="zoom-target">
			<div class="appDetail-container">  
				<div class="appDetail-contents">
					<table class="appDetail_table" id="appDetail_table1"> 
					  <colgroup>
					    <col style="width: 100px;">  
					    <col style="width: 120px;">  
					    <col style="width: 320px;">                    
					  </colgroup>
					  <thead>
					    <tr>
					      <th colspan="3">${app.nm} 임용 지원서(${app.ntcType})</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <td rowspan="3">지원자 사진</td>
					      <th>지원분야</th>
					      <td>${app.ntcCd}</td>
					    </tr>
					    <tr>
					      <th>전공분야(관련분야)</th>
					      <td>${app.fieldCd}</td>
					    </tr>
					    <tr>
					      <th>비고(지원자격 등)</th>
					      <td></td>
					    </tr>
					  </tbody>
					</table> 
				   	<div>인적사항</div>
				   	<table class="appDetail_table" id="appDetail_table2"> 
					    <tr>
					        <th>이름</th><td colspan="5">${app.nm}</td> 
					    </tr>
					    <tr>
					    	<th>한자이름</th><td colspan="2">${app.hanjaNm}</td>
					        <th>영문이름</th><td colspan="2">${app.engNm}</td>
					    </tr>
					    <tr>
					    	<th>생년월일</th><td colspan="5">${app.birthDate}</td>
					    </tr>
					    <tr> 
					        <th>자택전화</th><td colspan="2">${app.homePhone}</td>
					        <th>회사전화</th><td colspan="2">${app.officePhone}</td>
					    </tr>
					    <tr>
					        <th>휴대폰번호</th><td colspan="5">${app.mobPhone}</td>
					    </tr>
					    <tr>
					        <th>주소</th><td colspan="5">${app.postalCode}) ${app.address}</td>
					    </tr>
					    <tr>
					        <th>병역관계</th><td colspan="2">${app.milType}</td>
					        <th>계급(병과)</th><td colspan="2">${app.milRank}</td>
					    </tr>
					    <tr>
					        <th>복무기간</th><td colspan="5">${app.milStartDate}-${app.milEndDate}</td>
					    </tr>
					    <tr>
					        <th>보훈대상여부</th><td colspan="2">${app.veteranYn}</td>
					        <th>장애인여부</th><td colspan="2">${app.disabledYn}</td>
					    </tr>
					</table>
					<div>학력사항</div>
					<c:forEach var="edu" items="${eduList}">
						<table class="appDetail_table" id="appDetail_table3">
							 <tr> 
						        <th>구분</th><td colspan="2">${edu.eduType}</td>
						        <th>국가</th><td colspan="2">${edu.countryCd}</td>
						    </tr>
						    <tr>  
						        <th>학교명</th><td colspan="5">${edu.schoolNm}</td> 
						    </tr>
						    <tr> 
						        <th>기간</th><td colspan="2">${edu.eduStartDate}~${edu.eduEndDate}</td> 
						        <th>취득구분</th><td colspan="2">${edu.acqType}</td>
						    </tr>
						    <tr>
						        <th>학과</th><td colspan="2">${edu.major}</td>
						        <th>세부전공</th><td colspan="2">${edu.majorDetail}</td>
						    </tr>
						     <tr>  
						        <th>학위명</th><td colspan="2">${edu.degreeNm}</td>
						        <th>평균학점</th><td colspan="2">${edu.gpa}</td>
						    </tr>
						</table>
					</c:forEach>
					<div>경력사항</div>
					<c:forEach var="career" items="${careerList}">
						<table class="appDetail_table" id="appDetail_table4">
							 <tr> 
						        <th>기간 ${career.employedYn}</th>
						        <td colspan="2">
						        	<c:choose>
							        	<c:when test="${not empty career.workEndDate}">
										      ${career.workStartDate} ~ ${career.workEndDate}
										</c:when>
										<c:otherwise>
										    ${career.workStartDate} ~ ${career.employedYn}
										</c:otherwise>
									</c:choose>
					        	</td>
						        <th>근무년수</th><td colspan="2">${career.workPeriod}</td>
						    </tr>
						    <tr> 
						        <th>근무기관(처)</th><td colspan="2">${career.companyNm}</td> 
						        <th>직급/직위</th><td colspan="2">${career.position}</td>
						    </tr>
						    <tr>  
						        <th>담당업무</th><td colspan="5">${career.taskDesc}</td> 
						    </tr> 
						</table>
					</c:forEach>	
					<div>수상 및 자격증</div>
					<c:forEach var="award" items="${awardList}">
						<table class="appDetail_table" id="appDetail_table5">
							 <tr> 
						        <th>구분</th><td colspan="2">${award.awardType}</td>
						        <th>취득년월일</th><td colspan="2">${award.issueDate}</td>
						    </tr>
						    <tr>  
						        <th>수상명, 자격증, 면허이름</th><td colspan="5">${award.awardNm}</td> 
						    </tr> 
						    <tr> 
						        <th>내용 또는 종별</th><td colspan="2">${award.awardDesc}</td> 
						        <th>발급(행)기관/시행기관</th><td colspan="2">${award.issuer}</td>
						    </tr>
						</table>
					</c:forEach>
					<div>자기소개서</div>
					<pre >${selfIntro.introCnts}</pre>
					<div>추천인</div>
					<c:forEach var="rec" items="${recList}">
						<table class="appDetail_table" id="appDetail_table6">
							 <tr> 
						        <th>성명</th><td colspan="2">${rec.nm}</td>
						        <th>소속</th><td colspan="2">${rec.org}</td>
						    </tr>
						    <tr> 
						        <th>직급</th><td colspan="2">${rec.position}</td> 
						        <th>관계</th><td colspan="2">${rec.relation}</td>
						    </tr>
						    <tr>  
						        <th>비고</th><td colspan="5">${rec.remarks}</td> 
						    </tr> 
						</table>
					</c:forEach> 
				</div>
			</div>
		</div>
	</div>
</body>
<script>   
/* 줌인아웃 */
window.onload = function() {
	updatePageInfo();
	
	const element = document.querySelector('.zoom-target');
	const container = document.querySelector('.zoom-wrapper');
	const panzoom = Panzoom(element, {
		zoomSpeed: 0.01,
		minScale: 0.7,
		maxScale: 2.0,
		transformOrigin: 'top center',
		disablePan: true,
		disableDrag: true
	});
	
	// Panzoom의 확대/축소 동작에 따라 위치를 조정
	element.addEventListener('panzoomzoom', function(event) {
		const scale = event.detail.scale;
		const container = event.target;
		if (scale < 1) {
			container.style.transformOrigin = 'top center';
	    	container.style.overflow  = 'hidden'; 
	    	document.documentElement.classList.remove('hide-scrollbar'); 
		} else if (scale > 1) {
			container.style.transformOrigin = 'top center';
			container.style.overflow  = 'auto';  
			document.documentElement.classList.add('hide-scrollbar'); 
		} else {
			document.documentElement.classList.remove('hide-scrollbar'); 
		}
	});

  	// 버튼 이벤트 연결
	document.getElementById('zoom-in-btn').addEventListener('click', () => {
		panzoom.zoomIn();
		updateZoomDisplay(panzoom);
		updatePageInfo()
	});

	document.getElementById('zoom-out-btn').addEventListener('click', () => {
		panzoom.zoomOut();
		updateZoomDisplay(panzoom);
	});
	
	document.getElementById('zoom-reset-btn').addEventListener('click', () => { 
	 	panzoom.zoom(1);
	  	updateZoomDisplay(panzoom);
	  	document.documentElement.classList.remove('hide-scrollbar'); 
	}); 

	function updateZoomDisplay(pz) {
		if (pz) {
		    const scale = (pz.getScale() * 100).toFixed(0);
		    document.getElementById("zoomValue").innerText = scale + "%";
		  }
	} 
	// 초기값 반영
	updateZoomDisplay(panzoom);
	
	element.style.touchAction = 'none';   // 드래그와 팬 동작을 막음
    element.style.pointerEvents = 'none'; // 포인터 이벤트 비활성화
    container.style.overflow  = 'auto'; 
};

/* pdf로 저장 */
function downloadPDF() {
	const element = document.querySelector(".appDetail-container");
	
	html2pdf()
		.from(element)
		.set({
			margin : 1, 													// 여백 1인치로 설정
			filename: "${app.nm} 지원서",										// 다운로드되는 PDF 파일 이름 지정
			html2canvas : {scale : 2},										// 해상도 2배로 더 선명하게
			jsPDF: { orientation: "portrait", unit: "pt", format: "a4" },	// 페이지 방향을 세로로 설정 (가로는 landscape) 
			pagebreak: { mode: ['css', 'legacy'] }
		})
		.save();
}

/* pdf로 인쇄 */
function printPDF() {
    window.print();
}

/* 페이지 표시 */ 
const PAGE_HEIGHT = 1000;

function updatePageInfo() {
	  const scrollTop = window.scrollY || document.documentElement.scrollTop;
	  const totalHeight = document.documentElement.scrollHeight;
	  
	  const totalPages = Math.ceil(totalHeight / PAGE_HEIGHT);
	  const currentPage = Math.min(Math.ceil((scrollTop + 1) / PAGE_HEIGHT), totalPages);  

	  document.getElementById('pageNumber').innerText = currentPage;
	  document.getElementById('totalPages').innerText = totalPages;
	}


// 스크롤될 때마다 페이지 표시 업데이트
window.addEventListener('scroll', updatePageInfo); 

</script>
</html>
 