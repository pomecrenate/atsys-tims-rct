<%--
	////////////////////////////////////////////////////////////
	// 프로그램명 : jdg1100p1.jsp
	// 설명 : 외부 평가위원 등록(팝업)
	// 작성자 : 이예찬
	// 일자 : 2025.04.22
	//////////////////////////////////////////////////////////// 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div>
    <div class="list_top">
    	<div class="rgt-area btn_group">
    		<button type="button" class="n_btn btn_md btn_c01" data-action="addRow">
                <i class="fas fa-plus"></i> 추가
            </button>
            <button type="button" class="n_btn btn_md btn_c05" data-action="deleteRow">
                <i class="fas fa-trash"></i> 삭제
            </button>
        	<button type="button" class="n_btn btn_md btn_c02" data-action="save">저장</button>
        </div>
    </div>

	<div class="description">
		※ tm + 휴대폰번호 끝 4자리가 초기 비밀번호로 설정됩니다. (예: tm1234)
	</div>
	<table class="table" id="judge-pre-table">
		<thead>
			<tr>
				<th>
					<input type="checkbox">
				</th>
				<th>아이디</th>
				<th>이름</th>
				<th>소속</th>
				<th>휴대폰번호</th>
				<th>이메일주소</th>
			</tr>
		</thead>
		<tbody id="judge-pre-tbody">
		</tbody>
	</table>
</div>

<script>
// 테이블 컬럼 정의
const judgeColumns = [
    { type: "checkbox", name: "check" },
    { type: "text", name: "usrId", value: "자동생성", readonly: true, class: "form-control form-control-sm" },
    { type: "text", name: "nm", class: "form-control form-control-sm" },
    { type: "text", name: "org", class: "form-control form-control-sm" },
    { type: "text", name: "mobPhone", class: "form-control form-control-sm" },
    { type: "text", name: "email", class: "form-control form-control-sm" }
];

$(document).ready(function() {
    // 초기 행 추가
    RctUtil.insertRow(judgeColumns, '#judge-pre-table');
    
    // 버튼 이벤트 처리
    $(document).on("click", "button[data-action]", function() {
        const action = $(this).data("action");
        
        switch(action) {
            case "addRow":
                RctUtil.insertRow(judgeColumns, '#judge-pre-table');
                break;
            case "deleteRow":
                RctUtil.deleteSelectedRows("#judge-pre-table");
                break;
            case "save":
                saveJudges();
                break;
        }
    });
});

function validateJudges(judges) {
	for (const judge of judges) {
        if (!TimsUtil.checkNotBlank(judge.nm) || !TimsUtil.checkNotBlank(judge.org) || !TimsUtil.checkNotBlank(judge.mobPhone) || !TimsUtil.checkNotBlank(judge.email)) {
            alert("모든 필드를 입력해주세요.");
            return false;
        }

        if (!TimsUtil.checkNumberOnly(judge.mobPhone) || !judge.mobPhone.startsWith('010') || !TimsUtil.checkSize(judge.mobPhone, 11, 11)) {
            alert("휴대폰 번호는 010으로 시작하는 11자리 숫자여야 합니다.");
            return false;
        }

        if (!TimsUtil.checkEmail(judge.email)) {
            alert("이메일 형식이 올바르지 않습니다.");
            return false;
        }
    }
    
    return true;
}

function saveJudges() {
    // 테이블의 모든 데이터 가져오기
    const judges = RctUtil.getTableDataList("#judge-pre-table");
    
    if (!TimsUtil.checkNotEmpty(judges)) {
        alert("등록할 평가위원 정보가 없습니다.");
        return;
    }
    
    // 데이터 유효성 검사
    if (!validateJudges(judges)) {
        return;
    }
    
    // 서버에 데이터 전송
    const obj = TimsUtil.getObject("${serviceBathPath}/jdg1100e/insert", judges, true);
    
    if (obj) {
        if (obj.insertCnt > 0) {
            alert(obj.insertCnt + "건의 평가위원이 등록되었습니다.");
            TimsUtil.closeDialog();
            loadJudgeList(1);
        } else {
            alert("등록된 평가위원이 없습니다.");
        }
    } else {
        alert("평가위원 등록 중 오류가 발생했습니다.");
    }
}
</script>