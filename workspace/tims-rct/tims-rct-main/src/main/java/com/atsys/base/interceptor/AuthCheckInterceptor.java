/*************************************************************
  프로그램명 : AuthCheckInterceptor.java
  설명 : 권한 체크 인터셉터
  작성자 : K.Y.W.
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19          신규작성                 AT-SYS 
*************************************************************/
package com.atsys.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.usr.IAuthSupport;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;
import com.atsys.nxf.core.web.servlet.handler.util.SessionLoginUtils;

public class AuthCheckInterceptor implements HandlerInterceptor {
	
	@Autowired(required=false)
	private IAuthSupport support; 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (support == null) {
			return false;
		}
		
		// 이후에 해당 interceptor를 타지 않아야 하는 목록을 urlutil에 정리하여 주석 살리기
		/*
		 * authCheckInterceptor를 타지 않도록...
		 * */
//		if(UrlUtil.isInterceptorExcept()) {
//			return true;
//		}
		
		boolean valid = support.hasAuth(request, response, ((HandlerMethod)handler));
		if(!valid) {
			int errorCode = (int) (request.getAttribute("errorCode") == null ? 0 : request.getAttribute("errorCode"));
			boolean checked = false;
			boolean bAjaxRequest = RequestUtils.isAjaxRequest(request);
			boolean bLogin = SessionLoginUtils.isLogin();
			if(bAjaxRequest || checked) {
				if(bLogin) {
					if(errorCode > 0) {
						response.sendError(errorCode);
					} else {
						response.sendError(532, "AjaxSession need login.");
					}
				} else {
					response.sendError(errorCode, "AjaxSession is timeout.");
				}
			} else {
				if(bLogin) {
					if(Utilities.isAdminContext()) {
						//2차 인증(관리자 IP 미등록 계정)
						if(errorCode == 533) {
							request.getRequestDispatcher(request.getContextPath() + request.getServletPath() + "/authenticate/alogin").forward(request, response);
							return false;
						}
						
						if(errorCode > 0) {
							response.sendError(errorCode);
						} else {
							response.sendError(532, "권한 없음");
						}
					} else {
						request.getRequestDispatcher(request.getContextPath() + request.getServletPath() + "/").forward(request, response);
					}
				} else { 
					// 2023.12.15 KYW 로그인 권한 없는 메뉴인 경우
					if(errorCode > 0) {
						response.sendError(errorCode);
					}
					request.getRequestDispatcher(request.getContextPath() + request.getServletPath() + "/login").forward(request, response);
				}
			}
			return false;
		}
		return true;
	}

}

