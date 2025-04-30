/*************************************************************
  프로그램명 : SessionTimeoutCookieFilter.java
  설명 : 세션타임아웃 필터
  작성자 : 김종엽
  소속 : AT-SYS
  일자 : 2020.11.03
  프로그램설명
*************************************************************/
package com.atsys.nxf.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionTimeoutCookieFilter implements Filter {
	
    @SuppressWarnings("unused")
    private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResp = (HttpServletResponse)response;
        HttpServletRequest httpReq = (HttpServletRequest)request;
        
        // 현재타임 -> 1/1000초의 값을 리턴
        long currentTime = System.currentTimeMillis(); 
        
        // 세션타임
        int maxInactiveInterval = httpReq.getSession().getMaxInactiveInterval(); 

        // 세션만료타임
        long expiryTime = currentTime + (maxInactiveInterval * 1000);  
        
        Cookie cookie = new Cookie("latestTouch", "" + currentTime);
 
        cookie.setPath("/");
        httpResp.addCookie(cookie);
 
        cookie = new Cookie("sessionExpiry", "" + expiryTime); 
 
        cookie.setPath("/");
 
        httpResp.addCookie(cookie);
 
        chain.doFilter(request, response);
	}
 
    @Override
    public void destroy() {
    }
 
    @Override
    public void init(FilterConfig config) throws ServletException {
    	this.config = config;
    }
}

