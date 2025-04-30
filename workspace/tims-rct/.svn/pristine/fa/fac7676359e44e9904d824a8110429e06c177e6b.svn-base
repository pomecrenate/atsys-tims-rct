/*************************************************************
  프로그램명 : ParameterLoggingFilter.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class ParameterLoggingFilter implements Filter 
{
	private Logger LOGGER = Logger.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		LOGGER.debug("ParameterLoggingFilter init...");
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		String name;
		StringBuffer tmpBuffer = new StringBuffer();
		HttpServletRequest req = (HttpServletRequest) request;
		
		tmpBuffer.append("ServletPath : " + req.getServletPath() + "\n");
		tmpBuffer.append("Method : " + req.getMethod() + "\n");

		Enumeration<String> headers = req.getHeaderNames();

		tmpBuffer.append(">>>>>>>>>>>>>>>>>>> Header <<<<<<<<<<<<<<<<<<" + "\n");
		
		while (headers.hasMoreElements())
		{
			name =  headers.nextElement();
			
			tmpBuffer.append(name + ":" + req.getHeader(name) + "\n");
		}

		Enumeration<String> names = req.getParameterNames();

		tmpBuffer.append(">>>>>>>>>>>>>>>>>>> Parameter <<<<<<<<<<<<<<<<<<" + "\n");
		
		while (names.hasMoreElements())
		{
			name =  names.nextElement();
			
			tmpBuffer.append(name + ":" + req.getParameter(name) + "\n");
		}
		
		LOGGER.debug(tmpBuffer.toString());
		
//		byte[] tmpByte = new byte[1024]; 
//		req.getInputStream().read(tmpByte);
//		LOGGER.debug(new String(tmpByte));
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {} 
}
