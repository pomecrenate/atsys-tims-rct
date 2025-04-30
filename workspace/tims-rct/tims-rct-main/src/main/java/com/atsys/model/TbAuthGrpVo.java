package com.atsys.model;

public class TbAuthGrpVo extends TbAuthGrpBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8217963071294018259L;
	
	private String authYn;
	private String authExprtDt;
	private String newAuthGrpCd;

	public String getNewAuthGrpCd() {
		return newAuthGrpCd;
	}
	public void setNewAuthGrpCd(String newAuthGrpCd) {
		this.newAuthGrpCd = newAuthGrpCd;
	}
	public String getAuthYn() {
		return authYn;
	}
	public void setAuthYn(String authYn) {
		this.authYn = authYn;
	}
	public String getAuthExprtDt() {
		return authExprtDt;
	}
	public void setAuthExprtDt(String authExprtDt) {
		this.authExprtDt = authExprtDt;
	}
	
	
}