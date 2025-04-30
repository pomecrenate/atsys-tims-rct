/*************************************************************
 프로그램명 : Evl1103eController.java
 설명 : 최종합격관리
 작성자 : 백세진
 일자 : 2025.04.09
*************************************************************/
package com.atsys.tims.evl.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ui.ViewHelper;

@Controller
@RequestMapping(value = "/evl1103e")
public class Evl1103eController {
	
	private static final String ADM_PATH  = "tims/views/evl/"; 
	private final String viewList		  = ADM_PATH + "Evl1103e"; 
	private final String annSelectPop	  = ADM_PATH + "popup/" + "Evl1103p1";
	
	@Autowired
	ViewHelper viewHelper;
	
	@RequestMapping
	public ModelAndView init(HttpServletRequest request, @RequestParam Map<String, Object> so, Model model, HttpSession session) throws Exception { 
		model.addAllAttributes(so);
		session.setAttribute("userRole", "ADMIN");
		return viewHelper.createModelAndView(viewList);
	} 
	
	@RequestMapping(value="/popup/annSelect")
	public ModelAndView popupQualynSelect(@RequestParam Map<String, Object> so, Model model, HttpSession session) throws Exception {  
		model.addAllAttributes(so);
		session.setAttribute("userRole", "ADMIN");
		return viewHelper.createModelAndViewPopup(annSelectPop);
	} 
	
	
}
