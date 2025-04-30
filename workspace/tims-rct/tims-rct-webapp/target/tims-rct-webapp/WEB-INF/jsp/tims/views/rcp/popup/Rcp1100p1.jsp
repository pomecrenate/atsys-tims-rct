<%--
////////////////////////////////////////////////////////////
// 프로그램명 : Rcp1100p1.jsp
// 설명 : 자격미달여부관리(팝업)
// 작성자 : 백세진
// 일자 : 2025.04.09
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
        <th>충족여부</th>
        <td>
          <select class="form-control" id="status">
            <option value="Y">충족</option>
            <option value="N">미충족</option>
          </select>
        </td>
      </tr>
      <tr>
        <th>사유</th>
        <td>
          <input type="text" class="form-control" id="reason" placeholder="사유 입력">
        </td>
      </tr>
    </table>
</div>
