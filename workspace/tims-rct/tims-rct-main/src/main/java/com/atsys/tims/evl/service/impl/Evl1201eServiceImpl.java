package com.atsys.tims.evl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.Evl1201eVo;
import com.atsys.model.Rcp1100eVo;
import com.atsys.model.TbAnnVo;
import com.atsys.model.TbNtcFieldVo;
import com.atsys.tims.evl.dao.Evl1201eDao;
import com.atsys.tims.evl.service.Evl1201eService;

@Service("Evl1201eService")
public class Evl1201eServiceImpl implements Evl1201eService{
	
	@Autowired
	private Evl1201eDao evl1201eDao;

	@Override
	public TbNtcFieldVo selectRequiredCnt(TbNtcFieldVo vo) { 
		return evl1201eDao.selectRequiredCnt(vo);
	}

	@Override
	public List<Evl1201eVo> selectList(Map<String, Object> so) {
		List<Evl1201eVo> list = evl1201eDao.selectList(so);
		return list;
	}

	@Override
	public int selectTotalCount(Map<String, Object> so) { 
		return evl1201eDao.selectTotalCount(so);
	}

	@Override
	public List<TbAnnVo> selectAnnList(Map<String, Object> so) { 
		return evl1201eDao.selectAnnList(so);
	}

}
