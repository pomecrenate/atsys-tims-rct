/*************************************************************
 프로그램명 : Evl1103eDao.java
 설명 : 심사관리
 작성자 : 이예찬
 일자 : 2025.05.16
*************************************************************/
package com.atsys.tims.evl.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.Evl1103eVo;
import com.atsys.model.TbEvalResultVo;
import com.atsys.model.TbEvalScoreVo;

@Repository
@Mapper
public interface Evl1103eDao {
    List<Evl1103eVo> selectInfo(Map<String, Object> so);
    
    String selectResultCd(Map<String, Object> so);
    
    List<TbEvalResultVo> selectApplicants(Map<String, Object> so);
    
    List<TbEvalScoreVo> selectEvalDetails(Map<String, Object> so);
    
    String selectSheetCdByAppCd(Map<String, Object> params);
    
    List<Map<String, Object>> selectScoreCdsByResultCd(String resultCd);
    
    int insertEvalResult(TbEvalResultVo evalResult);
    
    int updateEvalResult(TbEvalResultVo evalResult);
    
	/*
	 * int deleteEvalResult(String resultCd);
	 * 
	 * int deleteEvalScoresByResultCd(String resultCd);
	 * 
	 * int checkResultDeletable(Map<String, Object> so);
	 */
    
    int insertEvalScore(TbEvalScoreVo evalScore);
    
    int updateEvalScore(TbEvalScoreVo evalScore);
    
    int deleteEvalScore(String scoreCd);

    int confirmResult(Map<String, Object> so);
    
    int checkConfirmCancelable(Map<String, Object> so);
} 