/*************************************************************
 프로그램명 : Bas1100eServiceImpl.java
 설명 : 코드관리
 작성자 : 최연재
 일자 : 2025.04.10
*************************************************************/
package com.atsys.tims.bas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.base.util.Utilities;
import com.atsys.model.TbCmmVo;
import com.atsys.tims.bas.dao.Bas1100eDao;
import com.atsys.tims.bas.service.Bas1100eService;

@Service("bas1100eService")
public class Bas1100eServiceImpl implements Bas1100eService{

	@Autowired
	Bas1100eDao bas1100eDao;
	
	@Override
	public Map<String, Object> save(TbCmmVo vo) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		int insertCnt = 0; 
		int updateCnt = 0;
			
 		if(vo.getsStatus().equals("I")){
 			insertCnt += bas1100eDao.insert(vo);
 		}else if(vo.getsStatus().equals("U")){
			updateCnt += bas1100eDao.update(vo);
		
			// 대분류 사용안함 처리시 하위 공통코드도 사용안함 처리
//			if(vo.getUseYn().equals("N") && vo.getCmmCdDiv().equals("01")) {
//				bas1100eDao.updateUseYn(vo);
		}
		
		if(insertCnt > 0) {
			Utilities.regSearchHst("I", insertCnt);
		}
		if(updateCnt > 0) {
			Utilities.regSearchHst("U", updateCnt);
		}
		
		map.put("insertCnt", insertCnt);
		map.put("updateCnt", updateCnt);
		
		return map;
			
	}

	@Override
	public int selectTotalCount(Object so) throws Exception {
		return 0;
	}

	@Override
	public List<TbCmmVo> selectTbCommonCdList(Object so) throws Exception {
		return bas1100eDao.selectList(so);
	}

	@Override
	public int selectSmallCdTotalCount(Object so) throws Exception {
		return 0;
	}

	@Override
	public List<TbCmmVo> selectSmallCdList(Object so) throws Exception {
		return bas1100eDao.selectSmallCdList(so);
	}
}

