/*************************************************************
  프로그램명 : ValidatorHelper.java
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
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springmodules.validation.commons.DefaultBeanValidator;

//@Component("validatorHelper")
public class ValidatorHelper {
	
	public enum BuildType {
		NORMAL, AJAX;
	
		@Override
		public String toString() {
			switch (this) {
			case NORMAL:
				return "Normal";
			case AJAX:
				return "Ajax";

			default:
				return name();
			}
		}
	}
	
	public final static String VALIDATION_MESSAGE = "validationMessage";
	
	@Resource(name = "beanValidator")
    private DefaultBeanValidator beanValidator;

	@Resource(name = "messageSource")
	private AbstractMessageSource messageSource;
    
    public boolean validate(Object object, BindingResult bindingResult) {
    	if (bindingResult == null) {
    		return true;
    	}
    	
    	beanValidator.validate(object, bindingResult);
    	
		if (bindingResult.hasErrors()) {
			return false;
		}
		
		return true;
    	
    }
    
    public Map<String, Object> buildMappedValidationMessage(BindingResult bindingResult) {
    	return buildMappedValidationMessage(bindingResult, null);
    }
    
    public Map<String, Object> buildMappedValidationMessage(BindingResult bindingResult, String fieldName) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String message = buildValidationMessage(bindingResult, fieldName);
    	
    	if (message != null) {
    		map.put(VALIDATION_MESSAGE, message);
    	}
    	
    	return map;
    	
    }

    public String buildValidationMessage(BindingResult bindingResult) {
    	return buildValidationMessage(bindingResult, null);
    }

    public String buildValidationMessage(BindingResult bindingResult, String fieldName) {
    	Locale locale = LocaleContextHolder.getLocale();
    	
    	String message = null;
		StringBuilder builder = new StringBuilder();
		
		List<FieldError> errors = bindingResult.getFieldErrors();
		for (FieldError error : errors) {
			if (fieldName != null && fieldName.equals(error.getField())) {
				message = messageSource.getMessage(error.getCode(), error.getArguments(), locale);
				break;
			} else {
				builder.append(messageSource.getMessage(error.getCode(), error.getArguments(), locale)).append("\n");
			}
		}
		
		if (message == null && builder.length() > 0) {
			message = builder.toString();
		}
		
		return message;
    }
}
