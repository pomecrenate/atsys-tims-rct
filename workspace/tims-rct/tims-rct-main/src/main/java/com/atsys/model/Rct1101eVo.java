package com.atsys.model;

import java.util.List;

import com.atsys.base.model.BaseVo;

public class Rct1101eVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8862426853236273997L;

    private List<String> ids;
    private String ntcCd;
    
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public String getNtcCd() {
		return ntcCd;
	}
	public void setNtcCd(String ntcCd) {
		this.ntcCd = ntcCd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
