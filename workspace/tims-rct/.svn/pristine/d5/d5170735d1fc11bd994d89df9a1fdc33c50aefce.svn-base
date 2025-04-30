/*************************************************************
 프로그램명 : PkSeqServiceImpl.java
 설명 : (과정, 차수)pk 생성 serviceImpl
 작성자 : 김병주
 일자 : 2021.10.27 
*************************************************************/
package com.atsys.tims.sys.service.impl;

import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.tims.sys.dao.UniqIdDao;
import com.atsys.tims.sys.service.UniqIdService;

@Service("uniqIdService")
public class UniqIdServiceImpl implements UniqIdService {
	@Autowired
	private UniqIdDao uniqIdDao;

	@Override
	public EgovMap selectTbUniqId(Object so) throws Exception{
		return uniqIdDao.selectTbUniqId(so);
	}
	
	@Override
	public Map<String, Object> selectPkCd(Map<String, Object> so) throws Exception {
		uniqIdDao.updatePkSeq(so);
		return so;
	}
}
