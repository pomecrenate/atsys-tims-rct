<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : jdg1100e.jsp
	// 설명 : 외부 평가위원 관리
	// 작성자 : 이예찬
	// 일자 : 2025.04.22
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/jdg1100e"/>
<c:set var="svnmInsPop" value="${svnm}/popup/insertPop"/>
<c:set var="svnmSearch" value="${svnm}/search"/>
<c:set var="svnmDelete" value="${svnm}/delete"/>

<div class="x_panel_wrap">
    <div class="x_panel" style="display: flex; align-items: center; gap: 10px;">
        <form method="POST" class="form-horizontal form-label-left" id="search" name="search">
            <div class="search_condition">
                <div class="condition_list">
                    <dl style="margin-bottom: 0;">
                        <dt><label for="searchCriteria">검색 기준</label></dt>
                        <dd>
                            <select class="form-control" id="searchCriteria" name="searchCriteria" style="width: 100px;">
                                <option value="name" selected>이름</option>
                                <option value="id">아이디</option>
                            </select>
                        </dd>
                    </dl>
                    <dl style="margin-bottom: 0;">
                        <dt><label for="searchKeyword">검색어</label></dt>
                        <dd>
                            <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"
                                   placeholder="이름을 입력하세요" style="width: 200px;">
                        </dd>
                    </dl>
                    <button data-action="search" type="button" class="n_btn btn_md btn_search" onclick="loadJudgeList(1)">
                        <i class="fas fa-search"></i> 조회
                    </button>
                </div>
            </div>
        </form>
        <button class="n_btn btn_md btn_c01" data-action="insertPop" style="margin-left: auto;">
        	<i class="fas fa-plus"></i> 외부 위원 등록
        </button>
    </div>

    <div class="x_content">
        <div class="description">※ 삭제 버튼 클릭 시 해당 위원을 삭제할 수 있습니다.</div>
        <div class="content_list" id="content_list">
	        <div class="list_top">
	        	<div class="rgt-area btn_group">
		            <button data-action="delete" type="button" class="n_btn btn_md btn_c05">
		                삭제
		            </button>
	            </div>
	        </div>
            <table class="table" id="judge-table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox">
                        </th>
                        <th>번호</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>소속</th>
                        <th>휴대폰 번호</th>
                        <th>이메일 주소</th>
                        <th>생성자</th>
                        <th>수정자</th>
                    </tr>
                </thead>
                <tbody id="judge-tbody">
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="x_pagination">
    </div>
</div>

<script>
// 테이블 컬럼 정의
const judgeColumns = [
    { type: "checkbox", name: "check" },
    { type: "text", name: "rowNum" },
    { type: "text", name: "usrId" },
    { type: "text", name: "nm" },
    { type: "text", name: "org" },
    { type: "text", name: "mobPhone" },
    { type: "text", name: "email" },
    { type: "text", name: "creUsrInfo" },
    { type: "text", name: "updUsrInfo" }
];

$(document).ready(function() {
    // 초기 데이터 로드 (전체 리스트)
    loadJudgeList(1); // 1페이지부터 시작
    
    // 검색 기준 변경 시 placeholder 텍스트 업데이트
    $("#searchCriteria").on("change", function() {
        const criteria = $(this).val();
        if (criteria === 'name') {
            $("#searchKeyword").attr("placeholder", "이름을 입력하세요");
        } else if (criteria === 'id') {
            $("#searchKeyword").attr("placeholder", "아이디를 입력하세요");
        }
    });
    
    // 엔터 키 검색 지원
    $("#searchKeyword").on("keypress", function(e) {
        if (e.keyCode === 13) {
            e.preventDefault();
            loadJudgeList(1);
        }
    });
    
    // 외부 평가위원 등록 버튼 클릭
    $(document).on("click", "button[data-action='insertPop']", function() {
		var url = '${svnmInsPop}';
		RctUtil.urlDialog('외부 평가위원 등록', url, 800, 400);
    });

    // 삭제 버튼 클릭 이벤트
    $(document).on("click", "button[data-action='delete']", function() {
        const selectedIds = [];
        
        // RctUtil에서 추가한 row-checkbox 클래스를 이용해 체크된 항목 찾기
        $(".row-checkbox:checked").each(function() {
            const row = $(this).closest("tr");
            const usrCd = row.attr("data-id");
            if (usrCd) selectedIds.push(usrCd);
        });

        if (selectedIds.length === 0) {
            alert("삭제할 평가위원을 선택해주세요.");
            return;
        }

        if (!confirm("선택한 평가위원을 삭제하시겠습니까?")) return;

        deleteSelectedJudges(selectedIds);
    });

    // 페이지네이션 클릭 이벤트 처리
    $(document).on('click', '.x_pagination a', function(e) {
        e.preventDefault();
        const pageNo = $(this).data('page');
        loadJudgeList(pageNo);
    });
});

// 리스트 로드
function loadJudgeList(pageNo) {
    const params = {
        searchCriteria: $("#searchCriteria").val(),
        keyword: $("#searchKeyword").val(),
        pageIndex: pageNo || 1
    };
    
    const obj = TimsUtil.getObject("${svnmSearch}", params, true);

    if (obj) {
        // 데이터 전처리: 번호와 생성자/수정자 정보 추가
        if (obj.list && obj.list.length > 0) {
        	obj.list.forEach((item, index) => {
                // 번호 (1부터 시작)
                item.rowNum = ((obj.paginationInfo.currentPageNo - 1) * obj.paginationInfo.recordCountPerPage) + (index + 1);
                // 생성자 정보
                item.creUsrInfo = `\${item.creUsrNm || ''}(\${item.creDate || ''})`;
                // 수정자 정보
                item.updUsrInfo = `\${item.updUsrNm || ''}(\${item.updDate || ''})`;
            });
        }
        RctUtil.renderTable("judge-tbody", judgeColumns, obj.list, null, obj.paginationInfo, "usrCd");
    } else {
        alert("평가위원 목록 조회 중 오류가 발생했습니다.");
    }
}

// 평가위원 삭제
function deleteSelectedJudges(ids) {
    const obj = TimsUtil.getObject("${svnmDelete}", ids, true);
    
    if (obj) {
        if (obj.deleteCnt > 0) {
            alert(obj.deleteCnt + "건의 평가위원이 삭제되었습니다.");
        } else {
            alert("확정된 평가위원이 있어 평가위원을 삭제할 수 없습니다.");
        }
    } else {
        alert("평가위원 삭제 중 오류가 발생했습니다.");
    }
    
    loadJudgeList(1);
}
</script>