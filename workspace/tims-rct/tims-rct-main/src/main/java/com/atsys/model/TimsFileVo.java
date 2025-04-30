package com.atsys.model;

import com.atsys.base.util.Utilities;

public class TimsFileVo extends FileVo {
	public static enum FileStatus {
		SAVED('S'), ADDED('A');

		private char code;

		FileStatus(char code) {
			this.code = code;
		}

		public char getCode() {
			return code;
		}

	}
	public static enum FileType {
		IMAGE('I'), AUDIO('A'), VIDEO('V'), TEXT('T'), ETC('E'), NONE('N');

		private char code;

		FileType(char code) {
			this.code = code;
		}

		public char getCode() {
			return code;
		}

	}

	public static enum Division{
		UCC("100"), SUBTTILE("200"), POSTER("201"), TRANSCODED("300"), THUMBNAIL("301"), ORIGINAL_TRANSCODED("302"), ATTATCH("000");

		private String code;

		Division(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 8517849701156832690L;
    private String div;
    private char fileType;
    private String thmbFileNm;
	
    private String cntsType;//
    private char fileStats;
    private String nm;
	private String url;
	private String thmbUrl;
	private String delUrl;
	private String delType;
	private String err;
	
	public String getDiv() {
		return div;
	}
	public void setDiv(String division) {
		this.div= division;
	}
	public char getFileType() {
		return fileType;
	}
	public void setFileType(char fileType) {
		this.fileType = fileType;
	}
	
	public String getThmbFileNm() {
		return thmbFileNm;
	}
	public void setThmbFileNm(String thmbFileNm) {
		this.thmbFileNm = thmbFileNm;
	}

	
	public String getFileNm() {
		return getOrigFileNm();
	}
	
	public final long getSize() {
		return getFileSiz();
	}
	public final void setSize(long size) {
		setFileSiz(size);
	}
	public final String getUrl() {
		return url;
	}
	public final void setUrl(String url) {
		this.url = url;
	}

	public final String getFormattedFileSize() {
		return Utilities.getFileSizString(getSize());
	}

	public final void setFileNm(String fileNm) {
		setOrigFileNm(fileNm);
		setNm(fileNm);
	}
	@Override
	public final void setFileSiz(Long fileSize) {
		super.setFileSiz(fileSize);
//		setSize(fileSize);
	}
	public String getCntsType() {
		return cntsType;
	}
	public void setCntsType(String cntsType) {
		this.cntsType = cntsType;
	}
	public char getFileStats() {
		return fileStats;
	}
	public void setFileStats(char fileStats) {
		this.fileStats = fileStats;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getThmbUrl() {
		return thmbUrl;
	}
	public void setThmbUrl(String thmbUrl) {
		this.thmbUrl = thmbUrl;
	}
	public String getDelUrl() {
		return delUrl;
	}
	public void setDelUrl(String delUrl) {
		this.delUrl = delUrl;
	}
	public String getDelType() {
		return delType;
	}
	public void setDelType(String delType) {
		this.delType = delType;
	}
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	
}
