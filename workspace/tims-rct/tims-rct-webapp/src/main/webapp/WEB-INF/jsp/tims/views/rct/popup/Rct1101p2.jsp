<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Rct1101p2.jsp
	// 설명 : 공고조회 팝업
	// 작성자 : 최연재
	// 일자 : 2025.05.13
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
const popTbColNms2 = [
	  { type: "text", name: "ntcNm" },
	  { type: "text", name: "ntcType" },
	  { type: "button", name: "ntcSelectBtn", btnText: "복사", dataAction: "ntcSelect" },
];

function init() {
	setMultiSelect("", {cls: "CMM_1", id: "ccNtcYear", cdg : "RCT081"}, "searchNtc", "qryNtcYear", {type:"", selectAll:"N", multiYn:"N"});
	setMultiSelect("", {cls: "CMM_1", id: "ccNtcSmt", cdg : "RCT001"}, "searchNtc", "qryNtcSmt", {type:"", selectAll:"N", multiYn:"N"});
	setMultiSelect("", {cls: "CMM_1", id: "ccNtcType", cdg : "RCT004"}, "searchNtc", "qryNtcType", {type:"", selectAll:"N", multiYn:"N"});
	setMultiSelect("", {cls: "CMM_1", id: "ccNtcType", cdg : "RCT005"}, "searchNtc", "qryNtcType", {type:"", selectAll:"N", multiYn:"N"});
	searchNtcList();
}

function searchNtcList() {
	const param = TimsUtil.formToMap('searchNtc');
 	const ajax = TimsUtil.getObject("${serviceBathPath}/rct1101e" + "/popup/searchNtc", param, true);
	const data = ajax.list; 
	const paginationInfo = ajax.paginationInfo;  
	
    RctUtil.renderTable("ntc-tbody", popTbColNms2, data, ()=>{}, paginationInfo, "ntcCd");  
}
$(document).off('click', 'button[data-action="ntcSelect"]')
.on('click', 'button[data-action="ntcSelect"]', function() {
	
	const ntcCd = $(this).closest('tr').data('id'); 
	const step = $(this).closest('tr').find('td').eq(2).text();
	const param = {	"ntcCd" : ntcCd,
					"step"  : step  };
	
	const ajax = TimsUtil.getObject("${serviceBathPath}/rct1101e" + "/search", param, true);
	const data = ajax.list; 
	const paginationInfo = ajax.paginationInfo;  
	
    RctUtil.renderTable("field-tbody", tbColNms, data, ()=>{}, paginationInfo, "fieldCd");  
    
	$('#timsdlg').modal("hide");
	
	alert("목록에서 추가하고자 하는 항목을 선택 후 저장을 눌러주세요 \n*저장하지않으면 현재 공고에 반영되지 않습니다")
	$('.description').text('※ 추가하려는 초빙(임용)분야를 선택 후 저장버튼을 눌러주세요');
	
	$('#isrtBtn').hide();
	$('#saveBtn').hide();
	$('#deleteBtn').hide();
	$('#addBtn').show();
	$('#reload').show();
});



</script>