/*************************************************************
  프로그램명 : NxFBindingResultException.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.validation.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class NxFBindingResultException extends Exception {

	private static final long serialVersionUID = -3171388764845153614L;
	
	private final BindingResult result;
	
	private boolean groupedField;
	
	public NxFBindingResultException(BindingResult result) {
		this.result = result;
	}
	
	public NxFBindingResultException(BindingResult result, boolean groupedField) {
		this.result = result;
		this.groupedField = groupedField;
	}
	
	public BindingResult getBindingResult() {
		return result;
	}
	
	public List<FieldError> getFieldErrors() {
		return result.getFieldErrors();
	}
	
	public List<String> getErrorFields() {
		List<String> fields = new ArrayList<String>();
		
		List<FieldError> errors = getFieldErrors();
		for (FieldError error : errors) {
			fields.add(error.getField());
		}
		
		return fields;
	}
	
	public String[] getErrorCodes(String field) {
		return result.getFieldError(field).getCodes();
	}

	public boolean isGroupedField() {
		return groupedField;
	}

	public void setGroupedField(boolean groupedField) {
		this.groupedField = groupedField;
	}

}
