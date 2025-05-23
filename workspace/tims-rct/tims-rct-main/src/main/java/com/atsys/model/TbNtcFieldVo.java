package com.atsys.model;

public class TbNtcFieldVo extends TbNtcFieldBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3931854184534301485L;
	
	private String fieldCd; 
	private String collegeCd;
	private String collegeNm;
	private String deptCd;
	private String deptNm;
	private String majorNm;
	private String staffType;
	private String staffTypeNm;
	private int requiredCnt;
	private String requirements;
	private String assignedSubject;
	private String confirmedYn;
	
	public String getFieldCd() {
		return fieldCd;
	}
	public void setFieldCd(String fieldCd) {
		this.fieldCd = fieldCd;
	} 
	public String getCollegeCd() {
		return collegeCd;
	}
	public void setCollegeCd(String collegeCd) {
		this.collegeCd = collegeCd;
	}
	public String getCollegeNm() {
		return collegeNm;
	}
	public void setCollegeNm(String collegeNm) {
		this.collegeNm = collegeNm;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getMajorNm() {
		return majorNm;
	}
	public void setMajorNm(String majorNm) {
		this.majorNm = majorNm;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getStaffTypeNm() {
		return staffTypeNm;
	}
	public void setStaffTypeNm(String staffTypeNm) {
		this.staffTypeNm = staffTypeNm;
	}
	public int getRequiredCnt() {
		return requiredCnt;
	}
	public void setRequiredCnt(int requiredCnt) {
		this.requiredCnt = requiredCnt;
	}
	public String getRequirements() {
		return requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public String getAssignedSubject() {
		return assignedSubject;
	}
	public void setAssignedSubject(String assignedSubject) {
		this.assignedSubject = assignedSubject;
	}
	public String getConfirmedYn() {
		return confirmedYn;
	}
	public void setConfirmedYn(String confirmedYn) {
		this.confirmedYn = confirmedYn;
	}

	 

}
