/*************************************************************
 프로그램명 : Rct1106eController.java
 설명 : 공고관리-전형절차
 작성자 : 최연재
 일자 : 2025.04.21
*************************************************************/
package com.atsys.tims.rct.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.ui.ViewHelper;
import com.atsys.model.TbNtcInfoVo;
import com.atsys.tims.rct.service.NtcInfoService; 

@Controller
@RequestMapping(value = "/rct1106e")
public class Rct1106eController {
	
	private static final String ADM_PATH   = ConstantsUtils.getAdmViewRoot() + "/rct/";
	private final String viewList		   = ADM_PATH + "Rct1106e";
	
	@Autowired
	ViewHelper viewHelper;

	@Autowired
	NtcInfoService ntcInfoService;
	
	@RequestMapping(value="/{ntcCd}")
	public ModelAndView init(@PathVariable(value = "ntcCd") String ntcCd, HttpServletRequest request, 
							 @RequestParam Map<String, Object> so) throws Exception { 
		return viewHelper.createModelAndView(viewList);
	} 
	
	@RequestMapping(value="/save")
	@ResponseBody
	public int save(@RequestBody Map<String, Object> map) throws Exception {	
		return ntcInfoService.save(map);
	}
	
	@RequestMapping("/search")
	public @ResponseBody TbNtcInfoVo search(@RequestBody Map<String, Object> map, Model model) throws Exception{	
		TbNtcInfoVo result = ntcInfoService.search(map);	
		return result;
	}
}