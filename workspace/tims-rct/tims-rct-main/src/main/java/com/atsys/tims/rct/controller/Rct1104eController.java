/*************************************************************
 프로그램명 : Rct1104eController.java
 설명 : 공고관리-지원방법
 작성자 : 최연재
 일자 : 2025.04.28
*************************************************************/
package com.atsys.tims.rct.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.ui.ViewHelper; 

@Controller
@RequestMapping(value = "/rct1104e")
public class Rct1104eController {
	
	private static final String ADM_PATH   = ConstantsUtils.getAdmViewRoot() + "/rct/";
	private final String viewList		   = ADM_PATH + "Rct1104e";
	
	@Autowired
	ViewHelper viewHelper;
	
	@RequestMapping(value="/{ntcCd}")
	public ModelAndView init(@PathVariable(value = "ntcCd") String ntcCd, HttpServletRequest request, 
							 @RequestParam Map<String, Object> so) throws Exception { 
		return viewHelper.createModelAndView(viewList);
	} 
}