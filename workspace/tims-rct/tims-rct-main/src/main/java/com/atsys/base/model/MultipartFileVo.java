/*************************************************************
  프로그램명 : MultipartFileVo.java
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

import com.atsys.nxf.core.web.multipart.MutilpartFileInfo;

/**
 * @Class Name : MultipartFileInfo.java
 * @Description : MultipartFileInfo class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 7. 19.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public class MultipartFileVo extends MutilpartFileInfo {
	
	private String fileGroupKey;
    private String division;

	public final String getFileGroupKey() {
		return fileGroupKey;
	}
	public final void setFileGroupKey(String fileGroupKey) {
		this.fileGroupKey = fileGroupKey;
	}
	public final String getDivision() {
		return division;
	}
	public final void setDivision(String division) {
		this.division= division;
	}
    
}
