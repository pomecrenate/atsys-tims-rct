/*************************************************************
 프로그램명 : Bas1300eController.java
 설명 : 메뉴관리
 작성자 : 최연재
 일자 : 2025.04.21
*************************************************************/
package com.atsys.tims.bas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ui.ViewHelper;
import com.atsys.model.TbSysMenuVo;
import com.atsys.tims.bas.service.Bas1300eService;

@Controller
@RequestMapping(value = "/bas1300e")
public class Bas1300eController {
	
	private static final String ADM_PATH = "tims/views/bas/";
	
	private final String viewList = ADM_PATH  + "Bas1300e";
	
	@Autowired
	ViewHelper viewHelper;
	
	@Autowired
	Bas1300eService bas1300eService;
	
	@RequestMapping
	public ModelAndView init(@RequestParam Map<String,Object> so, HttpServletRequest request, Model model, HttpSession session) throws Exception {
		session.setAttribute("userRole", "ADMIN");
		model.addAllAttributes(so);
		
		return viewHelper.createModelAndView(viewList);
	}
	
	@RequestMapping(value="/tree")
	public @ResponseBody Object selectTreeList (@RequestParam Map<String, Object> so) throws Exception {
		return  bas1300eService.selectTreeList(so);
	}
	
	@RequestMapping(value="/search")
	public @ResponseBody Map<String, Object> selectList (@RequestParam Map<String, Object> so) throws Exception {
		System.out.println(so);
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<TbSysMenuVo> data = bas1300eService.selectList(so);
		int totalCount = bas1300eService.selectTotalCount(so);
		
		map.put("data", data);
		map.put("count", totalCount);
		
//		Utilities.regSearchHst("R", data.size());
		
		return map;
	}
	
}