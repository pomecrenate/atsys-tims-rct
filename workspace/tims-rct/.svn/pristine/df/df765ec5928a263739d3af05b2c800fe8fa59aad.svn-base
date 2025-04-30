<%--
////////////////////////////////////////////////////////////
// 프로그램명 : Sys1100p1.jsp
// 설명 : 코드관리(팝업)
// 작성자 : 최연재
// 일자 : 2025.04.16
////////////////////////////////////////////////////////////
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   

 <div>
 	<div class="list_top">
	    <div class="rgt-area btn_group">
	      <button type="button" class="n_btn btn_md btn_c01">저장</button>
	    </div>
    </div>
    <div class="description">
      ※ 수정할 내용을 입력 후 상단의 [수정저장] 버튼을 클릭하시면 됩니다.
    </div>
    <table class="detail_table" id="detail_table">
    	<colgroup>
			<col style="width:120px">
			<col style="width:auto">
		</colgroup>
      <tr>
        <th>대분류코드</th>
        <td>
			<input type="text" class="form-control" id="lrgCd"/>
        </td>
      </tr>
      <tr>
        <th>대분류코드명</th>
        <td>
        	<input type="text" class="form-control" id="lrgCdNm"/>
        </td>
      </tr>
      <tr>
      	<th>사용여부</th>
      	<td>
      		<input type="checkbox"/>
      	</td>
      </tr>
    </table>
</div>
<script>
window.addEventListener("message", (event) => {
	if (event.data.type === "ROW_DATA") {
	  const rowData = event.data.payload;
	  document.getElementById("lrgCd").value = rowData[1];
	  document.getElementById("lrgCdNm").value = rowData[2];
	}
});
</script>