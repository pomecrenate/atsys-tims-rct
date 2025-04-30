/*************************************************************
  프로그램명 : FrontMainController.java
  설명 : 사용자 메인
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19          신규작성                 AT-SYS 구탁수
*************************************************************/
package com.atsys.tims.main.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ui.ViewHelper;  

@Controller  
public class FrontMainController  { 
	
	@Autowired
	ViewHelper viewHelper;
	
	//사용자
	@RequestMapping(value={"", "/", "index", "/index", "front", "/front"}) 
	public ModelAndView selectForFront(HttpServletRequest request, @RequestParam Map<String,Object> so, 
									   ModelMap model, HttpSession session) throws Exception {
		   
		session.setAttribute("userRole", "USER"); 
		model.addAttribute("pageTitle", "테스트 레이아웃");
	    return viewHelper.createModelAndView("tims/front/main/main"); 
	} 
}