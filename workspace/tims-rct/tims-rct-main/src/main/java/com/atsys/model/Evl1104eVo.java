package com.atsys.model;

import com.atsys.base.model.BaseVo;

public class Evl1104eVo extends BaseVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4731060073795898428L;
	
	private String appCd;
	private String nm;
	private String birthDate;
	private String mobPhone;
	private int totalAvg;
    private int exceptedAvg;
    private int rank;
    
	public String getAppCd() {
		return appCd;
	}
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getMobPhone() {
		return mobPhone;
	}
	public void setMobPhone(String mobPhone) {
		this.mobPhone = mobPhone;
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
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}
