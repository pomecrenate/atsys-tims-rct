package com.atsys.model;

public class TbAppVo extends TbAppBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7509806457787164367L;
	
	private String appCd; 
	private String appStatus;
	private String nm;
	private String mobPhone; 
	private String subDate; 
	private String qualYn;
	private String notQualReason;
	private String appUsrCd;
	private String careerPeriod;
	private String careerReason;
	
	
	public String getAppCd() {
		return appCd;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public String getNm() {
		return nm;
	}
	public String getMobPhone() {
		return mobPhone;
	} 
	public String getSubDate() {
		return subDate;
	}
	public String getQualYn() {
		return qualYn;
	}
	public String getNotQualReason() {
		return notQualReason;
	} 
	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public void setMobPhone(String mobPhone) {
		this.mobPhone = mobPhone;
	} 
	public void setUpdDate(String subDate) {
		this.subDate = subDate;
	}
	public void setQualYn(String qualYn) {
		this.qualYn = qualYn;
	}
	public void setNotQualReason(String notQualReason) {
		this.notQualReason = notQualReason;
	}
	public String getAppUsrCd() {
		return appUsrCd;
	}
	public String getCareerPeriod() {
		return careerPeriod;
	}
	public String getCareerReason() {
		return careerReason;
	}
	public void setAppUsrCd(String appUsrCd) {
		this.appUsrCd = appUsrCd;
	}
	public void setCareerPeriod(String careerPeriod) {
		this.careerPeriod = careerPeriod;
	}
	public void setCareerReason(String careerReason) {
		this.careerReason = careerReason;
	} 

}
