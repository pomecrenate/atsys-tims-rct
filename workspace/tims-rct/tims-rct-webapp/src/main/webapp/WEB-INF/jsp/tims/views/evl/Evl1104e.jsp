<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1103e.jsp
	// 설명 : 심사관리
	// 작성자 : 이예찬
	// 일자 : 2025.05.15
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1103e"/>    
<c:set var="svnmSearch" value="${svnm}/search"/>    
<c:set var="svnmGetApp" value="${svnm}/getApplicant"/>  
<c:set var="svnmGetSheet" value="${svnm}/getSheet"/>
<c:set var="svnmConfirmResult" value="${svnm}/confirmResult"/>
<c:set var="svnmCancelConfirmResult" value="${svnm}/cancelConfirmResult"/>
<c:set var="svnmGetResultCd" value="${svnm}/getResultCd"/>

<div class="x_panel_wrap">  
	<div class="x_panel">		
		<div class="search_condition"> 
			<div class="condition_list" style="display: flex; align-items: center; gap: 10px;">
				<dl style="margin-bottom: 0;">
					<dt><label>채용공고 차수</label></dt>
					<dd><div id="step"></div></dd>
				</dl>
				<dl style="margin-bottom: 0;">
					<dt><label>채용구분</label></dt>
					<dd><div id="ntcTypeNm"></div></dd>
				</dl> 
				<dl style="margin-bottom: 0;">
					<dt><label for="usrCd">평가위원</label></dt>
					<dd><select class="form-control" id="usrCd" name="usrCd"></select></dd>
				</dl>
				<button class="n_btn btn_md btn_c02" data-url="evl1100e" type="button" style="margin-left: auto;">돌아가기</button>
			</div>
		</div>
	</div> 
	
	<div class="x_content">
		<div class="description">※ 지원자 성명을 클릭하시면 지원서를 확인할 수 있습니다.</div>
		<div class="description">※ 모든 평가 항목에 점수를 입력한 후 확정 버튼을 클릭하시면 평가가 완료됩니다.</div>
		<div class="description">※ 확정 후에는 수정이 불가능합니다.</div>
		
		<div class="row">
			<div class="col-md-5">
				<div class="list_top">
					<div class="lft-area">
						<p class="list_title">지원자 목록</p>
					</div>
				</div>
				<div class="content_list">
					<table class="table" id="applicant-table">
						<thead>
							<tr>
								<th>성명</th>
								<th>지원분야</th>
								<th>심사여부</th>
							</tr>
						</thead>
						<tbody id="app-tbody"></tbody>
					</table>
				</div>
			</div>
			
			<div class="col-md-7">
				<div class="list_top">
					<div class="lft-area">
						<p class="list_title">평가표</p>
					</div>
					<div class="rgt-area">
						<div class="btn-group">
							<button id="btn-confirm" data-action="confirmResult" type="button" class="n_btn btn_md btn_c02">확정</button>
							<button id="btn-cancel-confirm" data-action="cancelConfirmResult" type="button" class="n_btn btn_md btn_c04" style="display:none;">확정취소</button>
						</div>
					</div>
				</div>
				<div class="content_list">
					<input type="hidden" id="ntcCd" name="ntcCd" />
					<input type="hidden" id="stepCd" name="stepCd" />
					<input type="hidden" id="appCd" name="appCd" />
					<input type="hidden" id="sheetCd" name="sheetCd" />
					<input type="hidden" id="resultCd" name="resultCd" />
					<input type="hidden" id="staffType" name="staffType" />
					
					<table class="table" id="sheet-table">
						<thead>
							<tr>
								<th>심사구분</th>
								<th>항목</th>
								<th>배점</th>
								<th>평가지표</th>
								<th>세부배점</th>
								<th>점수 기준</th>
								<th>평가 점수</th>
							</tr> 
						</thead>
						<tbody id="sheet-tbody"></tbody>
					</table> 
					
					<div class="opinion_area" style="display:none;">
						<div class="form-group">
							<label for="opinion">소견서</label>
							<textarea class="form-control" id="opinion" name="opinion" rows="3"></textarea>
						</div>
						<div class="form-group" id="minutes-area" style="display:none;">
							<label for="minutes">회의록 (위원장만 작성가능)</label>
							<textarea class="form-control" id="minutes" name="minutes" rows="3"></textarea>
						</div>
					</div>
					
					<div class="total_score" style="display:none;">
						<span>총점: </span>
						<span id="total-score">0</span>
					</div>
				</div>
			</div>
		</div>
	</div> 
</div>

<script>
</script>