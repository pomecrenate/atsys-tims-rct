/*************************************************************
 프로그램명 : Rcp1100eServiceImpl.java
 설명 : 지원서관리
 작성자 : 백세진
 일자 : 2025.04.09
*************************************************************/
package com.atsys.tims.rcp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.Rcp1100eVo;
import com.atsys.model.TbAnnVo;
import com.atsys.model.TbAppAwardVo;
import com.atsys.model.TbAppCareerVo;
import com.atsys.model.TbAppEduVo;
import com.atsys.model.TbAppRecVo;
import com.atsys.model.TbAppSelfIntroVo;
import com.atsys.model.TbAppVo;
import com.atsys.model.TbNtcDocVo;
import com.atsys.model.TbUpldFileVo;
import com.atsys.tims.rcp.dao.Rcp1100eDao;
import com.atsys.tims.rcp.service.Rcp1100eService;
import com.atsys.tims.sys.dao.AppDetailDao;

@Service("Rcp1100eService")
public class Rcp1100eServiceImpl implements Rcp1100eService{
	
	@Autowired
	private Rcp1100eDao rcp1100eDao;

	@Override
	public List<Rcp1100eVo> selectList(Map<String, Object> so) {
		List<Rcp1100eVo> list = rcp1100eDao.selectList(so);
		return list;
	}

	@Override
	public int selectTotalCount(Map<String, Object> so) { 
		return rcp1100eDao.selectTotalCount(so);
	} 


	@Override
	public Map<String, Object> updateAppstatus(TbAppVo vo) { 
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("updateAppstatus", rcp1100eDao.updateAppstatus(vo));
		return resultMap;
	}

	@Override
	public Map<String, Object> updateQualYnAndReason(TbAppVo vo) { 
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("updateQualYnAndReason", rcp1100eDao.updateQualYnAndReason(vo));
		return resultMap;
	}

	@Override
	public TbAppVo selectQualData(TbAppVo vo) {
		return rcp1100eDao.selectQualData(vo);
	}

	@Override
	public List<TbAppCareerVo> selectCareerList(TbAppCareerVo vo) { 
	    List<TbAppCareerVo> list = rcp1100eDao.selectCareerList(vo);

	    for (TbAppCareerVo careerVo : list) {
	        String startDate = careerVo.getWorkStartDate();
	        String endDate = careerVo.getWorkEndDate();
	        
	        if (startDate != null && !startDate.isEmpty()) {
	            if (endDate != null && !endDate.isEmpty()) {
	            	careerVo.setWorkDate(startDate + " ~ " + endDate);
	            } else { 
	            	careerVo.setWorkDate(startDate + " ~ 재직중");
	            }
	        }
	    }
	    return list;
	}

	@Override
	public Map<String, Object> updateCareerPeriodAndReason(TbAppVo vo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("updateCareerPeriodAndReason", rcp1100eDao.updateCareerPeriodAndReason(vo));
		return resultMap;
	}

	@Override
	public TbAppVo selectCareerData(TbAppVo vo) { 
		return rcp1100eDao.selectCareerData(vo);
	}

	@Override
	public TbAppVo selectAppNmAndMob(TbAppVo vo) { 
		return rcp1100eDao.selectAppNmAndMob(vo);
	} 
	
	@Override
	public List<TbNtcDocVo> selectNtcDocList(TbNtcDocVo vo) { 
		return rcp1100eDao.selectNtcDocList(vo);
	}

	@Override
	public List<TbUpldFileVo> selectUpldFileList(Map<String, Object> so) {
		return rcp1100eDao.selectUpldFileList(so);
	}  
	
}
