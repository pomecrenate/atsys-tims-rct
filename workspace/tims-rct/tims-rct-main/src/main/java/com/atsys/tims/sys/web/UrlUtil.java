/*************************************************************
  프로그램명 : UrlUtil.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.tims.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.util.Utilities;

public final class UrlUtil {
	private static Logger LOGGER = Logger.getLogger(UrlUtil.class);
	
	public static String getCurrentProgramId() {
		try {
			return getCurrentProgramId(Utilities.getRequest());
		} catch(RuntimeException e) {
			String errorResult = "info to RuntimeException(line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + ")";
			LOGGER.error(errorResult);
			return null;
		} catch (Exception ex) {
			String errorResult = "info to Exception(line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + ")";
			LOGGER.error(errorResult);
			return null;
		}
	}
	
	public static String getCurrentProgramId(HttpServletRequest request) throws Exception {
		if(request == null) {
			return "BAT1000E";
		}
			
		String programId = (String)request.getAttribute("programId");
		String brdCd = null;
		if(programId != null) {
			return programId;
		}
 
		String sPath = request.getServletPath(); // /service, /front
		String cPath = request.getContextPath(); // ""
		if("/".equals(sPath)) {
			sPath = "";
		}
		if("/".equals(cPath)) {
			cPath = "";
		}
			
		String ignPath = cPath + sPath + "/"; // /service/
		String rurl = request.getRequestURI(); // /service/cont1100e
		String url = rurl.length() <= ignPath.length() ? "" : rurl.substring(ignPath.length());
		if(url.startsWith("/")) {
			programId = url.substring(1);
		}
		
		if(Utilities.isEmpty(url)) {
			request.setAttribute("programId", Utilities.nullCheck(programId));
			return "";
		}
		//첫번째 프로그램 아이디
		//두번째 게시판 아이디
		int index = url.indexOf("/");
		
		if(index < 0) {
			programId = url;
		} else {
			programId = url.substring(0, index);
			if(url.length() > index) {
				brdCd = url.substring(index + 1);
				index = brdCd.indexOf("/");
				if(index > 0) {
					brdCd = brdCd.substring(0, index);
				} else {
					if(StringUtils.isBlank(brdCd)){
						brdCd = null;	
					}
				}
				request.setAttribute("brdCd", brdCd);
			}
		}
//		TbSystemMenuDao dao = Utilities.getBean(TbSystemMenuDao.class);
//		MenuService service = Utilities.getBean(MenuService.class);
//		TbSysMenuVo so = new TbSysMenuVo();
//		so.setMenuUrl(programId);
//		so.setBrdCd(brdCd);
//		
//		TbSysMenuVo vo = service.selectOnePath(so);
//		if(vo != null) {
//			programId = vo.getSysMenuCd();
//			request.setAttribute("systemMenu", vo);
//		}
		
		request.setAttribute("programId", Utilities.nullCheck(programId));
		return programId;
	}

	public static String getSystemId(HttpServletRequest request, String defaultSystem) {
		String sysId = (String)request.getAttribute("systemId");
		if(Utilities.isEmpty(sysId)) {
			sysId = request.getServletPath();
			if(sysId.equals("") || sysId.equals("/")) {
				sysId = ConstantsUtils.getFrtServiceBasePath();
			}
			if(sysId.startsWith("/")) {
				sysId = sysId.substring(1);
			}
			if(sysId.isEmpty()) {
				return defaultSystem;
			}
			request.setAttribute("systemId", sysId);
		}
		
		return sysId.toUpperCase();
	}
	
	public static String getSystemId( String defaultSystem) {
		return getSystemId(Utilities.getRequest(), defaultSystem);
	}
	
	public static String getSystemId() {
		return getSystemId(Utilities.getRequest(), null);
	}
	
	public static String getProgramId() {
		try {
			return getCurrentProgramId();
		} catch(RuntimeException e) {
			String errorResult = "info to RuntimeException(line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + ")";
			LOGGER.error(errorResult);
			return null;
		} catch (Exception ex) {
			String errorResult = "info to Exception(line:" + Thread.currentThread().getStackTrace()[1].getLineNumber() + ")";
			LOGGER.error(errorResult);
			return null;
		}
	}
	
	public static boolean isInterceptorExcept() {
		HttpServletRequest request = Utilities.getRequest();
		/*
		 * codeColls : 공통코드
		 * contwin : 학습창호출
		 * emaexam : 시험창
		 * tree : 트리
		 * blank : autologout 관련해서 세션 연장 호출
		 * */
		List<String> arrList = new ArrayList<String>();
		arrList.add("gisuInfo");
		arrList.add("allMenu");
		arrList.add("codeColls");
		arrList.add("tree");
		arrList.add("blank");
		arrList.add("orgn");
		arrList.add("datePeriod");
		arrList.add("fileUpload");
		arrList.add("fileDownload");
		arrList.add("indexTab");
		arrList.add("masking");
		arrList.add("logout"); // 로그아웃 controller
		arrList.add("contWin"); // 동영상학습
		arrList.add("examWin"); // 시험응시
		arrList.add("quizWin"); // 퀴즈참여
		arrList.add("certi"); // 수료증
		
		String url = request.getRequestURL().toString();
		
		for(String str : arrList) {
			if(url.indexOf("/" + str) > -1) {
				return true;
			}
		}
		
		return false;
	}
}
