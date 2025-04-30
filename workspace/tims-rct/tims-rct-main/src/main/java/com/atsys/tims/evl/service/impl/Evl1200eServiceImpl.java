/*************************************************************
 프로그램명 : Evl1200eServiceImpl.java
 설명 : 합격관리
 작성자 : 백세진
 일자 : 2025.04.09
*************************************************************/
package com.atsys.tims.evl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.TbNtcStepVo;
import com.atsys.model.TbNtcVo;
import com.atsys.tims.evl.dao.Evl1200eDao;
import com.atsys.tims.evl.service.Evl1200eService;

@Service("Evl1200eService")
public class Evl1200eServiceImpl implements Evl1200eService{
	
	@Autowired
	private Evl1200eDao evl1200eDao;
	
	  @Override public List<TbNtcStepVo> selectList(Map<String, Object> so) throws Exception { 
		  List<TbNtcStepVo> list = evl1200eDao.selectList(so); 
		  
		  return list; 
	  }

	@Override
	public int selectTotalCount(Map<String, Object> so) { 
		return evl1200eDao.selectTotalCount(so); 
	} 
	  
}
