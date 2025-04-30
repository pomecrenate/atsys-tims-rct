/*************************************************************
 프로그램명 : Rct1104eController.java
 설명 : 공고관리-지원방법
 작성자 : 최연재
 일자 : 2025.04.28
*************************************************************/
package com.atsys.tims.rct.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ui.ViewHelper; 

@Controller
@RequestMapping(value = "/rct1104e")
public class Rct1104eController {
	
	private static final String ADM_PATH   = "tims/views/rct/"; 
	private final String viewList		   = ADM_PATH + "Rct1104e";
	
	@Autowired
	ViewHelper viewHelper;
	
	@RequestMapping
	public ModelAndView init(@RequestParam Map<String, Object> so, Model model, HttpSession session) throws Exception { 
		model.addAllAttributes(so);
		session.setAttribute("userRole", "ADMIN"); 
		return viewHelper.createModelAndView(viewList);
	}
}