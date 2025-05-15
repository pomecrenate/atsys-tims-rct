package com.atsys.tims.evl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsys.base.util.Utilities;
import com.atsys.model.Evl1202eVo;
import com.atsys.model.TbAppVo;
import com.atsys.model.TbEvalSchVo;
import com.atsys.tims.evl.dao.Evl1202eDao;
import com.atsys.tims.evl.service.Evl1202eService;

@Service("Evl1202eService")
public class Evl1202eServiceImpl implements Evl1202eService{
	
	@Autowired
	private Evl1202eDao evl1202eDao;

	@Override
	public List<Evl1202eVo> selectList(Map<String, Object> so) {
		List<Evl1202eVo> list = evl1202eDao.selectList(so);
		return list;
	}

	@Override
	public int selectTotalCount(Map<String, Object> so) { 
		return evl1202eDao.selectTotalCount(so);
	}

	@Override
	public TbAppVo selectAppNm(TbAppVo vo) { 
		return evl1202eDao.selectAppNm(vo);
	} 

	@Override
	@Transactional
	public int insertEvalSch(TbEvalSchVo vo) throws Exception {  
		int insertCnt = 0;
		insertCnt += evl1202eDao.insertEvalSch(vo);
		
		if(insertCnt > 0) {
        	Utilities.regSearchHst("I", insertCnt);
        }
            
        return insertCnt;
	}

	@Override
	public TbEvalSchVo selectEvalSchData(TbEvalSchVo vo) { 
		return evl1202eDao.selectEvalSchData(vo);
	}

}
