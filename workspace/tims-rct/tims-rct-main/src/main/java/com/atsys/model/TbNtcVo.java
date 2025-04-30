package com.atsys.model;

import java.io.Serializable;

public class TbNtcVo extends TbNtcBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4731060073795898428L;
	
	private String ntcCd;
	private String ntcNum;
	private String ntcType;
	private String ntcYear;
	private String ntcSmt;
	private String ntcNm;
	private String ntcStatus;
	private int requiredCnt;
	private String ntcStartDate;
	private String regStartDate;
	private String regEndDate;	
	private String hiringDate; 
	private String rschStartDate;
	private String rschEndDate;
	
	public String getNtcCd() {
		return ntcCd;
	}
	public void setNtcCd(String ntcCd) {
		this.ntcCd = ntcCd;
	}
	public String getNtcNum() {
		return ntcNum;
	}
	public void setNtcNum(String ntcNum) {
		this.ntcNum = ntcNum;
	}
	public String getNtcType() {
		return ntcType;
	}
	public void setNtcType(String ntcType) {
		this.ntcType = ntcType;
	}
	public String getNtcYear() {
		return ntcYear;
	}
	public void setNtcYear(String ntcYear) {
		this.ntcYear = ntcYear;
	}
	public String getNtcSmt() {
		return ntcSmt;
	}
	public void setNtcSmt(String ntcSmt) {
		this.ntcSmt = ntcSmt;
	}
	public String getNtcNm() {
		return ntcNm;
	}
	public void setNtcNm(String ntcNm) {
		this.ntcNm = ntcNm;
	}
	public String getNtcStatus() {
		return ntcStatus;
	}
	public void setNtcStatus(String ntcStatus) {
		this.ntcStatus = ntcStatus;
	}
	public int getRequiredCnt() {
		return requiredCnt;
	}
	public void setRequiredCnt(int requiredCnt) {
		this.requiredCnt = requiredCnt;
	}
	public String getNtcStartDate() {
		return ntcStartDate;
	}
	public void setNtcStartDate(String ntcStartDate) {
		this.ntcStartDate = ntcStartDate;
	}
	public String getRegStartDate() {
		return regStartDate;
	}
	public void setRegStartDate(String regStartDate) {
		this.regStartDate = regStartDate;
	}
	public String getRegEndDate() {
		return regEndDate;
	}
	public void setRegEndDate(String regEndDate) {
		this.regEndDate = regEndDate;
	}
	public String getHiringDate() {
		return hiringDate;
	}
	public void setHiringDate(String hiringDate) {
		this.hiringDate = hiringDate;
	}
	public String getRschStartDate() {
		return rschStartDate;
	}
	public void setRschStartDate(String rschStartDate) {
		this.rschStartDate = rschStartDate;
	}
	public String getRschEndDate() {
		return rschEndDate;
	}
	public void setRschEndDate(String rschEndDate) {
		this.rschEndDate = rschEndDate;
	}


	
}
