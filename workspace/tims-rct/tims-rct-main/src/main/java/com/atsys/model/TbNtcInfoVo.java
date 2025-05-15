package com.atsys.model;

public class TbNtcInfoVo extends TbNtcInfoBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8168477860623456549L;
	
	private String ntcInfoCd;
	private String ntcInfoType;
	private String ntcInfoCnts;
	
	public String getNtcInfoCd() {
		return ntcInfoCd;
	}
	public void setNtcInfoCd(String ntcInfoCd) {
		this.ntcInfoCd = ntcInfoCd;
	}
	public String getNtcInfoType() {
		return ntcInfoType;
	}
	public void setNtcInfoType(String ntcInfoType) {
		this.ntcInfoType = ntcInfoType;
	}
	public String getNtcInfoCnts() {
		return ntcInfoCnts;
	}
	public void setNtcInfoCnts(String ntcInfoCnts) {
		this.ntcInfoCnts = ntcInfoCnts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
