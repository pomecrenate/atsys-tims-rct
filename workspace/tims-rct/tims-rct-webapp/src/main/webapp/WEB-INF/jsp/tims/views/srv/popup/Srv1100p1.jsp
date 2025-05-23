<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Srv1100p1.jsp
	// 설명 : 공지사항등록팝업
	// 작성자 : 최연재
	// 일자 : 2025.04.28
	// 이력 :  
	//////////////////////////////////////////////////////////// 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   

 <div> 
	<div class="list_top">
	    <div class="rgt-area btn_group">
	      <button type="button" class="n_btn btn_md btn_c01" data-action="save">저장</button>
	    </div>
    </div>
    <div class="description">
      ※ 내용을 입력 후 상단의 [저장] 버튼을 클릭하시면 됩니다.
    </div>
    <form method="POST" class="form-horizontal form-label-left" id="search" name="search" > 
	    <%-- <input type="hidden" name="${loginUser.getCsrfTokenId()}" value="${loginUser.getCsrfToken()}"/> --%>
	    <table class="detail_table" id="detail_table">
	    	<colgroup>
				<col style="width:120px">
				<col style="width:auto">
			</colgroup>
	      <tr>
	        <th>제목</th>
	        <td>
	        	<input class="form-control" id="qryPostTitle" name="qryPostTitle" required />
	        </td>
	      </tr>
	      <tr>
	        <th>중요여부</th>
	        <td>
	        	<select class="form-control" id="qryImportantYn" name="qryImportantYn" required>
	        		<option value="">선택</option>
	        		<option value="Y">중요</option>
	        		<option value="N">일반</option>
	        	</select>
	        </td>
	      </tr>
	      <tr>
	      	<th>글내용</th>
	      	<td id="editor"></td>
	      </tr>
	      <tr>
	      	<th>첨부파일</th>
	      	<td>
	      		<input type="file" />
	      	</td>
	      </tr>
	    </table>
    </form>
</div>

<script>
const editor = new toastui.Editor({
	el: document.querySelector('#editor'),
	height: '300px',
	autofocus : false,
	initialEditType: 'wysiwyg',
	placeholder: '공지사항 내용을 적어주세요',
	previewStyle: 'tab',
	hooks : {
			addImageBlobHook : function(blob, callback){
				var formData = new FormData();
				formData.append('image', blob);
				
				$.ajax({
					url : '${svnmFileUpload}/uploadBbsPstImgFile',
					enctype: 'multipart/form-data', 
					type: 'POST',
					data: formData,
					dataType: 'json',
					processData: false,
					contentType: false,
					async: false,
					success : function(data){
						if(data){
							if(data.fail){
								alert('파일 형식이 올바르지 않습니다.');
								return false;
							}
							else{
								callback(data.returnUrl,'image');
							}
						}
					}
				});
			}
		},
		events : {
			change : function(){
			}
		}
	});
</script>