/*************************************************************
  프로그램명 : BaseVoAspect.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.common.aop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.BufferOverflowException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;

import com.atsys.base.model.BaseVo;
import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.vo.BaseBean;
import com.atsys.nxf.app.vo.LoginUser;
import com.atsys.nxf.core.web.servlet.handler.util.SessionLoginUtils;
import com.atsys.search.ListSo;

public class BaseVoAspect {

	private Logger LOGGER = Logger.getLogger(this.getClass());
	private static final boolean CHECK_CRSF = false;
	
	private  Object getJson(HttpServletRequest request) {
		try {
			if(request == null) {
				return null;
			}
			if(!"application/json".contains(request.getContentType().toUpperCase())) {
				return null;
			}
			InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(inputStreamReader);
			JSONParser parser = new JSONParser();
			try {
				Object obj = parser.parse(br);
				return obj;
			} finally {
				br.close();
			}
		} catch(BufferOverflowException ex){
			LOGGER.debug("info to BufferOverflowException");
			return null;
		} catch(Exception ex){
			LOGGER.debug("info to Exception");
			return null;
		}
	}
	
	private void checkSecureKey(JoinPoint jp) {
		if(!CHECK_CRSF) {
			return;
		}
		HttpServletRequest request = Utilities.getRequest();
		if(request == null) {
			return;
		}
		String key = (String)request.getSession().getAttribute("sk");
		if(key.equals(request.getParameter("sk"))) {
			return;
		}
		Object objJson = getJson(request);
		if(objJson == null) {
			return;
		}
		if(objJson instanceof JSONArray) {
			return;
		}
		if(objJson instanceof JSONObject) {
			JSONObject json = (JSONObject)objJson;
			if(key.equals(json.get("sk"))) {
				return;
			}
		}
		findSelectBaseVo(jp.getArgs());
	}
	
	@Before("execution(* *..*Dao.select*(..))")
	public void selectBefore(JoinPoint jp) throws Exception {
		setGlobalValue(jp);
//		setEscapeValue(jp);
	}
	
	@Before("execution(* *..*Dao.insert*(..))")
	public void insertBefore(JoinPoint jp) throws Exception {
		checkSecureKey(jp);
		setGlobalValue(jp);
//		setEscapeValue(jp);
		setBaseValue(jp);
	}
	
	@Before("execution(* *..*Dao.save*(..))")
	public void saveBefore(JoinPoint jp) throws Exception {
		checkSecureKey(jp);
		setGlobalValue(jp);
//		setEscapeValue(jp);
		setBaseValue(jp);
	}

	@Before("execution(* *..*Dao.update*(..))")
	public void updateBefore(JoinPoint jp) throws Exception {
		checkSecureKey(jp);
		setGlobalValue(jp);
//		setEscapeValue(jp);
		setBaseValue(jp);
	}
	
	@Before("execution(* *..*Dao.delete*(..))")
	public void deleteBefore(JoinPoint jp) throws Exception {
		checkSecureKey(jp);
		setGlobalValue(jp);
		setBaseValue(jp);
	}
	
	private void setGlobalValue(JoinPoint jp) throws Exception {
		Object obj = findSelectBaseVo(jp.getArgs());
		if(obj == null || RequestContextHolder.getRequestAttributes() == null) {
			return;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setBaseValue(Object obj){
		try {
			if(obj == null) {
				return;
			}
			if(!(obj instanceof BaseVo)) {
				if(!(obj instanceof Map)) {
					return;	
				}
			}
			HttpServletRequest request = Utilities.getRequest();
			String id = request == null ? "SYSTEM" : findUserId(request);
			
			String ip = request == null ? "0.0.0.0" : findIp();
			String uri = request == null ? "BAT1000e" : Utilities.getProgramId();
			if(Utilities.isEmpty(id)) {
				id = "SYSTEM";
			}
			if(Utilities.isEmpty(ip)) {
				ip = "SYSTEM";
			}
			if(Utilities.isEmpty(uri)) {
				uri = "SYSTEM";
			}
			if((obj instanceof Map)) {
				Map map = (Map)obj;
				if(Utilities.isNotEmpty(id)) {
					map.put("creUsrCd", id);
					map.put("updUsrCd", id);
				}
				if(Utilities.isNotEmpty(uri)) {
					map.put("crePgm", uri);
					map.put("updPgm", uri);
				}
				if(Utilities.isNotEmpty(ip)) {
					map.put("creIp", ip);
					map.put("updIp", ip);
				}
			} else {
				BaseVo vo = (BaseVo) obj;
				if(Utilities.isNotEmpty(id)) {
					vo.setCreUsrCd(id);
					vo.setUpdUsrCd(id);
				}
				if(Utilities.isNotEmpty(uri)) {
					vo.setCrePgm(uri);
					vo.setUpdPgm(uri);
				}
				if(Utilities.isNotEmpty(ip)) {
					vo.setCreIp(ip);
					vo.setUpdIp(ip);
				}
			}
		} catch(BufferOverflowException ex){
			LOGGER.debug("info to BufferOverflowException");
		}
		catch(Exception ex){
			LOGGER.debug("info to Exception");
		}
	}
	private void setBaseValue(JoinPoint jp) {
		setBaseValue(findBaseVo(jp.getArgs()));
	}
	
	private Object findBaseVo(Object[] args) {
		if (args == null || args.length == 0) {
			return null;
		}
		
		for(Object arg : args) {
			if (arg instanceof BaseVo) {
				return arg;
			}
			else if (arg instanceof Map) {
				return arg;
			}
		}
		
		return null;
	}
	
//	@SuppressWarnings("unchecked")
	private Object findSelectBaseVo(Object[] args) {
		if(args == null || args.length == 0) {
			return null;
		}
		
		for(Object arg : args) {
			if(arg instanceof ListSo || arg instanceof Map || arg instanceof BaseBean) {
				return arg;
			}
		}
		return null;
	}
	
	private String findUserId(HttpServletRequest request) {
		try {
			LoginUser user = SessionLoginUtils.getLoginUser(request);
			if (user != null) {
				return user.getUsrCd();
			}
		} catch (SessionAuthenticationException e) {
			LOGGER.debug("info to SessionAuthenticationException");
		} catch (Exception e) {
			LOGGER.debug("info to Exception");
		}
		
		return null;
	}
	
	private String findIp() {
		return Utilities.getPeerIp();
	}
	
//	private String findUserNm(HttpServletRequest request){
//		try{
//			LoginUser user = SessionLoginUtils.getLoginUser(request);
//			if(user != null){
//				return user.getUsrNm();
//			}
//		} catch (SessionAuthenticationException e) {
//			LOGGER.debug("info to SessionAuthenticationException");
//		} catch (Exception e) {
//			LOGGER.debug("info to Exception");
//		}
//		return null;
//	} 
}
