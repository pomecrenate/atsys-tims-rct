/*************************************************************
 프로그램명 : Bas1300eServiceImpl.java
 설명 : 코드관리
 작성자 : 최연재
 일자 : 2025.04.22
*************************************************************/
package com.atsys.tims.bas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.base.model.AbstractTreeVo;
import com.atsys.base.model.ITreeVo;
import com.atsys.model.TbSysMenuVo;
import com.atsys.tims.bas.dao.Bas1300eDao;
import com.atsys.tims.bas.service.Bas1300eService;

@Service("bas1300eService")
public class Bas1300eServiceImpl implements Bas1300eService{
	
	@Autowired
	private Bas1300eDao bas1300eDao;

	@Override
	public List<ITreeVo> selectTreeList(Object so) throws Exception {
		return AbstractTreeVo.makeHierarchy(bas1300eDao.selectTreeList(so));
	}

	@Override
	public List<TbSysMenuVo> selectList(Object so) throws Exception {
		return bas1300eDao.selectList(so);
	}

	@Override
	public int selectTotalCount(Object so) throws Exception {
		return bas1300eDao.selectTotalCount(so);
	}

}

