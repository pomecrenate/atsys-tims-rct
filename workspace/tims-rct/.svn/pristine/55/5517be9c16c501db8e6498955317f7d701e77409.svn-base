/*************************************************************
 프로그램명 : Evl1100eController.java
 설명 : 평가관리
 작성자 : 이예찬
 일자 : 2025.04.29
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

import com.atsys.base.ui.ViewHelper;
import com.atsys.model.TbNtcStepVo;
import com.atsys.nxf.app.vo.Pagination;
import com.atsys.tims.evl.service.Evl1100eService;

@Controller
@RequestMapping(value = "/evl1100e")
public class Evl1100eController {

	private static final String ADM_PATH = "tims/views/evl/";
	private final String viewList = ADM_PATH + "Evl1100e";

	@Autowired
	ViewHelper viewHelper;

	@Autowired
	Evl1100eService evl1100eService;

	@RequestMapping
	public ModelAndView init(HttpServletRequest request, @RequestParam Map<String, Object> so, Model model,
			HttpSession session) throws Exception {
		model.addAllAttributes(so);
		session.setAttribute("userRole", "ADMIN");

		return viewHelper.createModelAndView(viewList);
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> selectList(@RequestBody Map<String, Object> so) throws Exception {

		int pageIndex = Integer.parseInt(String.valueOf(so.getOrDefault("pageIndex", "1")));
		so.put("currentPageNo", pageIndex); 

		List<TbNtcStepVo> list = evl1100eService.selectList(so);
		int totalCount = evl1100eService.selectTotalCount(so);

		Pagination page = new Pagination();
		page.setCurrentPageNo(pageIndex);
		page.setTotalRecordCount(totalCount);

		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("paginationInfo", page.getPaginationInfo());
		result.put("totalCount", totalCount);

		return result;
	} 
}
