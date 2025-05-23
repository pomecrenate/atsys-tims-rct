package com.atsys.model;

import java.io.Serializable;

import com.atsys.base.model.BaseVo;

public class TbBrdPostBo extends BaseVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 867451385547621589L;
	
	private String postCd;
	private String brdCd;
	private String brdType;
	private String category;
	private String postTitle;
	private String postCnts;
	private String uprPostCd;
	private String receiptNo;
	private String answerYn;
	private String writerNm;
	private String writerPhone;
	private String pw;
	private String viewCnt;
	private String importantYn;
	private String sortOrder;
	private String brdPostStatus;
	private String upldFileCd;
	
	
	public String getPostCd() {
		return postCd;
	}
	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}
	public String getBrdCd() {
		return brdCd;
	}
	public void setBrdCd(String brdCd) {
		this.brdCd = brdCd;
	}
	public String getBrdType() {
		return brdType;
	}
	public void setBrdType(String brdType) {
		this.brdType = brdType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostCnts() {
		return postCnts;
	}
	public void setPostCnts(String postCnts) {
		this.postCnts = postCnts;
	}
	public String getUprPostCd() {
		return uprPostCd;
	}
	public void setUprPostCd(String uprPostCd) {
		this.uprPostCd = uprPostCd;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getAnswerYn() {
		return answerYn;
	}
	public void setAnswerYn(String answerYn) {
		this.answerYn = answerYn;
	}
	public String getWriterNm() {
		return writerNm;
	}
	public void setWriterNm(String writerNm) {
		this.writerNm = writerNm;
	}
	public String getWriterPhone() {
		return writerPhone;
	}
	public void setWriterPhone(String writerPhone) {
		this.writerPhone = writerPhone;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getImportantYn() {
		return importantYn;
	}
	public void setImportantYn(String importantYn) {
		this.importantYn = importantYn;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getBrdPostStatus() {
		return brdPostStatus;
	}
	public void setBrdPostStatus(String brdPostStatus) {
		this.brdPostStatus = brdPostStatus;
	}
	public String getUpldFileCd() {
		return upldFileCd;
	}
	public void setUpldFileCd(String upldFileCd) {
		this.upldFileCd = upldFileCd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
