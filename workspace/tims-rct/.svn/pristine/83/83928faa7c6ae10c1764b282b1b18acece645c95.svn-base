/*************************************************************
  프로그램명 : LoginVo.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.model;

import com.atsys.base.util.Utilities;
import com.atsys.model.TbUsrVo;


/**
 * @Class Name : BaseVO.java
 * @Description : Base VO class
 * @Modification Information
 *
 * @author frida
 * @since 2013-04-22
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class LoginVo extends TbUsrVo{

	private static final long serialVersionUID = 5921535309666927626L;
	
	private String userIp         = Utilities.getPeerIp();
	private String sessionId      = Utilities.getSessionId();
	private String impersonateYn  = "N";
	private String eduAdminCertYn = "N";       // 과정담당자신청관리에서 로그인 할 경우 2차인증을 거지치 않게 하기 위해서
	private String acsDiv         = "admin";
	private String cpCd           = "";        // 2023.09.22. 운영기관 코드
	private String cpAdminYn      = "N";       // 2023.09.22. TB_CP_USR(운영기관 사용자)에서 CP_CD = '100' 진흥원인 경우 Y, 나머지 N
	
	private int loginErrLimitCnt;
	
	public int getLoginErrLimitCnt() {
		return loginErrLimitCnt;
	}
	public void setLoginErrLimitCnt(int loginErrLimitCnt) {
		this.loginErrLimitCnt = loginErrLimitCnt;
	}
	public String getAcsDiv() {
		return acsDiv;
	}
	public void setAcsDiv(String acsDiv) {
		this.acsDiv = acsDiv;
	}
	public String getEduAdminCertYn() {
		return eduAdminCertYn;
	}
	public void setEduAdminCertYn(String eduAdminCertYn) {
		this.eduAdminCertYn = eduAdminCertYn;
	}
	public String getImpersonateYn() {
		return impersonateYn;
	}
	public void setImpersonateYn(String impersonateYn) {
		this.impersonateYn = impersonateYn;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getAcsHstCd() {
		return getSessionId();
	}
	public void setAcsHstCd(String acsHstCd) {
		setSessionId(acsHstCd);
	}
	public String getCpCd() {
		return cpCd;
	}
	public void setCpCd(String cpCd) {
		this.cpCd = cpCd;
	}
	public String getCpAdminYn() {
		return cpAdminYn;
	}
	public void setCpAdminYn(String cpAdminYn) {
		this.cpAdminYn = cpAdminYn;
	}
}
