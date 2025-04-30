/*************************************************************
  프로그램명 : NxfException.java
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.util.Assert;

public class NxfException extends Exception {

	private static final long serialVersionUID = -6450153375005705138L;
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private Exception exception;
	private String message;
	
	public NxfException(Exception exception) {
		this.exception = exception;
	}

	public NxfException(MessageSource messageSource, String messageKey, Object[] messageParams) {
		this(messageSource, messageKey, messageParams, null, null, Locale.getDefault());
	}

	public NxfException(MessageSource messageSource, String messageKey, Exception exception) {
		this(messageSource, messageKey, null, null, exception, Locale.getDefault());
	}

	public NxfException(MessageSource messageSource, String messageKey, Exception exception, Locale locale) {
		this(messageSource, messageKey, null, null, exception, locale);
	}

	public NxfException(MessageSource messageSource, String messageKey, Object[] messageParams,
	        Exception exception, Locale locale) {
		this(messageSource, messageKey, messageParams, null, exception, locale);
	}

	public NxfException(MessageSource messageSource, String messageKey, Object[] messageParams,
	        Exception exception) {
		this(messageSource, messageKey, messageParams, null, exception, Locale.getDefault());
	}

	public NxfException(MessageSource messageSource, String messageKey, Object[] messageParams,
	        String defaultMessage, Exception exception) {
		this(messageSource, messageKey, messageParams, defaultMessage, exception, Locale.getDefault());
	}
	
	@SuppressWarnings("deprecation")
	public NxfException(MessageSource messageSource, String messageKey, Object[] messageParams, 
			String defaultMessage, Exception exception, Locale locale) { 
		Assert.notNull(messageSource);
		this.exception = exception;
		this.message = messageSource.getMessage(messageKey, messageParams, defaultMessage, locale);
	}
	
	public Exception getOriginalException() {
		return exception;
	}

	@Override
	public String getMessage() {
		if (message != null) {
			return message;
		}
		
		return exception == null ? null : exception.getMessage();
	}

}
