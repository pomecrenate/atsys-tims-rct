package com.atsys.base.web.handler;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.atsys.nxf.app.vo.LoginUser;
import com.atsys.nxf.core.web.servlet.handler.util.SessionLoginUtils;

@WebListener
public class TimsListener implements HttpSessionListener {
	protected Logger LOGGER = Logger.getLogger(this.getClass());
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
//		String sessionId = Utilities.getUniqId(20);
//		se.getSession().setAttribute("timsSessionId", sessionId);
//		LOGGER.debug("session start session id is [" + se.getSession().getId() + "]");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) { 
//		Mem1100eService service = Utilities.getBean("mem1100eService");
		try {
			if(SessionLoginUtils.isImperonate(se.getSession())) {
				LoginUser user = SessionLoginUtils.getImpersonateUser(se.getSession());
				if(user != null && user.isLogin()) {
					LOGGER.debug("logout impersonate from sesson destroyed id = [" + user.getLoginId() + "]");
//					service.logoutSession(se.getSession());
				}
				se.getSession().removeAttribute(SessionLoginUtils.LOGIN_IMPERSON_SESSION_NAME);
			}

			LoginUser user = SessionLoginUtils.getLoginUser(se.getSession());
			LOGGER.debug("login status of the session = [" + user.isLogin() + "]");
			if(user != null && user.isLogin()) {
				LOGGER.debug("logout from sesson destroyed id = [" + user.getLoginId() + "]");
//				service.logoutSession(se.getSession());
			} 
			se.getSession().removeAttribute(SessionLoginUtils.LOGIN_USER_SESSION_NAME);
			se.getSession().invalidate();
		} catch (SessionAuthenticationException e) {
			LOGGER.debug("info to SessionAuthenticationException");
		} catch (Exception e) {
			LOGGER.debug("info to Exception");
		}
	}
}
