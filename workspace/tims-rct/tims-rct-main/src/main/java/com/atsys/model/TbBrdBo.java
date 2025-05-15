package com.atsys.model;

import com.atsys.base.model.BaseVo;

public class TbBrdBo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8254247710620086727L;
	
	private String brdCd;
	private String brdNm;
	private String brdType;
	
	public String getBrdCd() {
		return brdCd;
	}
	public void setBrdCd(String brdCd) {
		this.brdCd = brdCd;
	}
	public String getBrdNm() {
		return brdNm;
	}
	public void setBrdNm(String brdNm) {
		this.brdNm = brdNm;
	}
	public String getBrdType() {
		return brdType;
	}
	public void setBrdType(String brdType) {
		this.brdType = brdType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
