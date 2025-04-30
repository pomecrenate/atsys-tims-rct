package com.atsys.model;

import java.util.ArrayList;
import java.util.List;

import com.atsys.base.model.ITreeVo;
import com.atsys.base.util.Utilities;

/**
 * @Class Name : MenuVo.java
 * @Description : MenuVo class
 * @Modification Information
 *
 * @author Kim hyun ho
 * @since 2017. 05. 16.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2017. All right reserved.
 */
public class MenuVo extends TbSysMenuVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8225110526950173422L;
	private List<MenuVo> children;
	private String usrCd;
	private String teacherMenu;
	private String usrMenu;
	private String rotSysId;
	private String loginId;
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUsrCd() {
		return usrCd;
	}

	public void setUsrCd(String usrCd) {
		this.usrCd = usrCd;
	}

	public String getRotSysId() {
		return rotSysId;
	}

	public void setRotSysId(String rotSysId) {
		this.rotSysId = rotSysId;
	}

	public MenuVo(){
		setSysCd(Utilities.getSystemId());
	}

	public List<MenuVo> getChildren() {
		List<MenuVo> ret = null;
		if(this.children != null) {
			ret = new ArrayList<MenuVo>();
			ret = this.children; 
		}
		return ret;
//		return children;
	}
	
	public void setChildren(List<MenuVo> children) {
		if(children != null) {
			this.children = new ArrayList<MenuVo>();
			this.children.addAll(children);
		}
//		this.children = children;
	}
	
	@Override
	public void addChild(ITreeVo vo) {
		addChildren((MenuVo) vo);
		super.addChild(vo);
	}
	
	public int addChildren(MenuVo vo){
		List<MenuVo> ret = null;
		if(this.children == null) {
			ret = new ArrayList<MenuVo>();
		} else {
			ret = new ArrayList<MenuVo>();
			ret = this.children; 
		}
		ret.add(vo);
		this.children = ret;
		return ret.size() - 1;
	}
	
	public String getUsrMenu() {
		if(Utilities.isEmpty(usrMenu)) {
			return Utilities.isAdminContext() ? "Y" : "N";
		} else {
			return usrMenu;
		}
	}

	public void setUsrMenu(String usrMenu) {
		this.usrMenu = usrMenu;
	}
	
	public String getTeacherMenu() {
		if(Utilities.isEmpty(teacherMenu)) {
			return Utilities.isTeacherContext()? "Y" : "N";
		} else {
			return teacherMenu;
		}
	}

	public void setTeacherMenu(String teacherMenu){
		this.teacherMenu = teacherMenu;
	}
	
	public boolean isHasViewChildren() {
		if(children == null || children.size() == 0) {
			return "Y".equals(getMenuUseYn());
		}
		for(MenuVo vo : children) {
			if(vo.isHasViewChildren()) {
				return true;
			}
		}
		return false;
	}
}
