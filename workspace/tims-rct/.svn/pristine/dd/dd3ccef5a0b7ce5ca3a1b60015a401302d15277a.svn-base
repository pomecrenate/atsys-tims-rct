/*************************************************************
  프로그램명 : MutilpartFileInfo.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.multipart;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class MutilpartFileInfo {

	// for upload
	private CommonsMultipartFile[] files;
	
	// for download
	private String[] filenames;

	public CommonsMultipartFile[] getFiles() {
		CommonsMultipartFile[] ret = null;
		if(this.files != null) {
			ret = new CommonsMultipartFile[this.files.length];
			ret = this.files; 
		}
		return ret;
//		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		if(files != null) {
			this.files = new CommonsMultipartFile[files.length];
			int i = 0;
			for(CommonsMultipartFile file : files) {
				this.files[i] = file;
				i++;
			}
		}
//		this.files = files;
	}

	public String[] getFilenames() {
		String[] ret = null;
		if(this.filenames != null) {
			ret = new String[this.filenames.length];
			int i = 0;
			for(String str : this.filenames) {
				ret[i] = str;
				i++;
			}
		}
		return ret; 
	}

	public void setFilenames(String[] filenames) {
		if(filenames != null) {
			this.filenames = new String[filenames.length];
			int i = 0;
			for(String str : filenames) {
				this.filenames[i] = str;
			}
		} 
	}
	
	
}
