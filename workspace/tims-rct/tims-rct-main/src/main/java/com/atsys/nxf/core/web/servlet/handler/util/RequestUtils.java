/*************************************************************
  프로그램명 : RequestUtils.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.servlet.handler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jfree.util.Log;

import com.atsys.base.util.Utilities;

public class RequestUtils {

	public static final String		ATTR_VIEW_LAYOUT		= "tims.view.layout";
	public static final String		ATTR_DETAIL_AUTH		= "tims.auth.detail";

	public static final String		HEADER_REVERSE_PREFIX	= "!";
	public static final String		HEADER_X_REQ_WITH		= "x-requested-with"; 
	
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader(HEADER_X_REQ_WITH));
	}
	
	public static String getReferer(HttpServletRequest request){
		String referer = request.getHeader("Referer");
		referer = (referer != null) ? referer : "";
		String regex = "^"+ request.getScheme() + "://((\\w)(\\.))*"+ request.getServerName() + "([:]\\d[/])*(\\S)*$";
		Matcher matcher = Pattern.compile(regex).matcher(referer);
		if( !matcher.matches())
			referer = request.getContextPath();
		return referer;
	}
	
	public static String getViewLayout(HttpServletRequest request) {
		if( request.getAttribute(ATTR_VIEW_LAYOUT) != null)
			return (String)request.getAttribute(ATTR_VIEW_LAYOUT);
		else 
			return "";
	}
	public static String getRequestBody() {
		HttpServletRequest request = Utilities.getRequest();
		
		String body = (String) request.getAttribute("requestBody");
		if(body!=null)
			return body;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while (true) {
					bytesRead = bufferedReader.read(charBuffer);
					if(bytesRead>0) {
						stringBuilder.append(charBuffer, 0, bytesRead);
					}
					else {
						break;
					}
				}
			}
		} catch (IOException ex) {
			Utilities.trace(ex);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					Log.info("info to Exception");
				}
			}
		}
		body=stringBuilder.toString().trim();
		request.setAttribute("requestBody", body);
		return body;
	} 

}
