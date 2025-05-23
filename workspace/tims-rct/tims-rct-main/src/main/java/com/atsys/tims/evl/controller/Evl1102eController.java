/*************************************************************
 프로그램명 : Evl1102eController.java
 설명 : 평가표관리
 작성자 : 이예찬
 일자 : 2025.05.13
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
import com.atsys.model.TbEvalSheetVo;
import com.atsys.tims.evl.service.Evl1102eService;

@Controller
@RequestMapping(value = "/evl1102e")
public class Evl1102eController {
	
	private static final String ADM_PATH  = ConstantsUtils.getAdmViewRoot() + "/evl/"; 
	private final String viewList		  = ADM_PATH + "Evl1102e"; 
	private final String sheetSelectPop	  = ADM_PATH + "popup/" + "Evl1102p1";
	
	@Autowired
	private ViewHelper viewHelper;
	
	@Autowired
	private Evl1102eService evl1102eService;
	
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
		List<TbEvalSheetVo> sheetList = evl1102eService.getInfo(so);
		result.put("list", sheetList);
		return result;
	}
	
	@RequestMapping(value = "/getSheet")
	@ResponseBody
	public Map<String, Object> getEvalSheet(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = evl1102eService.getEvalSheetWithQsts(so);
		return result;
	}
	
	@RequestMapping(value = "/searchSheetList")
	@ResponseBody
	public Map<String, Object> getEvalSheetList(@RequestParam Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		List<TbEvalSheetVo> sheetList = evl1102eService.getEvalSheetList(so);
		result.put("list", sheetList);
		return result;
	}
	
	@RequestMapping(value = "/popup/sheetSelect")
	public String popupSheetSelect() throws Exception {
		return sheetSelectPop;
	}
	
	@RequestMapping(value = "/deleteSheet")
	@ResponseBody
	public Map<String, Object> deleteEvalSheet(@RequestBody List<String> sheetCds) throws Exception {
		Map<String, Object> result = new HashMap<>();
		int deleteCnt = evl1102eService.deleteEvalSheet(sheetCds);
		result.put("deleteCnt", deleteCnt);
		return result;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> saveEvalSheet(@RequestBody Map<String, Object> so) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> sheet = (Map<String, Object>) so.get("sheet");
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> qsts = (List<Map<String, Object>>) so.get("qsts");
		
		List<Integer> resultCnts = evl1102eService.saveEvalSheet(sheet, qsts);
		
		result.put("insertCnt", resultCnts.get(0));
		result.put("deleteCnt", resultCnts.get(1));
		result.put("updateCnt", resultCnts.get(2));
		
		return result;
	}
}
