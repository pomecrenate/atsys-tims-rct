package com.atsys.model;

public class TbNtcDocVo extends TbNtcDocBo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2894281349300745L;
	
	private String ntcDocCd;
	private String docSeq;
	private String docCd;
	private String docDesc;
	private String fileExtn;
	private String requiredYn;
	
	public String getNtcDocCd() {
		return ntcDocCd;
	}
	public void setNtcDocCd(String ntcDocCd) {
		this.ntcDocCd = ntcDocCd;
	}
	public String getDocSeq() {
		return docSeq;
	}
	public void setDocSeq(String docSeq) {
		this.docSeq = docSeq;
	}
	public String getDocCd() {
		return docCd;
	}
	public void setDocCd(String docCd) {
		this.docCd = docCd;
	}
	public String getDocDesc() {
		return docDesc;
	}
	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}
	public String getFileExtn() {
		return fileExtn;
	}
	public void setFileExtn(String fileExtn) {
		this.fileExtn = fileExtn;
	}
	public String getRequiredYn() {
		return requiredYn;
	}
	public void setRequiredYn(String requiredYn) {
		this.requiredYn = requiredYn;
	}
	
	
	

}
