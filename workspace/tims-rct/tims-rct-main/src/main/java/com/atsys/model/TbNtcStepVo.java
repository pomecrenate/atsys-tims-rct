package com.atsys.model;

import java.io.Serializable;

public class TbNtcStepVo extends TbNtcStepBo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2793628245232411630L;

	private String stepCd;
	private String step;
	private String stepStartDate;
	private String stepEndDate;
	private String stepAnnDate;
	
	
	public String getStepCd() {
		return stepCd;
	}
	public void setStepCd(String stepCd) {
		this.stepCd = stepCd;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
}
