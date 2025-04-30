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
 	 <table class="table">
		<tr>
	        <th>지원자성명</th>
	        <td>백세진</td> 
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
          <input type="text" class="form-control" id="reason" placeholder="ex. 2025-03-31">
        </td>
      </tr>
      <tr>
        <th>심사장소</th>
        <td>
          <input type="text" class="form-control" id="reason" placeholder="ex. 세미나실 5층">
        </td>
      </tr>
      <tr>
        <th>심사방법</th>
        <td>
          <input type="text" class="form-control" id="reason" placeholder="ex. 세미나 15분 + 면접 15분">
        </td>
      </tr>
      <tr>
        <th>담당부서</th>
        <td>
          <input type="text" class="form-control" id="reason" placeholder="ex. 생명과학팀">
        </td>
      </tr>
      <tr>
        <th>심사일시</th>
        <td>
          <input type="text" class="form-control" id="reason" placeholder="ex 02-123-4567">
        </td>
      </tr>
    </table>
</div>