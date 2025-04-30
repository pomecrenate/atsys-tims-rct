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

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.util.Utilities;
import com.atsys.model.MenuVo;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;

//public class LanguageViewInterceptor extends HandlerInterceptorAdapter {
public class LanguageViewInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/*
		 * 호출 url이 공통코드, 진도율 시간 체크 체크일 때는 권한확인을 수행하지 않도록 함
		 * codeColls : 공통코드
		 * eduTimeChk : 진도율 시간 체크
		 * */
//		if(UrlUtil.isInterceptorExcept()) {
//			return true;
//		}
 
		
		MenuVo menuvo = new MenuVo();
		menuvo.setSysMenuCd(Utilities.getProgramId());
		
		// 2020.11.23 메로리 기능 요구사항 임시로 원복
//		String rootSystemId = menuService.selectRotSysId(menuvo);
//		request.setAttribute("rootSystemId", rootSystemId);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView == null) {
			return;
		}

		if(RequestUtils.isAjaxRequest(request) && !modelAndView.hasView()) {
			return;
		} 
	}
}
