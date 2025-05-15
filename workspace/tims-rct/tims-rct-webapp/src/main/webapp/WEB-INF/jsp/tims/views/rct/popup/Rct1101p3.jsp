<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Rct1101p3.jsp
	// 설명 : 초빙(임용)분야 상세보기 팝업
	// 작성자 : 최연재
	// 일자 : 2025.05.14
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   

 <div>  
 	<form method="POST" class="form-horizontal form-label-left" id="searchNtc" name="searchNtc" > 
 		<div class="list_top"> 
			<div class="rgt-area">
				<div class="btn-group">
					<button onclick="searchNtcList()" type="button" class="n_btn btn_md btn_c01" id="searchBtn">조회</button>
				</div>
			</div>
		</div>
	    <table class="detail_table margin_bottom_15" id="detail_table">
	    	<colgroup>
				<col style="width:120px">
				<col style="width:auto">
				<col style="width:120px">
				<col style="width:auto">
			</colgroup>
	      <tr>
	        <th>채용년도</th>
	        <td> 
	        	<select class="form-control" id="qryNtcYear" name="qryNtcYear" required></select>
	        </td>
	        <th>학기</th>
	        <td> 
	        	<select class="form-control" id="qryNtcSmt" name="qryNtcSmt" required></select>
	        </td>
	      </tr>
	      <tr>
	        <th>채용구분</th>
	        <td>
	        	<select class="form-control" id="qryNtcType" name="qryNtcType" required></select>
	        </td>
	      </tr>
    </table>
   </form>
   <div class="x_panel">
	   <table class="table" id="ntc-table">
	    	<thead>
				<tr>
					<th>채용명</th>
					<th>채용구분</th>
					<th>복사</th>
				</tr>
			</thead>
			<tbody id="ntc-tbody"> 
			</tbody>
	    </table>
    </div>
</div>

<script>


</script>