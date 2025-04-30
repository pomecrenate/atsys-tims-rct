/*************************************************************
 프로그램명 : MenuServiceImpl.java
 설명 : 관리자메뉴
 작성자 : 백세진
 일자 : 2025.04.23
*************************************************************/
package com.atsys.tims.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.base.util.Utilities;
import com.atsys.model.MenuVo;
import com.atsys.tims.sys.dao.MenuDao;
import com.atsys.tims.sys.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao; 
 
	
	@Override
	public List<MenuVo> selectLrgMenu(MenuVo vo) throws Exception {
		List<MenuVo> list = menuDao.selectLrgMenu(vo);
		return makeHierachi(list);
	}  

	@Override
	public List<MenuVo> selectMidMenu(MenuVo vo) throws Exception {
		List<MenuVo> list = menuDao.selectMidMenu(vo);
		return makeHierachi(list);
	} 

	@Override
	public List<MenuVo> selectSmMenu(MenuVo vo) throws Exception {
		List<MenuVo> list = menuDao.selectSmMenu(vo);
		return list;
	}
	
	
	private List<MenuVo> makeHierachi(List<MenuVo> list){
		if(Utilities.isEmpty(list)) {
			return null;
		}
		List<MenuVo> leftMenu = new ArrayList<MenuVo>();
		
		Map<String, MenuVo> map = new HashMap<String, MenuVo>();
		for(MenuVo vo : list) {
			map.put(vo.getSysMenuCd(), vo);
			if(map.containsKey(vo.getUprMenuCd())) {
				MenuVo parent = map.get(vo.getUprMenuCd());
				parent.addChildren(vo);
			} else {
				leftMenu.add(vo);
			}
		}
		return leftMenu;
	}

	@Override
	public MenuVo selectMenuNm(String sysMenuCd) throws Exception {
		return menuDao.selectMenuNm(sysMenuCd);
	}  
}
