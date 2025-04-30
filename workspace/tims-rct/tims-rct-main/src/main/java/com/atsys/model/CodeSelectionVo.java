/*************************************************************
  프로그램명 : CodeSelectionVo.java
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
 * @Class Name : CodeSelectionVo.java
 * @Description : CodeSelectionVo class
 * @Modification Information
 *
 * @author frida
 * @since 2014. 4. 29.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public class CodeSelectionVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1379329833492898296L;
	private String id;
	private String lrgCd;
    private String smallCd;
    private String cdNm;
    private String eng;
    private String korAls;
    private String engAls;
    private String sysDiv;
	private long sortOrder;
	private String oldCd;
    private String delYn;
	
	private String langSet;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLrgCd() {
		return lrgCd;
	}

	public void setLrgCd(String lrgCd) {
		this.lrgCd = lrgCd;
	}

	public String getSmallCd() {
		return smallCd;
	}

	public void setSmallCd(String smallCd) {
		this.smallCd = smallCd;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getKorAls() {
		return korAls;
	}

	public void setKorAls(String korAls) {
		this.korAls = korAls;
	}

	public String getEngAls() {
		return engAls;
	}

	public void setEngAls(String engAls) {
		this.engAls = engAls;
	}

	public String getSysDiv() {
		return sysDiv;
	}

	public void setSysDiv(String sysDiv) {
		this.sysDiv = sysDiv;
	}

	public long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getOldCd() {
		return oldCd;
	}

	public void setOldCd(String oldCd) {
		this.oldCd = oldCd;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getLangSet() {
		return langSet;
	}

	public void setLangSet(String langSet) {
		this.langSet = langSet;
	}
	
}
