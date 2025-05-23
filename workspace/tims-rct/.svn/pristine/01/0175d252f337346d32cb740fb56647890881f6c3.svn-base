/*************************************************************
 프로그램명 : Evl1104eController.java
 설명 : 사정관리
 작성자 : 이예찬
 일자 : 2025.05.22
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
import com.atsys.model.TbEvalTotalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;
import com.atsys.tims.evl.service.Evl1104eService;

@Controller
@RequestMapping(value = "/evl1104e")
public class Evl1104eController {
	
	private static final String ADM_PATH  = ConstantsUtils.getAdmViewRoot() + "/evl/"; 
	private final String viewList		  = ADM_PATH + "Evl1104e";
	
	@Autowired
	private ViewHelper viewHelper;
	
	@Autowired
	private Evl1104eService evl1104eService;
	
	@RequestMapping
	public ModelAndView init(HttpServletRequest request, @RequestParam Map<String, Object> so, Model model, HttpSession session) throws Exception { 
		model.addAllAttributes(so);
		session.setAttribute("userRole", "ADMIN");
		return viewHelper.createModelAndView(viewList);
	}
	
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> searchEvaluations(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<TbEvalTotalScoreVo> info = evl1104eService.getInfo(so);
		result.put("list", info);
		return result;
	}
	
	@RequestMapping(value = "/getApplicants")
	@ResponseBody
	public Map<String, Object> getApplicants(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<Evl1104eVo> applicants = evl1104eService.getApplicants(so);
		result.put("list", applicants);
		return result;
	}
	
	@RequestMapping(value = "/getJudges")
	@ResponseBody
	public Map<String, Object> getJudges(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<TbSelEvalJdgVo> judges = evl1104eService.getJudges(so);
		result.put("list", judges);
		
		// 총점 코드가 있는지 확인
		String totalCd = evl1104eService.getTotalCd(so);
		if (totalCd != null && !totalCd.isEmpty()) {
			result.put("totalCd", totalCd);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/calcScores")
	@ResponseBody
	public Map<String, Object> calcScores(@RequestBody Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		// 점수 계산 실행
		List<Integer> resultCnts = evl1104eService.calcScores(so);
		result.put("insertCnt", resultCnts.get(0));
		result.put("updateCnt", resultCnts.get(1));
		
		// 추가되었으면 totalCd 조회
		if (resultCnts.get(0) > 0 || resultCnts.get(1) > 0) {
			String totalCd = evl1104eService.getTotalCd(so);
			result.put("totalCd", totalCd);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/cancelCalcScores")
	@ResponseBody
	public Map<String, Object> cancelCalcScores(@RequestBody Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		int deleteCnt = evl1104eService.cancelCalcScores(so);
		result.put("deleteCnt", deleteCnt);
		return result;
	}
}
