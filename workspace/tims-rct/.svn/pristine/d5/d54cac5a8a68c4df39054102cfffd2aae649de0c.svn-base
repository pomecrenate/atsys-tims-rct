/*************************************************************
 프로그램명 : NtcInfoServiceImpl.java
 설명 : 공고관리-공고정보
 작성자 : 최연재
 일자 : 2025.05.12
*************************************************************/
package com.atsys.tims.rct.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.TbNtcInfoVo;
import com.atsys.tims.rct.dao.NtcInfoDao;
import com.atsys.tims.rct.service.NtcInfoService;

@Service("Rct1102eService")
public class NtcInfoServiceImpl implements NtcInfoService{

	@Autowired
	NtcInfoDao ntcInfoDao;

	@Override
	public int save(Map<String, Object> map) throws Exception {
		int res = 0;
		
		if("U".equals(map.get("sStatus"))) {
			res = ntcInfoDao.update(map);
			
			return res;
		}
		
		res = ntcInfoDao.save(map);
		
		return res;
	}

	@Override
	public TbNtcInfoVo search(Map<String, Object> map) throws Exception {
		return ntcInfoDao.selectOne(map);
	}

	
	
}
