package com.atsys.model;

public class TbAppCareerVo extends TbAppCareerBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8851155301689638769L;
	
	private String careerCd; 
	private String workStartDate;
	private String workEndDate; 
	private String employedYn;
	private String workPeriod;
	private String companyNm;
	private String position;
	private String taskDesc;
	
	public String getCareerCd() {
		return careerCd;
	}
	public void setCareerCd(String careerCd) {
		this.careerCd = careerCd;
	}
	public String getWorkStartDate() {
		return workStartDate;
	}
	public void setWorkStartDate(String workStartDate) {
		this.workStartDate = workStartDate;
	}
	public String getWorkEndDate() {
		return workEndDate;
	}
	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}
	public String getWorkPeriod() {
		return workPeriod;
	}
	public void setWorkPeriod(String workPeriod) {
		this.workPeriod = workPeriod;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmployedYn() {
		return employedYn;
	}
	public void setEmployedYn(String employedYn) {
		this.employedYn = employedYn;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	
}
