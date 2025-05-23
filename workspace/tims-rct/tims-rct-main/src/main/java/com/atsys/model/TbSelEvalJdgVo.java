package com.atsys.model;

public class TbSelEvalJdgVo extends TbUsrBo {
    
    private static final long serialVersionUID = 1L;
    
    private String stepCd;
    private String fieldCd;
    private String jdgDiv;
	private String jdgDivNm;
    private String chairYn;
    private String oathAgreeYn;
    private String oathAgreeDate;
    private String confirmedYn;
    private String totalScore;
    
	public String getStepCd() {
		return stepCd;
	}
	public void setStepCd(String stepCd) {
		this.stepCd = stepCd;
	}
	public String getFieldCd() {
		return fieldCd;
	}
	public void setFieldCd(String fieldCd) {
		this.fieldCd = fieldCd;
	}
	public String getJdgDiv() {
		return jdgDiv;
	}
	public void setJdgDiv(String jdgDiv) {
		this.jdgDiv = jdgDiv;
	}
	public String getJdgDivNm() {
		return jdgDivNm;
	}
	public void setJdgDivNm(String jdgDivNm) {
		this.jdgDivNm = jdgDivNm;
	}
	public String getChairYn() {
		return chairYn;
	}
	public void setChairYn(String chairYn) {
		this.chairYn = chairYn;
	}
	public String getOathAgreeYn() {
		return oathAgreeYn;
	}
	public void setOathAgreeYn(String oathAgreeYn) {
		this.oathAgreeYn = oathAgreeYn;
	}
	public String getOathAgreeDate() {
		return oathAgreeDate;
	}
	public void setOathAgreeDate(String oathAgreeDate) {
		this.oathAgreeDate = oathAgreeDate;
	}
	public String getConfirmedYn() {
		return confirmedYn;
	}
	public void setConfirmedYn(String confirmedYn) {
		this.confirmedYn = confirmedYn;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
}
