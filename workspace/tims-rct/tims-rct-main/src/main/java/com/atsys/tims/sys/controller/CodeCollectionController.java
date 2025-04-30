/*************************************************************
  프로그램명 : CodeCollectionController.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.tims.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atsys.model.CodeSelectionVo;
import com.atsys.model.TbCmmVo;
import com.atsys.search.AdmComboCodeSo;
import com.atsys.search.CodeSelectionSo;
import com.atsys.tims.sys.service.CodeCollectionService;

/**
 * @Class Name : CodeCollectionController.java
 * @Description : CodeCollectionController class
 * @Modification Information
 *
 * @author frida
 * @since 2014. 4. 8.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
@Controller
@RequestMapping(value = "/codeColls")
public class CodeCollectionController  { 

	@Resource(name = "codeCollectionService")
	private CodeCollectionService codeCollectionService; 
	@RequestMapping
	public @ResponseBody Object selectCodes(HttpServletRequest request, @RequestBody CodeSelectionSo so, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<CodeSelectionVo>> tmpMap = codeCollectionService.selectCodeList(so);
		map.putAll(tmpMap);
		return map;
	}

	
	// - 콤보박스
	// - 기본값  
	@RequestMapping(value="/comboCode" )
	public @ResponseBody Object selectAfaComCode(HttpServletRequest request,
			@RequestBody AdmComboCodeSo so,
			@ModelAttribute TbCmmVo vo, BindingResult bindingResult,
			Model model) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		return map;
	}
}

