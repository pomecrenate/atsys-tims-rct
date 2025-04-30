/*************************************************************
  프로그램명 : ValidationMessage.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.validation.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationMessage implements Serializable {

	private static final long serialVersionUID = -1934313859929040867L;

	private String field;
	
	private final List<String> messages;
	
	public ValidationMessage(String field) {
		this.field = field;
		this.messages = new ArrayList<String>();
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<String> getMessages() {
		List<String> ret = null;
		if(this.messages != null) {
			ret = new ArrayList<String>();
			ret = this.messages; 
		}
		return ret; 
	}

	public void addMessage(String message) {
		this.messages.add(message);
	}
}
