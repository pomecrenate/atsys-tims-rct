<%--
////////////////////////////////////////////////////////////
// 프로그램명 : Eval1202p1.jsp
// 설명 : 심사일정 등록 및 수정 팝업
// 작성자 : 백세진
// 일자 : 2025.04.14
////////////////////////////////////////////////////////////
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
 <div> 
 	<div class="list_top">
	    <div class="rgt-area btn_group">
	      <button type="button" class="n_btn btn_md btn_c01" data-action="saveSchData">저장</button>
	    </div>  
    </div>
    <div class="description">
      ※ 수정할 내용을 입력 후 상단의 [저장] 버튼을 클릭하시면 됩니다.
    </div>
 	 <table class="table">
		<tr>
	        <th>지원자성명</th>
	        <td id="appNm"></td> 
	    </tr> 
	 </table> 
     <table class="detail_table" id="detail_table">
    	<colgroup>
			<col style="width:120px">
			<col style="width:auto">
		</colgroup> 
      <tr>
        <th>심사일시</th>
        <td>
          <input type="text" class="form-control" id="evalDateSch" placeholder="ex. 2025-03-31 15:30">
        </td>
      </tr>
      <tr>
        <th>심사장소</th>
        <td>
          <input type="text" class="form-control" id="evalPlaceSch" placeholder="ex. 세미나실 5층">
        </td>
      </tr>
      <tr>
        <th>심사방법</th>
        <td>
          <input type="text" class="form-control" id="evalMethodSch" placeholder="ex. 세미나 15분 + 면접 15분">
        </td>
      </tr>
      <tr>
        <th>담당부서</th>
        <td>
          <input type="text" class="form-control" id="deptName" placeholder="ex. 생명과학팀">
        </td>
      </tr>
      <tr>
        <th>심사일시</th>
        <td>
          <input type="text" class="form-control" id="deptPhone" placeholder="ex 02-123-4567">
        </td>
      </tr>
    </table>
</div>

<script>
/* 기초데이터 세팅 */
$('#timsdlg').on('shown.bs.modal', function () {  
	selectAppNm();
	selectEvalSchData();
});

/* 합격자명 세팅 */
function selectAppNm() {
	var url = "${serviceBathPath}/evl1202e/selectAppNm";
	var params = { 'appCd' : '${appCd}' };
	
	const result = TimsUtil.getObject(url, params, true);  
	$('#appNm').text(result.nm); 
} 

/* 심사일정 수정 및 저장 */
$(document).on("click", "button[data-action='saveSchData']", function() {  
	const evalDateSch = $("#evalDateSch").val();   
	const evalPlaceSch = $("#evalPlaceSch").val();   
	const evalMethodSch = $("#evalMethodSch").val();   
	const deptName = $("#deptName").val();   
	const deptPhone = $("#deptPhone").val();    
	
	var url = "${serviceBathPath}/evl1202e/saveEvalSch";
	var params = {
					'appCd' : '${appCd}',
					'stepCd' : '${stepCd}',
					'evalDateSch' : evalDateSch,
					'evalPlaceSch' : evalPlaceSch,
					'evalMethodSch' : evalMethodSch,
					'deptName' : deptName,
					'deptPhone' : deptPhone
				 } 
	
	const result = TimsUtil.getObject(url, params, true);  
	
	if (result) {
		alert("심사일정 저장 성공");
		selectEvalSchData();
		
    } else {
        alert("심사일정 저장 실패");
    }  
}); 

/* 심사일정 데이터 조회 */
function selectEvalSchData() {
	var url = "${serviceBathPath}/evl1202e/selectEvalSchData";
	var params = { 'appCd' : '${appCd}' , 'stepCd' : '${stepCd}' };
	
	const result = TimsUtil.getObject(url, params, true);  
	 
	if(result) {
		$("#evalDateSch").val(result.evalDateSch); 
		$("#evalPlaceSch").val(result.evalPlaceSch);
		$("#evalMethodSch").val(result.evalMethodSch);
		$("#deptName").val(result.deptName);
		$("#deptPhone").val(result.deptPhone);
	} else {
		console.log("심사일정 데이터 조회 실패");
	}
	
	
}
</script>