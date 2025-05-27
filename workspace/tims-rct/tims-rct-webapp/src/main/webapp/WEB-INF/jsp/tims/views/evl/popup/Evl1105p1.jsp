<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : Evl1105p1.jsp
	// 설명 : 평가 결과표 확인(팝업)
	// 작성자 : 이예찬
	// 일자 : 2025.05.25
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/evl1105e"/>    
<c:set var="svnmGetResult" value="${svnm}/getResultDetail"/>    

<div>
    <div class="list_top">
        <div class="lft-area">
            <div class="search_condition">
                <div class="condition_list">
                    <dl>
                        <dt><label>평가위원</label></dt>
                        <dd><div id="jdgNm"></div></dd>
                    </dl>
                    <dl>
                        <dt><label>지원자</label></dt>
                        <dd><div id="appNm"></div></dd>
                    </dl>
                    <dl>
                        <dt><label>지원분야</label></dt>
                        <dd><div id="fieldInfo"></div></dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>

    <div class="description">
        ※ 평가위원이 입력한 평가표 정보입니다.
    </div>

    <table class="table" id="result-table">
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
        <tbody id="result-tbody"></tbody>
    </table>
    
    <div class="content_list" style="margin-top: 20px;">
        <div class="form-group">
            <label for="opinion">소견서</label>
            <textarea class="form-control" id="opinion" name="opinion" rows="3" readonly></textarea>
        </div>
        <div class="form-group" id="minutes-area">
            <label for="minutes">회의록</label>
            <textarea class="form-control" id="minutes" name="minutes" rows="3" readonly></textarea>
        </div>
    </div>
    
    <div class="total_score" style="margin-top: 10px; font-weight: bold; text-align: right;">
        <span>총점: </span>
        <span id="total-score">0</span>
    </div>
</div>

<script>
// 테이블 컬럼 정의
const resultColumns = [
    { type: "text", name: "qstTypeNm" },
    { type: "text", name: "qstNm" },
    { type: "text", name: "qstScore" },
    { type: "text", name: "indNm" },
    { type: "text", name: "indScore" },
    { type: "text", name: "scoreCriteria" },
    { type: "text", name: "resultScore" }
];

$(document).ready(function() {
    // 결과 데이터 로드
    loadResultData();
});

// 결과 데이터 로드
function loadResultData() {
    // 부모 창에서 resultCd 가져오기
    let resultCd;
    
	resultCd = parent.window.selectedResultCd;
    
    if (!resultCd) {
        alert("평가 결과 정보가 없습니다.");
        if (parent) {
            TimsUtil.closeDialog();
        } else {
            window.close();
        }
        return;
    }
    
    const apiParams = {
        resultCd: resultCd
    };
    
    // 결과 데이터 가져오기
    const obj = TimsUtil.getObject("${svnmGetResult}", apiParams, false);
    
    if (!obj || !obj.list || obj.list.length === 0) {
        alert("평가 결과 정보를 불러올 수 없습니다.");
        if (parent) {
            TimsUtil.closeDialog();
        } else {
            window.close();
        }
        return;
    }
    
    // 평가위원, 지원자 정보 표시
    if (obj.info) {
        $('#jdgNm').text(obj.info.jdgNm);
        $('#appNm').text(obj.info.appNm);
        $('#fieldInfo').text(obj.info.fieldInfo);
        $('#opinion').val(obj.info.opinion);
        $('#minutes').val(obj.info.minutes);
        
        // 위원장이 아니면 회의록 영역 숨기기
        if (obj.info.chairYn !== 'Y') {
            $('#minutes-area').hide();
        }
    }
    
    // 평가 결과 테이블 렌더링
    RctUtil.renderTable("result-tbody", resultColumns, obj.list, null, null, "qstCd");
    
    // 총점 계산 및 표시
    calculateTotalScore(obj.list);
}

// 총점 계산
function calculateTotalScore(data) {
    let total = 0;
    
    data.forEach(item => {
        total += parseInt(item.resultScore || 0);
    });
    
    $('#total-score').text(total);
}
</script> 