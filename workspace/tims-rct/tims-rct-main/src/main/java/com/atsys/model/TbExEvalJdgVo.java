/*************************************************************
 프로그램명 : TbExEvalJdgVo.java
 설명 : 외부 평가위원 VO
 작성자 : 이예찬
 일자 : 2025.04.22
*************************************************************/
package com.atsys.model;

public class TbExEvalJdgVo extends TbUsrBo {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void setMobPhone(String mobPhone) {
        super.setMobPhone(mobPhone);
        // 외부 평가위원 비밀번호: tm + 휴대폰 번호 끝 4자리
        this.setPw("tm" + mobPhone.substring(mobPhone.length() - 4));
    }
} 