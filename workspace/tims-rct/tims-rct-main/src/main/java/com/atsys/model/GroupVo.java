/*************************************************************
  프로그램명 : GroupVo.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.model;

import java.io.Serializable;

/**
 * @Class Name : GroupVo.java
 * @Description : GroupVo class
 * @Modification Information
 *
 * @author Kim Wan Tae
 * @since 2015. 02. 12.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2015. All right reserved.
 */
public class GroupVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8076060540975064609L;
	private String bizId;
	private String bizNm;
	private String prtOrd;
	private String adminAcsYn;
	private String authAcsYn;
	private String authGrpTypeCd;
	
	public String getAuthGrpTypeCd() {
		return authGrpTypeCd;
	}

	public void setAuthGrpTypeCd(String authGrpTypeCd) {
		this.authGrpTypeCd = authGrpTypeCd;
	}

	public String getAdminAcsYn() {
		return adminAcsYn;
	}

	public void setAdminAcsYn(String adminAcsYn) {
		this.adminAcsYn = adminAcsYn;
	}

	public String getAuthAcsYn() {
		return authAcsYn;
	}

	public void setAuthAcsYn(String authAcsYn) {
		this.authAcsYn = authAcsYn;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizNm() {
		return bizNm;
	}

	public void setBizNm(String bizNm) {
		this.bizNm = bizNm;
	}

	public String getPrtOrd() {
		return prtOrd;
	}

	public void setPrtOrd(String prtOrd) {
		this.prtOrd = prtOrd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
