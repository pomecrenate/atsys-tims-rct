package com.atsys.model;

public class TbEvalSheetQstVo extends TbEvalSheetQstBo {
    
    private static final long serialVersionUID = 1L;
    
    private String qstCd;
    private String qstType;
    private String qstTypeNm;
    private String qstNm;
    private int qstScore;
    private String indNm;
    private int indScore;
    private String scoreCriteria;
    private int sortOrder;
    
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
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
}
