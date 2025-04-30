/*************************************************************
  프로그램명 : NxfAjaxException.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;


public class NxfAjaxException extends NxfException {

	private static final long serialVersionUID = -6450153375005705138L;

	public NxfAjaxException(Exception exception) {
		super(exception);
	}

	public NxfAjaxException(MessageSource messageSource, String messageKey, Object[] messageParams) {
		super(messageSource, messageKey, messageParams, null, null, Locale.getDefault());
	}

	public NxfAjaxException(MessageSource messageSource, String messageKey, Exception exception) {
		super(messageSource, messageKey, null, null, exception, Locale.getDefault());
	}

	public NxfAjaxException(MessageSource messageSource, String messageKey, Exception exception, Locale locale) {
		super(messageSource, messageKey, null, null, exception, locale);
	}

	public NxfAjaxException(MessageSource messageSource, String messageKey, Object[] messageParams,
	        Exception exception, Locale locale) {
		super(messageSource, messageKey, messageParams, null, exception, locale);
	}

	public NxfAjaxException(MessageSource messageSource, String messageKey, Object[] messageParams,
	        Exception exception) {
		super(messageSource, messageKey, messageParams, null, exception, Locale.getDefault());
	}

	public NxfAjaxException(MessageSource messageSource, String messageKey, Object[] messageParams,
	        String defaultMessage, Exception exception) {
		super(messageSource, messageKey, messageParams, defaultMessage, exception, Locale.getDefault());
	}
	
	public NxfAjaxException(MessageSource messageSource, String messageKey, Object[] messageParams, 
			String defaultMessage, Exception exception, Locale locale) {
		super(messageSource, messageKey, messageParams, defaultMessage, exception, locale);
	}

}
