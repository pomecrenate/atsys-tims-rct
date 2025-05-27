/*************************************************************
 프로그램명 : Evl1105eController.java
 설명 : 결과관리
 작성자 : 이예찬
 일자 : 2025.05.23
*************************************************************/
package com.atsys.tims.evl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.ui.ViewHelper;
import com.atsys.model.Evl1104eVo;
import com.atsys.model.Evl1105eVo;
import com.atsys.model.Evl1105p1Vo;
import com.atsys.model.TbEvalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;
import com.atsys.tims.evl.service.Evl1105eService;

@Controller
@RequestMapping(value = "/evl1105e")
public class Evl1105eController {
	
	private static final String ADM_PATH  = ConstantsUtils.getAdmViewRoot() + "/evl/"; 
	private final String viewList		  = ADM_PATH + "Evl1105e"; 
	private final String resultViewPop	  = ADM_PATH + "popup/" + "Evl1105p1";
	
	@Autowired
	private ViewHelper viewHelper;
	
	@Autowired
	private Evl1105eService evl1105eService;
	
	@RequestMapping
	public ModelAndView init(HttpServletRequest request, @RequestParam Map<String, Object> so, Model model, HttpSession session) throws Exception { 
		model.addAllAttributes(so);
		session.setAttribute("userRole", "ADMIN");
		return viewHelper.createModelAndView(viewList);
	}
	
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> getInfo(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<Evl1105eVo> info = evl1105eService.getInfo(so);
		result.put("list", info);
		return result;
	}
	
	@RequestMapping(value = "/getApplicants")
	@ResponseBody
	public Map<String, Object> getApplicants(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<Evl1104eVo> applicants = evl1105eService.getApplicants(so);
		result.put("list", applicants);
		return result;
	}
	
	@RequestMapping(value = "/getJudges")
	@ResponseBody
	public Map<String, Object> getJudges(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<TbSelEvalJdgVo> judges = evl1105eService.getJudges(so);
		result.put("list", judges);
		return result;
	}
	
	@RequestMapping(value = "/updatePassStep")
	@ResponseBody
	public Map<String, Object> updatePassStep(@RequestBody Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		int updateCnt = evl1105eService.updatePassStep(so);
		result.put("updateCnt", updateCnt);
		return result;
	}
	
	@RequestMapping(value="/popup/resultView")
	public String popupResultView() throws Exception {
		return resultViewPop;
	}
	
	@RequestMapping(value = "/getResultDetail")
	@ResponseBody
	public Map<String, Object> getResultDetail(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		// 평가 결과 기본 정보 조회
		Evl1105p1Vo resultInfo = evl1105eService.getResultInfo(so);
		result.put("info", resultInfo);
		
		// 평가 문항별 점수 조회
		List<TbEvalScoreVo> resultScores = evl1105eService.getResultScores(so);
		result.put("list", resultScores);
		
		return result;
	}
}
