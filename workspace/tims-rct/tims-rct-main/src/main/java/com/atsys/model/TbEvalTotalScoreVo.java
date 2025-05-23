package com.atsys.model;

public class TbEvalTotalScoreVo extends TbEvalTotalScoreBo {
    
    private static final long serialVersionUID = 1L;
    
    private String totalCd;
    private int totalAvg;
    private int exceptedAvg;
    private String passYn;
    private String stepStartDate;
	private String stepEndDate;
	private String stepAnnDate;
    
	public String getTotalCd() {
		return totalCd;
	}
	public void setTotalCd(String totalCd) {
		this.totalCd = totalCd;
	}
	public int getTotalAvg() {
		return totalAvg;
	}
	public void setTotalAvg(int totalAvg) {
		this.totalAvg = totalAvg;
	}
	public int getExceptedAvg() {
		return exceptedAvg;
	}
	public void setExceptedAvg(int exceptedAvg) {
		this.exceptedAvg = exceptedAvg;
	}
	public String getPassYn() {
		return passYn;
	}
	public void setPassYn(String passYn) {
		this.passYn = passYn;
	}
	public String getStepStartDate() {
		return stepStartDate;
	}
	public void setStepStartDate(String stepStartDate) {
		this.stepStartDate = stepStartDate;
	}
	public String getStepEndDate() {
		return stepEndDate;
	}
	public void setStepEndDate(String stepEndDate) {
		this.stepEndDate = stepEndDate;
	}
	public String getStepAnnDate() {
		return stepAnnDate;
	}
	public void setStepAnnDate(String stepAnnDate) {
		this.stepAnnDate = stepAnnDate;
	}
}
