/*************************************************************
  프로그램명 : NxSecureInterceptor.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.util.Utilities;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;

//public class NxSecureInterceptor extends HandlerInterceptorAdapter {
public class NxSecureInterceptor implements HandlerInterceptor {
	
	private final String sName = "sk";
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		if(session == null)
			return;
		String strKey = (String)session.getAttribute(sName);
		if(strKey ==null || strKey.length()==0)
			session.setAttribute(sName,Utilities.getUUID());
		if (RequestUtils.isAjaxRequest(request)) {
			return;
		}
		if(request.getRequestURI().endsWith("login"))
			return;
		ModelMap model = modelAndView.getModelMap();
		model.addAttribute(sName,strKey);
	}
}
