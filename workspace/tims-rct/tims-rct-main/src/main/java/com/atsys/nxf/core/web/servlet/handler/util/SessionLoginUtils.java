/*************************************************************
  프로그램명 : SessionLoginUtils.java
  설명 : Http Session Utils
  작성자 : frida
  소속 : AT-SYS
  일자 : 2013.07.08
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '13.07.08          신규작성                 AT-SYS
*************************************************************/
package com.atsys.nxf.core.web.servlet.handler.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.vo.LoginUser;

public class SessionLoginUtils {
	
	public final static String LOGIN_USER_SESSION_NAME = "tims.login.user";
	public final static String LOGIN_IMPERSON_SESSION_NAME = "tims.imperson.user";
	public final static String GUEST = "GUEST";
	
	public static boolean isLogin() throws Exception {
		return isLogin(getRequest());
	}
	
	public static boolean isLogin(HttpServletRequest request) throws Exception {
		if(request != null) {
			return isLogin(request.getSession());
		}
		return false;
	}
	
	public static boolean isImperonate() throws Exception {
		return isImperonate(Utilities.getSession());
	}
	
	public static boolean isImperonate(HttpSession session) throws Exception {
		if(session == null) {
			return false;
		}
		return session.getAttribute(LOGIN_IMPERSON_SESSION_NAME) != null;
	}
	
	public static boolean isLogin(HttpSession session) throws Exception {
		if(session != null) {
			LoginUser user = getLoginUser();
			if(user != null && user.isLogin()) {
				return true;
			}
		}
		return false;
	}
	
	public static LoginUser getLoginUser() throws Exception {
		return getLoginUser(getRequest());
	}
	
	public static LoginUser getLoginUser(HttpServletRequest request) throws Exception {
		if(request != null) {
			return getLoginUser(request.getSession(false));
		}
		return new LoginUser();
	}
	
	public static LoginUser getImpersonateUser() throws Exception{
		return getImpersonateUser(getRequest());
	}
	
	public static LoginUser getImpersonateUser(HttpServletRequest request) {
		if(request != null) {
			return getImpersonateUser(request.getSession(false));
		}
		return null;
	}
	
	public static LoginUser getImpersonateUser(HttpSession session) {
		return (LoginUser)session.getAttribute(LOGIN_IMPERSON_SESSION_NAME);
	}
	
	public static LoginUser getLoginUser(HttpSession session) throws Exception {
		LoginUser user = null;
		if(session != null) {
			if(session.getAttribute(LOGIN_IMPERSON_SESSION_NAME) != null) {
				user = (LoginUser)session.getAttribute(LOGIN_IMPERSON_SESSION_NAME);
			} else if(session.getAttribute(LOGIN_USER_SESSION_NAME) != null) {
				user = (LoginUser)session.getAttribute(LOGIN_USER_SESSION_NAME);
			}
			if(user == null) {
				user = new LoginUser();
				setLoginUser(user);
			}
			return user;
		}
		return new LoginUser();
	}
	
	// 2023.10.04. 권영욱 세션에서 실제 로그인 사용자 정보를 리턴
	public static LoginUser getOrgLoginUser(HttpSession session) throws Exception {
		LoginUser user = null;
		if(session != null) {
			if(session.getAttribute(LOGIN_USER_SESSION_NAME) != null) {
				user = (LoginUser)session.getAttribute(LOGIN_USER_SESSION_NAME);
			}
			if(user == null) {
				user = new LoginUser();
				setLoginUser(user);
			}
			return user;
		}
		return new LoginUser();
	}
	
	public static HttpSession setImpersonate(LoginUser user) {
		return setImpersonate(user,getRequest().getSession(true));
	}
	
	public static HttpSession setImpersonate(LoginUser user,HttpSession session) {
		session.removeAttribute(LOGIN_IMPERSON_SESSION_NAME);
		session.setAttribute(LOGIN_IMPERSON_SESSION_NAME, user);
		return session;
	}
	
	public static HttpSession setLoginUser(LoginUser user) {
		if(getRequest() != null) {
			HttpSession session = getRequest().getSession(true);
			session.setAttribute(LOGIN_USER_SESSION_NAME, user);
			return session;
		}
		return null;
	}
	
	public static String getSessionId() {
		HttpSession session = getRequest().getSession(true);
		return session.getId();
	}
	
	public static void removeLoginUser() {
		if(getRequest() != null) {
			HttpSession session = getRequest().getSession(false);
			removeLoginUser(session);
		}
	}
	
	public static void removeLoginUser(HttpSession session) {
		if(session == null) {
			return;
		}
		if(session.getAttribute(LOGIN_IMPERSON_SESSION_NAME) != null) {
			session.removeAttribute(LOGIN_IMPERSON_SESSION_NAME);
		} else {
			session.removeAttribute(LOGIN_USER_SESSION_NAME);
			session.invalidate();
		}
	}
	
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes servletContainer = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(servletContainer != null) {
			return servletContainer.getRequest();
		} else {
			return null;
		}
	}
	
}
