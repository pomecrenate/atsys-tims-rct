package com.atsys.base.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.util.Utilities;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;

@Controller
@RequestMapping(value = "/error")
public class TimsErrorController {
	private static final String ERROR_PATH = "tims/common/error/"; 

	@RequestMapping
	public String init( HttpServletRequest request, Model model) throws Exception { 
		
		Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code"); 
		return error(request, statusCode, model);
	}
	
	@RequestMapping(value="/{statusCode}")
	public String error( HttpServletRequest request, @PathVariable("statusCode") int statusCode, Model model) throws Exception {
		if(RequestUtils.isAjaxRequest(request)){
			return null;
		}
		String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
		String cPath = request.getContextPath();
		String systemId = "";
		if(Utilities.isNotEmpty(requestUri)) {
			if("".equals(cPath) && !"/".equals(cPath)) {
				if(cPath.length() <= requestUri.length()) {
					requestUri = requestUri.substring(cPath.length());
				}
			}
			if(Utilities.isNotEmpty(requestUri) && requestUri.startsWith("/") && requestUri.length() > 1) {
				requestUri = requestUri.substring(1);
			}
			if(Utilities.isNotEmpty(requestUri)&& requestUri.startsWith(ConstantsUtils.getAdmContenxt())){
				systemId = ConstantsUtils.getAdmContenxt();
			}
		}
		
		String errorJsp = "error";
		switch (statusCode) {
			case 450:
				errorJsp = String.valueOf(statusCode);
				break;
			case 530:
				errorJsp = String.valueOf(statusCode);
				break;
			case 531:
				errorJsp = String.valueOf(statusCode);
				break;
			case 532:
				errorJsp = String.valueOf(statusCode);
				break;
			case 533:
				errorJsp = String.valueOf(statusCode);
				break;
			case 534:
				errorJsp = String.valueOf(statusCode);
				break;
			case 535:
				errorJsp = String.valueOf(statusCode);
				break;
			case 536:
				errorJsp = String.valueOf(statusCode);
				break;
			default:
				errorJsp = "error";
				break;
		}
		
		if(ConstantsUtils.getAdmContenxt().equalsIgnoreCase(systemId)){
			String message = (String)request.getAttribute("javax.servlet.error.message");
			model.addAttribute("message", message);
			model.addAttribute("statusCode", statusCode);
			return ERROR_PATH + errorJsp;
		}
		
		return ERROR_PATH + errorJsp;
	}
}
