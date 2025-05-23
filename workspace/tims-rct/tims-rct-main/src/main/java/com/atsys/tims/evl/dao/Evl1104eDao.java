/*************************************************************
 프로그램명 : Evl1104eDao.java
 설명 : 사정관리
 작성자 : 이예찬
 일자 : 2025.05.22
*************************************************************/
package com.atsys.tims.evl.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.Evl1104eVo;
import com.atsys.model.TbEvalTotalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;

@Repository
@Mapper
public interface Evl1104eDao {
    List<TbEvalTotalScoreVo> selectInfo(Map<String, Object> so);
    
    List<Evl1104eVo> selectApplicants(Map<String, Object> so);
    
    List<TbSelEvalJdgVo> selectJudges(Map<String, Object> so);
    
    String selectTotalCd(Map<String, Object> so);
    
    List<Map<String, Object>> selectConfirmedScores(Map<String, Object> so);
    
	/* String selectStaffTypeByAppCd(Map<String, Object> so); */
    
    int insertTotalScore(TbEvalTotalScoreVo totalScore);
    
    int updateTotalScore(TbEvalTotalScoreVo totalScore);
    
    int deleteTotalScore(Map<String, Object> so);
} 