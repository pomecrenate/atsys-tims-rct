/*************************************************************
 프로그램명 : Srv1300eController.java
 설명 : 자료실관리
 작성자 : 최연재
 일자 : 2025.04.18
*************************************************************/
package com.atsys.tims.srv.controller;

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
@RequestMapping(value = "/srv1300e")
public class Srv1300eController {
	
	private static final String ADM_PATH = "tims/views/srv/";
	
	private final String viewList = ADM_PATH  + "Srv1300e";
	
	@Autowired
	ViewHelper viewHelper;
	
	@RequestMapping
	public ModelAndView init(@RequestParam Map<String,Object> so, HttpServletRequest request, Model model, HttpSession session) throws Exception {
		session.setAttribute("userRole", "ADMIN");
		model.addAllAttributes(so);
		
		return viewHelper.createModelAndView(viewList);
	}
	
}
