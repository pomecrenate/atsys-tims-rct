/*************************************************************
  프로그램명 : BindingResultHelper.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.atsys.nxf.core.validation.exception.NxFBindingResultException;

@Component("bindingResultHelper")
public class BindingResultHelper {

	@Resource(name = "messageSource")
	private AbstractMessageSource messageSource;
	 
	
	public void resolveBindingResultAsException(BindingResult bindingResult, boolean groupedField) throws NxFBindingResultException {
		
		resolveBindingResult(bindingResult, groupedField, true);
	}

	protected Map<String, ValidationMessage> resolveBindingResult(BindingResult bindingResult, boolean groupedField, boolean throwException) throws NxFBindingResultException {

		if (throwException) { 
			
			throw new NxFBindingResultException(bindingResult, groupedField);
		} 
		
		return buildValidationMessage(bindingResult, groupedField);
	}

	public Map<String, ValidationMessage> buildValidationMessage(BindingResult bindingResult, boolean groupedField) {
		
		Map<String, ValidationMessage> messageMap = new HashMap<String, ValidationMessage>();
		
		List<FieldError> errors = bindingResult.getFieldErrors();
		
		String field = null;
		String message = null; 
		ValidationMessage messages = null;

		for (FieldError error : errors) {
			messages = null;

			field = error.getField(); 
			
			if (groupedField) {
				messages = messageMap.get(field);
			}
			
			if (messages == null) {
				messages = new ValidationMessage(field);
				messageMap.put(field, messages);
			}
			
			message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			messages.addMessage(message);
			 
		} 
		
		return messageMap;
	}
}
