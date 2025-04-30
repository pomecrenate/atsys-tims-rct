/**
 * 
 */
package com.atsys.search;

import com.atsys.nxf.app.vo.BaseBean;



/**
 * @Class Name : AuthorizedMenuSo.java
 * @Description : AuthorizedMenuSo class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 6. 11.
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class AuthorizedMenuSo extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5342675765900508996L;
	private String usrId;
	private String sysDiv;
	private String forwordUrl;
	private String menuId;
	private String pgmId;
	private String upMenuId;
	private String validIpChk;
	
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getSysDiv() {
		return sysDiv;
	}
	public void setSysDiv(String sysDiv) {
		this.sysDiv = sysDiv;
	}
	public String getForwordUrl() {
		return forwordUrl;
	}
	public void setForwordUrl(String forwordUrl) {
		this.forwordUrl = forwordUrl;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getPgmId() {
		return pgmId;
	}
	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUpMenuId() {
		return upMenuId;
	}
	public void setUpMenuId(String upMenuId) {
		this.upMenuId = upMenuId;
	}
	public String getValidIpChk() {
		return validIpChk;
	}
	public void setValidIpChk(String validIpChk) {
		this.validIpChk = validIpChk;
	}
}
