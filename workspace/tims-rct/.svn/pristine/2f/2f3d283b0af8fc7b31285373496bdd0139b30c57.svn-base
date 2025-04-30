/*************************************************************
 프로그램명 : TbFacStaffVo.java
 설명 : 교직원(내부 평가위원) VO
 작성자 : 이예찬
 일자 : 2025.04.22
*************************************************************/
package com.atsys.model;

public class TbFacStaffVo extends TbUsrBo {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void setUsrCd(String empCd) {
        super.setUsrCd(empCd); // EMP_CD 설정
        super.setUsrId(empCd); // 내부 평가위원: 교번(empCd)을 그대로 사용
        super.setPw("1");      // 내부 평가위원 기본 비밀번호
    }
} 