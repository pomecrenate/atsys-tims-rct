/*************************************************************
  프로그램명 : CodeCollectionInterceptor.java
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.code.ICodeSupport;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;
 
public class CodeCollectionInterceptor implements HandlerInterceptor {

	@Autowired(required = false)
	private ICodeSupport support;

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			if(!RequestUtils.isAjaxRequest(request) && Utilities.isNotEmpty(request.getParameter("msgTicket"))) {
				Utilities.sendMessage("00000000");
			}
		}catch (IllegalStateException e) {
			logger.debug("info to preHandle Exception : ", e);
		}catch (Exception e) {
			logger.debug("info to preHandle Exception : ", e);
		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (modelAndView == null) {
			// logger.debug("ModelAndView doesn't exist.");
			return;
		}

		if (RequestUtils.isAjaxRequest(request) && !modelAndView.hasView()) {
			// logger.debug("Ajax request. No need for code.");
			return;
		}

		ModelMap model = modelAndView.getModelMap();
		model.addAllAttributes(support.getCodeList(request, response,
				(HandlerMethod) handler));
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		try {
			if (!RequestUtils.isAjaxRequest(request)
					&& Utilities.isNotEmpty(request.getParameter("msgTicket")))
				Utilities.sendMessage("99999999");

		}catch (IllegalStateException e) {
			logger.debug("info to afterCompletion Exception : ", e);
		}catch (Exception e) {
			logger.debug("info to afterCompletion Exception : ", e);
		} 
	}
}
