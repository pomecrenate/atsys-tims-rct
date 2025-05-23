<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Rct1101e.jsp
	// 설명 : 공고관리-초빙(임용)분야
	// 작성자 : 최연재
	// 일자 : 2025.04.21
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/rct1101e"></c:set>   
<c:set var="svnmSearch" value="${svnm}/search" />
<c:set var="svnmSave" value="${svnm}/save" />
<c:set var="svnmCheckNtc" value="${svnm}/checkNtc"></c:set>   
<c:set var="svnmCheckApp" value="${svnm}/checkApp" />
<c:set var="svnmAddField" value="${svnm}/addField"></c:set>   
<c:set var="svnmDeleteField" value="${svnm}/deleteField"></c:set>   
<c:set var="svnmSearchNtcPop" value="${svnm}/popup/searchNtcPop"></c:set>   
<c:set var="svnmSearchDeptPop" value="${svnm}/popup/searchDeptPop"></c:set>   

 <div class="x_panel_wrap">	 
	<div class="list_top">
		<div class="rgt-area">
			<button data-url="${svnmSearchNtcPop}" type="button" class="n_btn btn_md btn_c05">공고불러오기</button>
			<button class="n_btn btn_md btn_c02" data-action="moveTab" data-url="/rct1100e" type="button">돌아가기</button>
		</div>
	</div>
	<div class="btn_tab">
		<ul>			
			<li class="active"><button data-action="moveTab" type="button" data-url="/rct1101e/${ntcCd}">초빙(임용)분야</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1102e/${ntcCd}">지원자격</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1103e/${ntcCd}">유의사항</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1104e/${ntcCd}">지원방법</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1105e/${ntcCd}">제출서류</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1106e/${ntcCd}">전형절차</button></li>
			<li><button data-action="moveTab" type="button" data-url="/rct1107e/${ntcCd}">기타및서식</button></li>
		</ul>			
	</div>	
	<div class="x_panel">
		<div class="x_content">		
			<div class="content_list" id="content_list">
				<form method="POST" class="form-horizontal form-label-left" id="insert" name="insert" >
					<table class="table" id="field-table">
						<div class="list_top">
							<div class="rgt-area">
								<div class="btn-group">
									<button data-action="insert" type="button" class="n_btn btn_md btn_c03" id="isrtBtn"><i class="fas fa-plus"></i> 신규</button>
									<button data-action="save" type="button" class="n_btn btn_md btn_c01" id="saveBtn"><i class="fas fa-check"></i> 저장</button>
									<button data-action="remove" type="button" class="n_btn btn_md btn_c05" id="deleteBtn">삭제</button>
									<button data-action="addField" type="button" class="n_btn btn_md btn_c02" id="addBtn" hidden><i class="fas fa-check"></i> 저장</button>
								</div>
							</div>
							<div class="lft-area">
								<button data-action="reload" type="button" class="n_btn btn_md btn_c04" id="reload" hidden><i class="fas fa-redo"></i></button>
							</div>
						</div>
						<div class="description"> ※ 리스트를 클릭하시면 상세 내역 확인 및 수정가능합니다.</div>
						<thead>
							<tr>
								<th><input type="checkbox" value=""></th>
								<th>대학명</th>
								<th>학과(부)명</th>
								<th>교직원구분</th>
								<th>전공분야(교)<br>관련분야(직)</th>
								<th>담당과목(교)<br>우대사항(직)</th>	
								<th>채용예정인원수</th>
								<th>비고(지원자격)</th>
							</tr>
						</thead>
						<tbody id="field-tbody"> 
						</tbody>
					</table> 
					<div class="x_pagination"> 
					</div> 
				</form>
			</div>
		</div> 
	</div>
	<div class="x_panel">
		<form method="POST" id="detail" name="detail">		
			<p class="detail_title">상세내용</p>
			<table class="view_table" id="detail-table">
				<input type="hidden" id="deptCd" name="deptCd" />
				<input type="hidden" id="collegeCd" name="collegeCd" />
				<input type="hidden" id="fieldCd" name="fieldCd" />
				<caption>상세내용</caption>
				<colgroup>
					<col style="width:120px">
					<col style="width:auto">
					<col style="width:120px">
					<col style="width:auto">
					<col style="width:120px">
					<col style="width:auto">
				</colgroup>
				<tbody id="detail-tbody">
					<tr>
						<th><label for="collegeNm">대학명</label></th>
						<td>
							<input type="text" class="form-control" id="collegeNm" name="collegeNm" maxlength="200" required="required" readonly/>
						</td>
						<th><label for="deptNm">학과(부)명</label></th>
						<td>
							<div class="input-wrapper">
								<input type="text" class="form-control" id="deptNm" name="deptNm" maxlength="50" required="required" readonly/>
								<button type="button" data-url="${svnmSearchDeptPop}"><i class="fa fa-search"></i></button>
							</div>
						</td>
						</div>
						<th><label for="staffType">교직원구분</label></th>
						<td>
							<select class="form-control" id="staffType" name="staffType" maxlength="80" required="required" />							
						</td>
					</tr>
					<tr>
						<th><label for="majorNm">전공분야(교)<br>관련분야(직)</label></th>
						<td>
							<input type="text" class="form-control" id="majorNm" name="majorNm" required="required"/>
						</td>
						<th><label for="assignedSubject">담당과목(교)<br>우대사항(직)</label></th>
						<td>
							<input type="text" class="form-control" id="assignedSubject" name="assignedSubject" required="required"/>
						</td>
						<th><label for="requiredCnt">채용예정인원수</label></th>
						<td>
							<input type="text" class="form-control" id="requiredCnt" name="requiredCnt" maxlength="300" />
						</td>
					</tr>
					<tr>
						<th><label for="requirements">비고(지원자격)</label></th>
						<td colspan="3">
							<input type="text" class="form-control" id="requirements" name="requirements" maxlength="300" />
						</td>
					</tr>					
				</tbody>
			</table>
		</form>
	</div>
</div>


<script>  
	const tbColNms = [
		  { type: "checkbox"},
		  { type: "text", name: "collegeNm", id: "collegeNm", readonly: true },
		  { type: "hidden", name: "collegeCd", dataField: "collegeCd"},
		  { name: "deptNm", id: "deptNm", readonly: true, addButton: true, url: "${svnmSearchDeptPop}", iclass: "fa fa-search" },
		  { type: "hidden", name: "deptCd", dataField: "deptCd"},
		  { type: "select", name: "staffType", id: "staffType"}, 
		  { type: "hidden", name: "staffTypeCd", dataField: "staffTypeCd"},
		  { type: "text", name: "majorNm" },
		  { type: "textarea", name: "assignedSubject" },
		  { type: "text", name: "requiredCnt" },
		  { type: "textarea", name: "requirements" },
	];
	
	let ntcType = 0;
	
	$(document).ready(function() { 		
		const param = {"ntcCd" : "${ntcCd}"};
		const ajax = TimsUtil.getObject("${svnmSearch}", param, true);
		const data = ajax.list; 
		const paginationInfo = ajax.paginationInfo;  
		
	    RctUtil.renderTable("field-tbody", tbColNms, data, (event) => {onClickRow(event)}, paginationInfo, "fieldCd"); 
	    
	    const ntcCd = {"ntcCd" : "${ntcCd}"};
		ntcType = TimsUtil.getObject("${svnmCheckNtc}", ntcCd, true);
		
		if(ntcType == 40){
			setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT005"}, "detail", "staffType", {type:"", selectAll:"N", multiYn:"N"});
		} else {
			setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT004"}, "detail", "staffType", {type:"", selectAll:"N", multiYn:"N"});
		}	
	});
	
	function save() {
		const param = TimsUtil.formToMap("detail");
		
		if(!param["deptCd"] || param["deptCd"].trim() === "") {
			alert("대학과 학과명을 선택해주세요")
			return;
		}
		
		if(!param["staffType"] || param["staffType"].trim() === "") {
			alert("교직원구분을 선택해주세요")
			return;
		}
		
		if(!param["majorNm"] || param["majorNm"].trim() === "") {
			alert("전공분야(관련분야)를 입력해주세요")
			return;
		}
		
		if(!param["requiredCnt"] || param["requiredCnt"].trim() === "") {
			alert("채용예정인원수를 입력해주세요")
			return;
		}
		
		param["ntcCd"] = "${ntcCd}";
		
		const fieldCd = $('#fieldCd').val();
		if(fieldCd == null || fieldCd == '') {
			param["sStatus"] = "I";
		} else {
			param["sStatus"] = "U";
		}
		
		console.log(param);

  		const ajax = TimsUtil.getObject("${svnmSave}", param, false);
		if(ajax == 1) {
			alert("저장되었습니다.");
			location.reload();
		} else {
			alert("오류가 발생했습니다.")
		} 
	};
	
	function insert() {
		$("form#detail")[0].reset();
		$('.detail_title').text('추가');
		$("#staffType").multiselect('destroy');
		
		const param = {"ntcCd" : "${ntcCd}"};
		ntcType = TimsUtil.getObject("${svnmCheckNtc}", param, true);
		
		if(ntcType == 40){
			setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT005"}, "detail", "staffType", {type:"", selectAll:"N", multiYn:"N"});
		} else {
			setMultiSelect("", {cls: "CMM_1", id: "ccStaffType", cdg : "RCT004"}, "detail", "staffType", {type:"", selectAll:"N", multiYn:"N"});
		}	
	 };
	 
	 function addField() {
		let dataIds = $('#field-table tr[data-id]').has('input[type="checkbox"]:checked').map(function() {
			return $(this).data('id');
		}).get();
		
		const count = dataIds.length;
		const param = {
				"ids" : dataIds, 
				"ntcCd" : "${ntcCd}"
		};

		const ajax = TimsUtil.getObject("${svnmAddField}", param, false);
		
		if(ajax == count) {
			alert(ajax + "건이 저장되었습니다.")
			location.reload();
		} else {
			alert("오류가 발생했습니다.")
		}
	 }
	 
	 function reload(){
		location.reload();
	 }
	 
	 function remove() {
		 
		 let dataIds = $('#field-table tr[data-id]').has('input[type="checkbox"]:checked').map(function() {
			 return $(this).data('id');
		 }).get();
		 
		 const count = dataIds.length;

         if (count == 0) {
             alert("삭제할 초빙분야를 선택해주세요.");
             return;
         }
         
		 const param = {"ids" : dataIds};
         const app = TimsUtil.getObject("${svnmCheckApp}", param, true);
         
         if(app > 0) {
         	alert("이미 지원서가 존재하는 초빙분야는 삭제할 수 없습니다.");
         	return;
         } 
	 
		 if(confirm(count + "건을 정말 삭제하시겠습니까?")){			 
			 			 
			 const ajax = TimsUtil.getObject("${svnmDeleteField}", param, false);
			 
			 if(ajax == 0) {
				 alert("오류가 발생했습니다.")
			 } else {
				 alert(ajax + "건이 삭제되었습니다.");
				 location.reload();
			 } 
			 
		 } else {
			 return;
		 }
		 
	 }
	 
	 function onClickRow(event) {

		 if ($(event.target).is('input[type="checkbox"]')) {
		    return;
		  }
		 $('.detail_title').text('상세내용');
		 const tr = event.target.closest("tr");
 
		 const fieldCd = $(tr).data('id'); 
		 $('#fieldCd').val(fieldCd);
		 
		 const rowData = Array.from(tr.querySelectorAll("td")).map(td =>
		      td.textContent.trim()
		 );
		 
		 $('#collegeCd').val($(tr).find('td[data-field="collegeCd"]').text());
		 $('#deptCd').val($(tr).find('td[data-field="deptCd"]').text());
	 
		 tbColNms.forEach((col, i) => {
			  if (!col.name || col.type === 'hidden') return;

			  const $el = $('#detail-table [id="' + col.name + '"]');
			  const value = rowData[i];
	
			  $el.val(value);

		 });
		 const $select = $("#detail-table [id='staffType']");
		 const staffTypeCd = $(tr).find('td[data-field="staffTypeCd"]').text().trim();
		 
		 $select.val(staffTypeCd);
		 $select.multiselect('refresh');
	 }
	  
	$(document).on('click', 'button[data-url="${svnmSearchDeptPop}"]', function() {
		const url = $(this).attr('data-url');
		if (url) {
			RctUtil.urlDialog('부서검색', url, 800, 500);  
			searchDept();
		}
	}); 
	
	$(document).on('click', 'button[data-url="${svnmSearchNtcPop}"]', function() {
		const url = $(this).data('url');
		if (url) {
			RctUtil.urlDialog('공고검색', url, 800, 500);  
			init();
		}
	}); 
	
	$(document).on('click', 'button[data-action="moveTab"]', function() {
	  const url = "${serviceBathPath}" + $(this).data('url');
	  if (url) {
	    location.href = url;
	  }
	});
	
</script>

