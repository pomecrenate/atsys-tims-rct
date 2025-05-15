package com.atsys.model;

public class TbEvalTotalScoreVo extends TbEvalTotalScoreBo {
    
    private static final long serialVersionUID = 1L;
    
    private String totalCd;
    private int totalAvg;
    private int exceptedAvg;
    private String passYn;
    
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
}
