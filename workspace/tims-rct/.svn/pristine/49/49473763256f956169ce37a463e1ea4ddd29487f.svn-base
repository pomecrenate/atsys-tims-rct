/*************************************************************
 프로그램명 : MenuService.java
 설명 : 관리자메뉴
 작성자 : 백세진
 일자 : 2025.04.23
*************************************************************/
package com.atsys.tims.sys.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.MenuVo;

@Transactional(readOnly=true)
public interface MenuService {

	List<MenuVo> selectLrgMenu(MenuVo vo) throws Exception;

	List<MenuVo> selectMidMenu(MenuVo vo) throws Exception;

	List<MenuVo> selectSmMenu(MenuVo vo) throws Exception;
	
	MenuVo selectMenuNm(String sysMenuCd) throws Exception;
}
