/*************************************************************
 프로그램명 : Bas1100eController.java
 설명 : 코드관리
 작성자 : 최연재
 일자 : 2025.04.09
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.ui.ViewHelper;
import com.atsys.base.util.Utilities;
import com.atsys.model.TbCmmVo;
import com.atsys.tims.bas.service.Bas1100eService;

@Controller
@RequestMapping(value = "/bas1100e")
public class Bas1100eController {
	
	private static final String ADM_PATH = ConstantsUtils.getAdmViewRoot() + "/bas/";
	
	private final String viewList = ADM_PATH  + "Bas1100e";
	private final String update = ADM_PATH + "popup/" + "Bas1100p1";
	
	@Autowired
	ViewHelper viewHelper;
	
	@Autowired
	Bas1100eService bas1100eService;
	
	@RequestMapping
	public ModelAndView init(@RequestParam Map<String,Object> so, HttpServletRequest request, Model model, HttpSession session) throws Exception {
		session.setAttribute("userRole", "ADMIN");
		model.addAllAttributes(so);
		
		return viewHelper.createModelAndView(viewList);
	}
	
	@RequestMapping("/searchLeft")
	public @ResponseBody Map<String, Object> selectList (@RequestParam Map<String, Object> map, Model model) throws Exception {
		
		map.put("qryDelYn", Utilities.parseStringToArray((String)map.get("qryDelYn")));
	    
		List<TbCmmVo> data = bas1100eService.selectTbCommonCdList(map);
		
		Map<String, Object> newMap = new HashMap<>();
		
		newMap.put("data", data);
		
		return newMap;	
	}
	
	@RequestMapping("/searchRight")
	public @ResponseBody Map<String, Object> selectSmallList (@RequestParam Map<String, Object> map, Model model) throws Exception {
		
		map.put("qryDelYn", Utilities.parseStringToArray((String)map.get("qryDelYn")));

	    List<TbCmmVo> data = bas1100eService.selectSmallCdList(map);
		
		Map<String, Object> newMap = new HashMap<>();
		
	    newMap.put("data", data);
		
		return newMap;
	}
	
	@PostMapping("/save")
	public @ResponseBody Map<String, Object> save(@RequestBody TbCmmVo vo) throws Exception {			
		return bas1100eService.save(vo);
	}
	
	@RequestMapping(value="/popup/update")
	public ModelAndView update(@RequestParam Map<String, Object> so, Model model, HttpSession session) throws Exception {  
		model.addAllAttributes(so);
		session.setAttribute("userRole", "ADMIN");
		return viewHelper.createModelAndViewPopup(update);
	} 
}
