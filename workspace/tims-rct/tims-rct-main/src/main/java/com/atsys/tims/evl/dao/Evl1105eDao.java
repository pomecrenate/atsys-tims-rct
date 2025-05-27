/*************************************************************
 프로그램명 : Evl1105eDao.java
 설명 : 결과관리
 작성자 : 이예찬
 일자 : 2025.05.23
*************************************************************/
package com.atsys.tims.evl.dao;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.Evl1104eVo;
import com.atsys.model.Evl1105eVo;
import com.atsys.model.Evl1105p1Vo;
import com.atsys.model.TbEvalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;

@Repository
@Mapper
public interface Evl1105eDao {
    List<Evl1105eVo> selectInfo(Map<String, Object> so);
    
    List<Evl1104eVo> selectApplicants(Map<String, Object> so);
    
    List<TbSelEvalJdgVo> selectJudges(Map<String, Object> so);
    
    int updatePassStep(Map<String, Object> so);
    
    Evl1105p1Vo selectResultInfo(Map<String, Object> so);
    
    List<TbEvalScoreVo> selectResultScores(Map<String, Object> so);
} 