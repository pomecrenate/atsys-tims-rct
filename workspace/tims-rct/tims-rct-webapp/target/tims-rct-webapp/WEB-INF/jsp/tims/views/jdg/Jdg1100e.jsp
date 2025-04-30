<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/tims/common/taglib/Taglib.jsp" %>
<jsp:useBean id="cmmCdConstants" class="com.atsys.base.util.CmmCdConstants"/>

<c:set var="svnm" value="${serviceBathPath}/rcp1200e"></c:set>

<div class="x_panel_wrap">
    <div class="x_panel_title">전체 평가 위원 리스트</div>
    <div class="x_panel">
        <form method="POST" class="form-horizontal form-label-left" id="search" name="search">
            <div class="search_condition">
                <button data-action="search" type="button" class="n_btn btn_md btn_search">
                    <i class="fas fa-search"></i> 조회
                </button>
                <div class="condition_list">
                    <dl>
                        <dt><label for="qryType">내/외부</label></dt>
                        <dd>
                            <select class="form-control" id="qryType" name="qryType">
                                <option value="">전체</option>
                                <option value="내부">내부</option>
                                <option value="외부">외부</option>
                            </select>
                        </dd>
                    </dl>
                </div>
            </div>
        </form>
    </div>

    <div class="x_content">
        <div class="content_top">
            <button class="n_btn btn_md btn_c01" data-action="registerEvaluator">외부 위원 등록</button>
        </div>
        <div class="content_list" id="content_list">
            <table class="table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>이름</th>
                        <th>소속</th>
                        <th>휴대폰 번호</th>
                        <th>이메일 주소</th>
                        <th>내/외부구분</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    
    <div class="x_pagination">
    </div>
</div>

<script>
$(document).ready(function() {
    // 초기 데이터 로드
    loadEvaluatorList();
    
    // 조회 버튼 클릭
    $(document).on("click", "button[data-action='search']", function() {
        loadEvaluatorList();
    });
    
    // 외부 위원 등록 버튼 클릭
    $(document).on("click", "button[data-action='registerEvaluator']", function() {
        // 등록 팝업 구현
    });
});

function loadEvaluatorList() {
    const params = $("#search").serialize();
    
    $.ajax({
        url: "${svnm}/list",
        type: "POST",
        data: params,
        success: function(data) {
            renderEvaluatorList(data.list);
        }
    });
}

function renderEvaluatorList(list) {
    const tbody = $("#content_list tbody");
    tbody.empty();
    
    list.forEach((item, index) => {
        const tr = $("<tr>");
        tr.append(`<td>${index + 1}</td>`);
        tr.append(`<td>${item.name}</td>`);
        tr.append(`<td>${item.organization}</td>`);
        tr.append(`<td>${item.phone}</td>`);
        tr.append(`<td>${item.email}</td>`);
        tr.append(`<td>${item.type}</td>`);
        tr.append(`<td><button class="n_btn btn_md btn_c02" onclick="deleteEvaluator('${item.id}')">삭제</button></td>`);
        tbody.append(tr);
    });
}

function deleteEvaluator(id) {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    
    $.ajax({
        url: "${svnm}/delete",
        type: "POST",
        data: { id: id },
        success: function(data) {
            if (data.result === "success") {
                alert("삭제되었습니다.");
                loadEvaluatorList();
            }
        }
    });
}
</script>