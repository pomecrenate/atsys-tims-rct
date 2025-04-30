<%--
////////////////////////////////////////////////////////////
// 프로그램명 : Rcp1100p2.jsp
// 설명 : 경력산정-직원전용(팝업)
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
      ※ 결재의견
    </div>
    <table class="detail_table" id="detail_table">
    	<colgroup>
			<col style="width:120px">
			<col style="width:auto">
		</colgroup>
      <tr>
        <th>경력인정기간</th>
        <td>
          <input type="text" class="form-control" id="month" placeholder="개월">
        </td>
      </tr>
      <tr>
        <th>경력인정사유</th>
        <td>
          <input type="text" class="form-control" id="reason" placeholder="사유 입력">
        </td>
      </tr>
    </table>
    <table class="table">
		<thead>
			<tr> 
				<th>기간</th>
				<th>근무기간(개월)</th>
				<th>근무처</th>
				<th>직급/직위</th>
				<th>담당업무</th> 
			</tr>
		</thead>
		<tbody> 
            <tr> 
				<td>2022-01-01~2024-08-31</td>
				<td>288</td>
				<td>감자밭</td>
				<td>사원</td>
				<td>감자캐기</td> 
            </tr> 
		</tbody>
	</table> 
</div>