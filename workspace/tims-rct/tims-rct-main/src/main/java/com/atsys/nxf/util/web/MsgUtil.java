/*************************************************************
  프로그램명 : MsgUtil.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.util.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.jfree.util.Log;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


public final class MsgUtil 
{
	public static String getMessage(AbstractMessageSource messageSource, String messageCode, Object[] params) 
	{
		String message = "";
		Locale locale = new Locale("ko", "KR");

		try 
		{
			message = messageSource.getMessage(messageCode, params, locale);
		} 
		catch (IllegalStateException ex) 
		{ 
			Log.info("info to IllegalStateException");
			message = messageCode + "(undefined)";
		} 
		catch (Exception ex) 
		{ 
			Log.info("info to Exception");
			message = messageCode + "(undefined)";
		}
		
		return message;
	}
	
	public static String getMessage(String messageCode, Object[] params) 
	{
		String message = "";
		Locale locale = new Locale("ko", "KR");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
		AbstractMessageSource messageSource = (AbstractMessageSource) ctx.getBean("messageSource");

		try 
		{
			message = messageSource.getMessage(messageCode, params, locale);
		} 
		catch (IllegalStateException ex) 
		{ 
			Log.info("info to IllegalStateException");
			message = messageCode + "(undefined)";
		} 
		catch (Exception ex) 
		{ 
			Log.info("info to Exception");
			message = messageCode + "(undefined)";
		}
		
		return message;
	}
}
