/*************************************************************
  프로그램명 : LoginCheckInterceptor.java
  설명 : 로그인 체크 인터셉터
  작성자 : K.Y.W.
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19          신규작성                 AT-SYS 
*************************************************************/
package com.atsys.base.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import com.atsys.base.app.login.LoginService;
import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.vo.LoginUser;

public class LoginCheckInterceptor implements HandlerInterceptor {
	 
	
//	@Resource(name = "mem1100eService")
//	private Mem1100eService mem1100eService;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 세션타임 확인
		 */
		boolean bSession = false;
		if(bSession) {
	        // 현재타임 -> 1/1000초의 값을 리턴
	        long currentTime = System.currentTimeMillis();
	        logger.debug("현재타임(currentTime) : " + currentTime);
	        // 세션타임
	        int maxInactiveInterval = request.getSession().getMaxInactiveInterval();
	        logger.debug("세션타임(maxInactiveInterval) : " + maxInactiveInterval);
	        // 세션만료타임
	        long expiryTime = currentTime + (maxInactiveInterval * 1000);
	        logger.debug("세션만료타임(expiryTime) : " + expiryTime);
		}
		
		/*
		 * loginCheckInterceptor를 예외처리
		 */
//		if(UrlUtil.isInterceptorExcept()) {
//			return true;
//		}
		
		// 모바일여부
		request.setAttribute("mobileYn", Utilities.isMobile() ? "Y" : "N");
		request.setAttribute("httpServletResponse", response);
		
		LoginUser loginUser = LoginUser.getLoginUser();
		
		boolean isLogin = loginUser != null && loginUser.isLogin();
		boolean isLoginOk = false;
		
		if( !isLoginOk ) {
			if( isLogin ) {
				// 로그인 사용자라면 중복로그인 체크를 한다.
				boolean bPass = false;
				if( "Y".equalsIgnoreCase(loginUser.getDupLoginChkYn()) ) {
					bPass = true;
				}
				
				// 2023.10.04. 권영욱 - 관리자가 회원으로 로그인 한 경우 중복로그인 체크 제외한다.
				if( "Y".equalsIgnoreCase(loginUser.getImpersonateYn()) ) {
					bPass = false;
				}
				
				if( bPass ) {
					// DB에 저장된 세션id
//					String dbSessionId = mem1100eService.selectLoginSessionId(loginUser);
					
					// HttpSession에 저장된 세션id
					String sessionId = Utilities.getSessionId();
					
					// DB에 저장된 세션id과 HttpSession에 저장된 세션id를 비교한다.
					/*
					 * if( Utilities.isNotEmpty(dbSessionId) && !sessionId.equals(dbSessionId) ) {
					 * // 세션id가 다른 경우 로그아웃 처리한다. loginService.logout(request);
					 * request.setAttribute("loginError", 534);
					 * if(RequestUtils.isAjaxRequest(request)) { response.sendError(534,
					 * "invalid session id"); return false; } }
					 */
				}
			}
		}
		
		return true;
	}
}
