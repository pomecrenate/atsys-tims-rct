<%--
////////////////////////////////////////////////////////////
// 프로그램명 : Eval1201p1.jsp
// 설명 : (불)합격자알림문구설정팝업
// 작성자 : 백세진
// 일자 : 2025.04.14
////////////////////////////////////////////////////////////
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   

 <div> 
    <div class="description">
      ※ 알림문구 제목을 클릭하시면 상세 내용을 확인할 수 있습니다. 
    </div> 
    <table class="table">
		<thead>
			<tr> 
				<th>NO</th>
				<th>알림문구 제목</th>
				<th>알림문구 내용</th>
				<th>선택</th> 
			</tr>
		</thead>
		<tbody> 
            <tr> 
				<td>1</td>
				<td>1차 합격안내</td>
				<td>[OO대학교 신규채용 결과안내]...</td>
				<td>  <button type="button" class="n_btn btn_md btn_c02">선택</button></td> 
            </tr> 
		</tbody>
	</table> 
	 <table class="detail_table" id="detail_table">
    	<colgroup>
			<col style="width:120px">
			<col style="width:auto">
		</colgroup>
      <tr>
        <th>알림문구 제목</th>
        <td>
          1차 합격안내
        </td>
      </tr>
      <tr>
        <th>알림문구 내용</th>
       <td>
		  <pre>
[OO대학교 신규채용 결과안내]
안내사항 : [@채용공고명] 결과안내
@(이름) 선생님께
우리대학교가 정한 소정의 심사 절차에 의거 선생님께서 @(합격심사)에 합격되었음을 알려드리며, 
@(해당심사)에 관련된 일정을 다음과 같이 알려드립니다.

1. 심사일시 : @(일시) (10분전 도착 요망)
2. 심사장소 : [개인별 입력] 
3. 심사방법 : [개인별 입력]
   ※ 문의처 : [@(부서) (@(전화번호))] 
4. 제출서류 원본은 세미나 당일 지참하여 제출(공고문 참조)
명지대학교 교육지원처장 

▶ 본 알림톡은 OO대학교 [@(부서) (@(전화번호))] 에서 발송되었습니다.
		  </pre>
		</td> 
      </tr>
    </table>
</div>