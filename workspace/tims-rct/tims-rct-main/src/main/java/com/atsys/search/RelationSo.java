/*************************************************************
  프로그램명 : RelationSo.java
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
public class RelationSo extends BaseBean {

	private static final long serialVersionUID = -8746647844029188860L;

    private String column;
    private List<String> val;

	public RelationSo(){
		val = new ArrayList<String>();
	};

	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public List<String> getVal() {
		List<String> ret = null;
		if(this.val != null) {
			ret = new ArrayList<String>();
			ret = this.val; 
		}
		return ret;
//		return val;
	}
	public void setVal(List<String> val) {
		if(val != null) {
			this.val = new ArrayList<String>();
			this.val.addAll(val);
		}
//		this.val = val;
	}

}
