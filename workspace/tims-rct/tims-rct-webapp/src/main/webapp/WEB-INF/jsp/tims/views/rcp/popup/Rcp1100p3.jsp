<%--
////////////////////////////////////////////////////////////
// 프로그램명 : Rcp1100p3.jsp
// 설명 : 첨부파일조회(팝업)
// 작성자 : 백세진
// 일자 : 2025.04.09
////////////////////////////////////////////////////////////
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   

 <div> 
    <div class="description">
      ※ 파일명을 클릭하시면 파일을 다운로드 받을 수 있습니다. 
    </div> 
    <input type="hidden" name="ntcCd" value="ntcCd"/>  
    <table class="detail_table" id="detail_table">
    	<colgroup>
			<col style="width:120px">
			<col style="width:auto">
		</colgroup>
      <tr>
        <th>지원자성명</th>
        <td id="appNm"></td> 
        <th>핸드폰번호</th>
        <td id="appMobPhone"></td>
      </tr> 
    </table>
    <table class="table" id="docList-table">
		<thead>
			<tr> 
				<th>순번</th>
				<th>서식명</th>
				<th>필수여부</th> 
				<th>파일</th> 
			</tr>
		</thead>
		<tbody id="docList-tbody"> 
		</tbody>
	</table> 
</div>

<script>
const tbColNms = [
		 { type: "text", name: "docSeq", dataField : "docSeq" },
		 { type: "text", name: "docDesc", dataField : "docDesc" },
		 { type: "text", name: "requiredYn", dataField : "requiredYn" },
		 { type: "text", name: "saveFileNm", dataField : "saveFileNm" } 
];


$('#timsdlg').on('shown.bs.modal', function () { 
	 selectAppNmAndMob(); 
	 
	 /* 첨부파일 서식 목록 조회 */ 
	 var url = "${serviceBathPath}/rcp1100e/selectNtcDocList"; 
	 var ntcCd = $('input[name="ntcCd"]').val();
	 var params = { 'ntcCd' : ntcCd }; 
	 
	 RctUtil.loadPageData(url, params, "", "docList-tbody", tbColNms);
	 
	 selectUpldFileList();
});

/* 지원자 기본정보 세팅 */
function selectAppNmAndMob() {
	var url = "${serviceBathPath}/rcp1100e/selectAppNmAndMob";
	var params = { 'appCd' : '${appCd}' };
	
	const result = TimsUtil.getObject(url, params, true);   
	$('#appNm').text(result.nm);
	$('#appMobPhone').text(result.mobPhone); 
	$('input[name="ntcCd"]').val(result.ntcCd);
}

/* 지원자 첨부파일 목록 세팅 */
function selectUpldFileList() {
	var url = "${serviceBathPath}/rcp1100e/selectUpldFileList";
	var params = { 'appCd' : '${appCd}' };
	
	const result = TimsUtil.getObject(url, params, true);    
	
	if (result && result.list) {
		result.list.forEach(file => {
			const docSeq = file.fileSeq;
			const fileName = file.saveFileNm;
			const filePath = file.savePath;
			
			 const $td = $(`td[data-field="docSeq"]`).filter(function () {
	                return $(this).text().trim() === String(docSeq);
	            }).closest("tr").find(`td[data-field="saveFileNm"]`);
 
			 const fullPath = "/edata" + filePath;
	            const $link = $(`<a></a>`)
	                .attr("href", fullPath)
	                .attr("download", fileName)
	                .text(fileName);

	            $td.empty().append($link);
		});
	} else {
		console.log("지원자 첨부파일 목록 조회 실패");
	}
} 

</script>