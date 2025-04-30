/*************************************************************
 프로그램명 : MenuDao.java
 설명 : 관리자메뉴
 작성자 : 백세진
 일자 : 2025.04.23
*************************************************************/
package com.atsys.tims.sys.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.MenuVo;

@Repository
@Mapper
public interface MenuDao {

	List<MenuVo> selectLrgMenu(MenuVo vo);

	List<MenuVo> selectMidMenu(MenuVo vo);

	List<MenuVo> selectSmMenu(MenuVo vo);
	
	MenuVo selectMenuNm(String sysMenuCd);
}
