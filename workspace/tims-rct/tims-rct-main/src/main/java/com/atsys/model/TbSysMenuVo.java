package com.atsys.model;

import com.atsys.base.util.Utilities;

public class TbSysMenuVo extends TbSysMenuBo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8689272542683767610L;
	
	private String uprMenuNm;
	private String authGrpCd;
	private String expandedYn;
	private String prsnInfoSetn;
	private String adminSrchSetn;
	private String menuFullPath;

	public String getMenuFullPath() {
		return menuFullPath;
	}
	public void setMenuFullPath(String menuFullPath) {
		this.menuFullPath = menuFullPath;
	}
	public String getPrsnInfoSetn() {
		return prsnInfoSetn;
	}
	public void setPrsnInfoSetn(String prsnInfoSetn) {
		this.prsnInfoSetn = prsnInfoSetn;
	}
	public String getAdminSrchSetn() {
		return adminSrchSetn;
	}
	public void setAdminSrchSetn(String adminSrchSetn) {
		this.adminSrchSetn = adminSrchSetn;
	}
	public String getUprMenuNm() {
		return uprMenuNm;
	}
	public void setUprMenuNm(String uprMenuNm) {
		this.uprMenuNm = uprMenuNm;
	}
	public String getUrl(){
		if(Utilities.isEmpty(getMenuUrl()))
			return null;
		String url = Utilities.nullCheck(getMenuUrl());
		return url;
	}
	public String getAuthGrpCd() {
		return authGrpCd;
	}
	public void setAuthGrpCd(String authGrpCd) {
		this.authGrpCd = authGrpCd;
		if(getState()!=null)
			getState().setChecked(Utilities.isNotEmpty(authGrpCd));
	}
	public String getExpandedYn() {
		return expandedYn;
	}
	public void setExpandedYn(String expandedYn) {
		this.expandedYn = expandedYn;
		if(getState()!=null)
			getState().setExpanded("Y".equals(expandedYn));
	}
	
}