/*************************************************************
 프로그램명 : TbEvalScoreVo.java
 설명 : 평가 점수 정보 VO
 작성자 : 이예찬
 일자 : 2025.05.25
*************************************************************/
package com.atsys.model;

import java.io.Serializable;

public class TbEvalScoreVo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String qstCd;          // 문항 코드
    private String qstType;        // 문항 유형
    private String qstTypeNm;      // 문항 유형명
    private String qstNm;          // 문항명
    private int qstScore;          // 문항 배점
    private String indNm;          // 평가지표명
    private int indScore;          // 세부배점
    private String scoreCriteria;  // 점수기준
    private int resultScore;       // 평가점수
    
    public String getQstCd() {
        return qstCd;
    }
    
    public void setQstCd(String qstCd) {
        this.qstCd = qstCd;
    }
    
    public String getQstType() {
        return qstType;
    }
    
    public void setQstType(String qstType) {
        this.qstType = qstType;
    }
    
    public String getQstTypeNm() {
        return qstTypeNm;
    }
    
    public void setQstTypeNm(String qstTypeNm) {
        this.qstTypeNm = qstTypeNm;
    }
    
    public String getQstNm() {
        return qstNm;
    }
    
    public void setQstNm(String qstNm) {
        this.qstNm = qstNm;
    }
    
    public int getQstScore() {
        return qstScore;
    }
    
    public void setQstScore(int qstScore) {
        this.qstScore = qstScore;
    }
    
    public String getIndNm() {
        return indNm;
    }
    
    public void setIndNm(String indNm) {
        this.indNm = indNm;
    }
    
    public int getIndScore() {
        return indScore;
    }
    
    public void setIndScore(int indScore) {
        this.indScore = indScore;
    }
    
    public String getScoreCriteria() {
        return scoreCriteria;
    }
    
    public void setScoreCriteria(String scoreCriteria) {
        this.scoreCriteria = scoreCriteria;
    }
    
    public int getResultScore() {
        return resultScore;
    }
    
    public void setResultScore(int resultScore) {
        this.resultScore = resultScore;
    }
}
