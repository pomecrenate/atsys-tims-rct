/*************************************************************
 프로그램명 : MenuController.java
 설명 : 관리자메뉴
 작성자 : 백세진
 일자 : 2025.04.23
*************************************************************/

package com.atsys.tims.sys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atsys.model.MenuVo;
import com.atsys.tims.sys.service.MenuService;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
	
	@Resource(name="menuService")
	private MenuService menuService;
	 
	@RequestMapping("/lrgMenu")  
	public @ResponseBody Object lrgMenu(HttpSession session, @RequestBody MenuVo vo, Model model) throws Exception {
		
		session.setAttribute("userRole", "ADMIN");
		return menuService.selectLrgMenu(vo);
	}

	@RequestMapping("/midMenu")  
	public @ResponseBody Object midMenu(HttpSession session, @RequestBody MenuVo vo, Model model) throws Exception {
		
		session.setAttribute("userRole", "ADMIN");
		return menuService.selectMidMenu(vo);
	}
	
	@RequestMapping("/smMenu")  
	public @ResponseBody Object smMenu(HttpSession session, @RequestBody MenuVo vo, Model model) throws Exception {
		
		session.setAttribute("userRole", "ADMIN");
		return menuService.selectSmMenu(vo);
	}

}
