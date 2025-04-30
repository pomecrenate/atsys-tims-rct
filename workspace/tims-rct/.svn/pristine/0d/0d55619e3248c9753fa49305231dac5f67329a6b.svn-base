/*************************************************************
  프로그램명 : NaviInterceptor.java
  설명 : 네비게이션 메뉴 구성 인터셉터
  작성자 : K.Y.W.
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19          신규작성                 AT-SYS 
*************************************************************/
package com.atsys.base.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.app.login.LoginService;
import com.atsys.base.util.Utilities;
import com.atsys.model.MenuVo;
import com.atsys.nxf.app.vo.LoginUser;
import com.atsys.nxf.app.vo.Pagination;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;
import com.atsys.tims.sys.service.MenuService;
import com.atsys.tims.sys.web.UrlUtil;

public class NaviInterceptor implements HandlerInterceptor {
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "loginService")
	protected LoginService loginService;
	
	@Resource(name = "menuService")
	protected MenuService menuService;
	
	private Logger LOGGER = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		/*
		 * naviInterceptor를 타지 않도록...
		 * */
		if(UrlUtil.isInterceptorExcept()) {
			return true;
		}
		
//		loginService.initUserMenus();
		LoginUser loginUser = LoginUser.getLoginUser();
		String systemId = UrlUtil.getSystemId().toUpperCase();
		
		MenuVo vo = new MenuVo();
		vo.setSysCd(systemId);
		vo.setSysMenuCd(systemId);
		vo.setUsrCd(loginUser.getUsrCd());
		
		// 2023.09.19. 권영욱 service와 front 분리
//		List<MenuVo> topMenus = null;
//		if(Utilities.isAdminContext()) {
//			topMenus = menuService.selectTopMenu(vo);
//		}else {
//			topMenus = menuService.selectFrontTopMenu(vo);
//		}
//		request.setAttribute("topMenus", topMenus);
		
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
		
		ModelMap model = modelAndView.getModelMap();
		model.addAttribute("utilities", Utilities.getThis());
		model.addAttribute(ConstantsUtils.getSessionLUKey(), LoginUser.getLoginUser());
		
		Map<String, Object> menuMap = getMenus(model);
		model.addAllAttributes(menuMap);
		model.addAttribute(ConstantsUtils.getProgramId(), Utilities.getProgramId().toLowerCase()); // programId 이름으로 셋팅
		model.addAttribute(ConstantsUtils.getSystemId(), Utilities.getSystemId());
		
		// jsp에서 사용될 serviceBathPath 값을 설정
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String serviceBathPath = contextPath + servletPath;
		model.addAttribute(ConstantsUtils.getServiceBasePathName(), serviceBathPath);
		
		MenuVo vo = menuService.selectMenuNm(Utilities.getProgramId());
		String menuNm = vo.getMenuNm();
		model.addAttribute("menuNm", menuNm);
		
		Pagination page = (Pagination) request.getAttribute(ConstantsUtils.getPagenationInfo());
		if(page != null && model.containsKey(ConstantsUtils.getTotalRecordCount())) {
			model.addAttribute(ConstantsUtils.getPagenationInfo(), page);
			page.setTotalRecordCount(Utilities.parseInt(model.get(ConstantsUtils.getTotalRecordCount())));
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> getMenus(ModelMap model) {
		
		try {
			LoginUser user = LoginUser.getLoginUser();
			
			String systemId = UrlUtil.getSystemId().toUpperCase();
			String programId = UrlUtil.getProgramId().toUpperCase();
			if(Utilities.isEmpty(systemId)) {
				return null;
			}
				
			MenuVo vo = new MenuVo();
			vo.setSysCd(systemId);
			vo.setSysMenuCd(systemId);
			vo.setUsrCd(user.getUsrCd());
			List<MenuVo> topMenus = (List<MenuVo>) Utilities.getRequest().getAttribute("topMenus");// menuService.selectTopMenu(vo);
			if(Utilities.isEmpty(topMenus)) {
				return null;
			}
			
			String topMenuId = null;
			List<MenuVo> naviMenus = (List<MenuVo>)model.get("naviMenus");
			Map<String, Object> map = new HashMap<String, Object>();
			if(Utilities.isNotEmpty(programId)) {
				vo.setSysMenuCd(programId);
//				if(naviMenus == null) {
//					naviMenus = menuService.selectNaviMenu(vo);
//				}
//				if(naviMenus.size() > 1) {
//					topMenuId = naviMenus.get(1).getSysMenuCd();
//				} else {
//					topMenuId = topMenus.get(0).getSysMenuCd();
//				}
				if(naviMenus.size() > 0) {
					map.put("programNm", naviMenus.get(naviMenus.size() - 1).getMenuNm());
				} else {
					map.put("programNm", "");
				}
			} else {
				topMenuId = topMenus.get(0).getSysMenuCd();
			}
				
			if(Utilities.isAdminContext()) {
				vo.setSysMenuCd(topMenuId);
			} else {
				vo.setSysMenuCd("FRT0000");
			}
			
//			List<MenuVo> leftMenus = menuService.selectLeftMenu(vo);
			
			map.put("topMenus", topMenus);
			map.put("naviMenus", naviMenus);
//			map.put("leftMenus", leftMenus);
			
			// 탭메뉴 추가
			vo.setSysMenuCd(programId);
			
//			List<MenuVo> tabMenus = menuService.selectAccessTabMenu(vo);
//			map.put("tabMenus", tabMenus);
			
			return map;
		}catch (IllegalStateException e) {
			LOGGER.debug("info to IllegalStateException : " + e);
			return null;
		}catch (Exception ex) {
			LOGGER.debug("info to Exception : " + ex);
			return null;
		}
	}
}
