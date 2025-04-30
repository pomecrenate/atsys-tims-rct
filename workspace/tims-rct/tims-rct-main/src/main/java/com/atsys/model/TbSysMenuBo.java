package com.atsys.model;

import com.atsys.base.model.AbstractTreeVo;

public class TbSysMenuBo extends AbstractTreeVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 227648478505243991L;

	private String sysMenuCd;
	private String uprMenuCd;
	private String menuNm;
	private String engMenuNm;
	private String sysCd;
	private int menuLvl;
	private int menuSort;
	private String menuUrl;
	private String menuUseYn;
	private String engMenuUseYn;
	
	private String menuCd;

	
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	public String getSysMenuCd() {
		return sysMenuCd;
	}
	public void setSysMenuCd(String sysMenuCd) {
		this.sysMenuCd = sysMenuCd;
	}
	public String getUprMenuCd() {
		return uprMenuCd;
	}
	public void setUprMenuCd(String uprMenuCd) {
		this.uprMenuCd = uprMenuCd;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getEngMenuNm() {
		return engMenuNm;
	}
	public void setEngMenuNm(String engMenuNm) {
		this.engMenuNm = engMenuNm;
	}
	public String getSysCd() {
		return sysCd;
	}
	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	public int getMenuLvl() {
		return menuLvl;
	}
	public void setMenuLvl(int menuLvl) {
		this.menuLvl = menuLvl;
	}
	public int getMenuSort() {
		return menuSort;
	}
	public void setMenuSort(int menuSort) {
		this.menuSort = menuSort;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuUseYn() {
		return menuUseYn;
	}
	public void setMenuUseYn(String menuUseYn) {
		this.menuUseYn = menuUseYn;
	}
	public String getEngMenuUseYn() {
		return engMenuUseYn;
	}
	public void setEngMenuUseYn(String engMenuUseYn) {
		this.engMenuUseYn = engMenuUseYn;
	}
	
	@Override
	public String getId() {	
		return getSysMenuCd();
	}
	@Override
	public String getText() {
		return getMenuNm();
	}
	@Override
	public String getParentId() {
		return getUprMenuCd();
	}
	
}