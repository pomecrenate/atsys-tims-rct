/*************************************************************
 프로그램명 : Rct1101eServiceImpl.java
 설명 : 공고관리-초빙(임용)분야
 작성자 : 최연재
 일자 : 2025.05.12
*************************************************************/
package com.atsys.tims.rct.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.DeptVo;
import com.atsys.model.Rct1101eVo;
import com.atsys.model.TbNtcFieldVo;
import com.atsys.model.TbNtcStepVo;
import com.atsys.tims.rct.dao.Rct1101eDao;
import com.atsys.tims.rct.service.Rct1101eService;

@Service("Rct1101eService")
public class Rct1101eServiceImpl implements Rct1101eService{

	@Autowired
	Rct1101eDao rct1101eDao;

	@Override
	public List<TbNtcFieldVo> selectList(Map<String, Object> map) throws Exception {
		return rct1101eDao.selectList(map);
	}

	@Override
	public String selectNtcType(Map<String, Object> map) throws Exception {
		return rct1101eDao.selectNtcType(map);
	}

	@Override
	public List<DeptVo> selectDeptList(Map<String, Object> map) throws Exception {
		return rct1101eDao.selectDeptList(map);
	}

	@Override
	public int save(Map<String, Object> map) throws Exception {
		return rct1101eDao.save(map);
	}

	@Override
	public List<TbNtcStepVo> selectNtcList(Map<String, Object> map) throws Exception {
		return rct1101eDao.selectNtcList(map);
	}

	@Override
	public int addField(Rct1101eVo vo) throws Exception {   
		return rct1101eDao.insertField(vo);
	}
	
	@Override
	public int checkApp(Rct1101eVo vo) throws Exception {
		return rct1101eDao.selectApp(vo);
	}
	
	@Override
	public int deleteField(Rct1101eVo vo) throws Exception {
		return rct1101eDao.deleteYnField(vo);
	}
	
}
