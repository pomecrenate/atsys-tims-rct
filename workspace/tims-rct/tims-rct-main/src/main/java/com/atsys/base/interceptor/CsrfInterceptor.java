/*************************************************************
  프로그램명 : CsrfInterceptor.java
  설명 : CSRF TOKEN 체크 인터셉터
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

import org.springframework.web.servlet.HandlerInterceptor;

import com.atsys.base.app.controller.TimsErrorController;
import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.vo.LoginUser;
import com.atsys.nxf.core.web.servlet.handler.util.HandlerUtils;

public class CsrfInterceptor implements HandlerInterceptor { 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 적용 제외 클래스
		if( HandlerUtils.isInstance(handler, TimsErrorController.class)) return true;

		LoginUser loginUser = LoginUser.getLoginUser();
		String csrfTokenId = loginUser.getCsrfTokenId();
		
		if(Utilities.isEmpty(request.getParameter(csrfTokenId))) {
			return true;
		}

		// 화면에서 넘긴 CSRF TOKEN 값
		String viewCsrfToken = request.getParameter(csrfTokenId);
		// 세션에 있는 CSRF TOKEN 값
		String sessionCsrfToken = loginUser.getCsrfToken();
		if(!sessionCsrfToken.equalsIgnoreCase(viewCsrfToken)) {
			response.sendError(535);
			return false;
		}
		
		return true;
	}
}