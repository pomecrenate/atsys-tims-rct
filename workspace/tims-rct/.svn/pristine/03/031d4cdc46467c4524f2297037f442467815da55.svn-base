/*************************************************************
  프로그램명 : CodeSelectionSo.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.search;

import java.util.ArrayList;
import java.util.List;

import com.atsys.nxf.app.vo.BaseBean;

/**
 * @Class Name : CodeSelectionSo.java
 * @Description : CodeSelectionSo class
 * @Modification Information
 *
 * @author frida
 * @since 2014. 4. 29.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public class CodeSelectionSo extends BaseBean {

	private static final long serialVersionUID = 4058780668619743155L;

	private List<CodeSelection> codeColls;

	public CodeSelectionSo(){
		codeColls = new ArrayList<CodeSelection>();
	}

	public List<CodeSelection> getCodeColls() {
		List<CodeSelection> ret = null;
		if(this.codeColls != null) {
			ret = new ArrayList<CodeSelection>();
			ret = this.codeColls; 
		}
		return ret;
//		return codeColls;
	}

	public void setCodeColls(List<CodeSelection> codeColls) {
		if(codeColls != null) {
			this.codeColls = new ArrayList<CodeSelection>();
			this.codeColls.addAll(codeColls);
		}
//		this.codeColls = codeColls;
	}
	
	public void addCodeColls(CodeSelection cs){
		this.codeColls.add(cs);
	}

}
