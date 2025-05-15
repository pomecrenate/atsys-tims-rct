/*************************************************************
 프로그램명 : Bas1200eServiceImpl.java
 설명 : 알림 문구 관리
 작성자 : 최연재
 일자 : 2025.05.07
*************************************************************/
package com.atsys.tims.bas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.TbAnnVo;
import com.atsys.tims.bas.dao.Bas1200eDao;
import com.atsys.tims.bas.service.Bas1200eService;

@Service("bas1200eService")
public class Bas1200eServiceImpl implements Bas1200eService{
	
	@Autowired
	Bas1200eDao bas1200eDao;

	@Override
	public int save(Map<String, Object> so) throws Exception {
		int res = 0;
		
		if("U".equals(so.get("sStatus"))) {
			res = bas1200eDao.update(so);
		} else if("I".equals(so.get("sStatus"))){
			res = bas1200eDao.insert(so);
		}
		
		return res;
	}

	@Override
	public List<TbAnnVo> selectList(Map<String, Object> so) throws Exception {
		return bas1200eDao.selectList(so);
	}

}

