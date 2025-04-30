/*************************************************************
 프로그램명 : Rct1100eServiceImpl.java
 설명 : 공고관리
 작성자 : 최연재
 일자 : 2025.04.29
*************************************************************/
package com.atsys.tims.rct.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsys.model.TbNtcStepVo;
import com.atsys.model.TbNtcVo;
import com.atsys.tims.rct.dao.Rct1100eDao;
import com.atsys.tims.rct.service.Rct1100eService;

@Service("Rct1100eService")
public class Rct1100eServiceImpl implements Rct1100eService{

	@Autowired
	Rct1100eDao rct1100eDao;
	
	@Override
	public int save(TbNtcVo vo) throws Exception {
		String ntcNum = vo.getNtcYear()+ "-" + vo.getNtcSmt() + "-" + vo.getNtcType() + "-";
		vo.setNtcNum(ntcNum);
		rct1100eDao.insertNtc(vo);
		
		String ntcCd = vo.getNtcCd();
		
		int res = 0;
	
		if(ntcCd != null) {
			vo.setNtcCd(ntcCd);
			res = insertStep(vo);
		}
		
		return res;
	}
	
	int insertStep(TbNtcVo vo) {

		TbNtcStepVo ntcStep = new TbNtcStepVo();
		
		String[] stepStartDates = {vo.getStepStartDate1(), vo.getStepStartDate2(), vo.getStepStartDate3()};
		String[] stepEndDates = {vo.getStepEndDate1(), vo.getStepEndDate2(), vo.getStepEndDate3()};
		String[] stepAnnDates = {vo.getStepAnnDate1(), vo.getStepAnnDate2(), vo.getStepAnnDate3()};

		int res = 0;
		
		for (int i = 1; i <= 3; i++) {
		    ntcStep.setStepStartDate(stepStartDates[i-1]);
		    ntcStep.setStepEndDate(stepEndDates[i-1]);
		    ntcStep.setStepAnnDate(stepAnnDates[i-1]);
		    ntcStep.setNtcCd(vo.getNtcCd());
		    ntcStep.setStep(String.valueOf(i));
		    
		    res += rct1100eDao.insertStep(ntcStep);
		}
		
		return res;		
	}

	@Override
	public List<TbNtcVo> selectList(Map<String, Object> map) throws Exception {
		
		return rct1100eDao.selectList(map);
	}
	
}
