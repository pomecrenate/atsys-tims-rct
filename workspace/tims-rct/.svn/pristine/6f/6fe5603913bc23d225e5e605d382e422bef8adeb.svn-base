/*************************************************************
  프로그램명 : LoginUser.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.app.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.model.AbstractTreeVo;
import com.atsys.base.model.LoginVo;
import com.atsys.base.util.Utilities;
import com.atsys.model.GroupVo;
import com.atsys.model.MenuVo;
import com.atsys.model.TbAuthUsrIpVo;
import com.atsys.nxf.core.web.servlet.handler.util.SessionLoginUtils;

/**
 * @Class Name : LoginUser.java
 * @Description : LoginUser class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 5. 6.
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
public class LoginUser extends LoginVo implements Serializable {

	private static final long serialVersionUID = -6209822516935072533L;
	
	private Map<String, GroupVo> groups = new HashMap<String, GroupVo>();
	private Map<String, MenuVo> menus = new HashMap<String, MenuVo>();
	private List<TbAuthUsrIpVo> ipList;
	private Map<String,Object> captchaMap;
	private boolean sencondAuth = false;
	private boolean ipAccess = false;
	private String groupTypes = "";
	
	private String brdCd;
	private String pstCd;
	private String ansPstCd;
	
	// 권한만료일 체크 값 AUTH_EXPIR_DT_CHK_YN
	private String authExprdDtChkYn;
	private String sessionExpiryPreTime; // 세션만료 안내 타임
	
	// 로그아웃시 마지막 메뉴 url
	private String lastMenuUrl;
	private String lastSysLang;
	
	private String srchHstGrnt;   // 관리이력 권한여부
	private String ssoYn;         // sso 사용여부
	private String dupLoginChkYn; // 로그인 중복 체크 적용여부
	private String diChkYn;       // 본인인증(DI) 체크 적용여부
	private String pwChgCycYn;    // 비밀번호 변경 주기 적용여부
	private String csrfToken;    // CSRF Token 설정
	private String csrfTokenId; // CSRF Token 설정
	
	public String getCsrfTokenId() {
		if(isLogin()) {
			// 로그인 후의 CSRF TOKEN ID
			return csrfTokenId;	
		} else {
			// 로그인 전의 CSRF TOKEN ID
			if(Utilities.isEmpty(csrfTokenId)) {
				setCsrfTokenId(Utilities.getTbUniqId());
			}
			return csrfTokenId;
		}
	}

	public void setCsrfTokenId(String csrfTokenId) {
		this.csrfTokenId = csrfTokenId;
	}

	public String getCsrfToken() {
		if(isLogin()) {
			// 로그인 후의 CSRF TOKEN 값
			return csrfToken;	
		} else {
			// 로그인 전의 CSRF TOKEN 값
			if(Utilities.isEmpty(csrfToken)) {
				setCsrfToken(Utilities.getUUID());
			}
			return csrfToken;
		}
	}

	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}

	public String getDupLoginChkYn() {
		return dupLoginChkYn;
	}
	
	public void setDupLoginChkYn(String dupLoginChkYn) {
		this.dupLoginChkYn = dupLoginChkYn;
	}
	
	public String getDiChkYn() {
		return diChkYn;
	}
	
	public void setDiChkYn(String diChkYn) {
		this.diChkYn = diChkYn;
	}
	
	public String getPwChgCycYn() {
		return pwChgCycYn;
	}
	
	public void setPwChgCycYn(String pwChgCycYn) {
		this.pwChgCycYn = pwChgCycYn;
	}
	
	public String getSsoYn() {
		return ssoYn;
	}
	
	public void setSsoYn(String ssoYn) {
		this.ssoYn = ssoYn;
	}
	
	public String getSrchHstGrnt() {
		return srchHstGrnt;
	}
	
	public void setSrchHstGrnt(String srchHstGrnt) {
		this.srchHstGrnt = srchHstGrnt;
	}
	
	public boolean isIpAccess() {
		return ipAccess;
	}
	
	public void setIpAccess(boolean ipAccess) {
		this.ipAccess = ipAccess;
	}
	
	public String getLastSysLang() {
		return lastSysLang;
	}
	
	public void setLastSysLang(String lastSysLang) {
		this.lastSysLang = lastSysLang;
	}
	
	public String getLastMenuUrl() {
		return lastMenuUrl;
	}
	
	public void setLastMenuUrl(String lastMenuUrl) {
		this.lastMenuUrl = lastMenuUrl;
	}
	
	public String getBrdCd() {
		return brdCd;
	}
	
	public void setBrdCd(String brdCd) {
		this.brdCd = brdCd;
	}
	
	public String getPstCd() {
		return pstCd;
	}
	
	public void setPstCd(String pstCd) {
		this.pstCd = pstCd;
	}
	
	public String getAnsPstCd() {
		return ansPstCd;
	}
	
	public void setAnsPstCd(String ansPstCd) {
		this.ansPstCd = ansPstCd;
	}
	
	public String getSessionExpiryPreTime() {
		return sessionExpiryPreTime;
	}
	
	public void setSessionExpiryPreTime(String sessionExpiryPreTime) {
		this.sessionExpiryPreTime = sessionExpiryPreTime;
	}
	
	public boolean isLogin(){
		return Utilities.isNotEmpty(getUsrCd());
	}
	
	public void addCaptcha(String sDivision){
		if(captchaMap == null) {
			captchaMap = new HashMap<String, Object>();
		}
		captchaMap.put(sDivision, Utilities.getDateString("") + Utilities.getTimeString(""));
	}
	
	public boolean hasCaptcha(String sDivision){
		if(captchaMap == null) {
			return false;
		}
		return captchaMap.containsKey(sDivision);
	}
	
	public static LoginUser getLoginUser() throws Exception {
		return SessionLoginUtils.getLoginUser();
	}
	
	public Map<String, GroupVo> getGroups() {
		return groups;
	}
	
	public void setGroups(Map<String, GroupVo> groups) {
		this.groups = groups;
	}
	
	public void initGroups(List<GroupVo> list){
		groups.clear();
		groupTypes = "";
		for(GroupVo vo : list) {
			groups.put(vo.getBizId(), vo);
			groupTypes += vo.getAuthGrpTypeCd() + "|";
		}
	}
	
	public void initMenus(List<MenuVo> list){
		menus.clear();
		for(MenuVo vo : list) {
			menus.put(vo.getSysMenuCd().toUpperCase(), vo); 
		}
		AbstractTreeVo.makeHierarchy(list);
	}
	
	public Map<String, MenuVo> getMenus() {
		return menus;
	}
	
	public void setMenus(Map<String, MenuVo> menus) {
		this.menus = menus;
	}
	
	public boolean isAdmin() throws Exception{
		return hasGroupType(ConstantsUtils.getGroupAdminType()); 
	} 
	
	public boolean isTutor(){
		return hasGroupType(ConstantsUtils.getGroupTutorType()); 
	} 
	
	public boolean isAdminAccess() throws Exception {
		if(Utilities.isEmpty(groups)) {
			return false;
		}
		boolean isAccess = false;
		for(String key : groups.keySet()){
			GroupVo vo2 = groups.get(key);
			if(vo2.getAdminAcsYn().equals("Y")) {
				isAccess = true;
				break;
			}
		}
		return isAccess; 
	}
	
	public boolean hasGroup(String groupCode){
		return groups.containsKey(groupCode);
	}
	
	public boolean hasGroupType(String groupType){
		if(groupTypes.indexOf(groupType) > -1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasMenu(String menuCd){
		if(Utilities.isEmpty(menuCd)) {
			return false;
		}
		return menus.containsKey(menuCd.toUpperCase());
	}
	
	public boolean canAccessMenu(String menuCd){
		if(!hasMenu(menuCd)) {
			return false;
		}
		MenuVo vo = getMenu(menuCd);
		if(Utilities.isAdminContext()){
			if(!ConstantsUtils.getAdmContenxt().equals(vo.getSysCd())) {
				return false;
			}
		} else {
			String systemCd = vo.getSysCd();
			if(!(ConstantsUtils.getFrontContenxt().equals(systemCd))) {
				return false;
			}
		}
		return true;
	}
	
	public String getMenuName(String menuCd){
		if(!hasMenu(menuCd)) {
			return null;
		}
		return menus.get(menuCd).getMenuNm();
	}
	
	public String getGroupName(String groupCode){
		if(!hasGroup(groupCode)) {
			return null;
		}
		return groups.get(groupCode).getBizNm();
	}
	
	public MenuVo getMenu(String menuCd){
		if(!hasMenu(menuCd)) {
			return null;
		}
		return menus.get(menuCd);
	}
	
	public GroupVo getGroup(String groupCode){
		if(!hasGroup(groupCode)) {
			return null;
		}
		return groups.get(groupCode);
	}
	 
	
	public List<TbAuthUsrIpVo> getIpList() {
		List<TbAuthUsrIpVo> ret = null;
		if(this.ipList != null) {
			ret = new ArrayList<TbAuthUsrIpVo>();
			ret = this.ipList; 
		}
		return ret; 
	}
	
	public void setIpList(List<TbAuthUsrIpVo> ipList) {
		if(ipList != null) {
			this.ipList = new ArrayList<TbAuthUsrIpVo>();
			this.ipList.addAll(ipList);
		} 
	}
	
	public boolean canAccess() {
		if(!ConstantsUtils.getAdmContenxt().equals(Utilities.getSystemId())) {
			return true;
		}
		String userIp = Utilities.getPeerIp();
		// atsys계정은 로컬일 경우에만 가능하도록 함
		if("atsys".equalsIgnoreCase(getUsrCd()) && userIp.equals("127.0.0.1")) {
			return true;
		}
		if(getUsrCd().indexOf("atsys") > -1) {
			return true;
		}
		if(isSencondAuth()) {
			return true;
		}
		if(getEduAdminCertYn().equals("Y")) { // 과정담당자신청관리에서 로그인 할 경우 2차인증을 거지치 않게 하기 위해서
			return true;
		}
		if(isIpAccess()) {
			return true;
		}
		return false;
	}
	
	public boolean isSencondAuth() {
		return sencondAuth;
	}
	
	public void setSecondAuth(boolean sencondAuth) {
		this.sencondAuth = sencondAuth;
	}
	
	public String getAuthExprdDtChkYn() {
		return authExprdDtChkYn;
	}
	
	public void setAuthExprdDtChkYn(String authExprdDtChkYn) {
		this.authExprdDtChkYn = authExprdDtChkYn;
	}
	
}