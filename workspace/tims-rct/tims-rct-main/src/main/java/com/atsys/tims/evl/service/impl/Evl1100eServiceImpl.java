/*************************************************************
 프로그램명 : Evl1100eServiceImpl.java
 설명 : 평가관리
 작성자 : 이예찬
 일자 : 2025.04.29
*************************************************************/
package com.atsys.tims.evl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.TbNtcStepVo;
import com.atsys.tims.evl.dao.Evl1100eDao;
import com.atsys.tims.evl.service.Evl1100eService;

@Service("Evl1100eService")
public class Evl1100eServiceImpl implements Evl1100eService{
	
	@Autowired
	private Evl1100eDao evl1100eDao;
	
	@Override 
	public List<TbNtcStepVo> selectList(Map<String, Object> so) throws Exception { 
		List<TbNtcStepVo> list = evl1100eDao.selectList(so); 
		return list; 
	}

	@Override
	public int selectTotalCount(Map<String, Object> so) { 
		return evl1100eDao.selectTotalCount(so); 
	} 
	  
}
