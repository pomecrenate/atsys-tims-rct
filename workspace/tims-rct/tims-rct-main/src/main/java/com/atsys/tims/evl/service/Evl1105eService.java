/*************************************************************
 프로그램명 : Evl1105eService.java
 설명 : 결과관리
 작성자 : 이예찬
 일자 : 2025.05.23
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import com.atsys.model.Evl1104eVo;
import com.atsys.model.Evl1105eVo;
import com.atsys.model.Evl1105p1Vo;
import com.atsys.model.TbEvalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;

public interface Evl1105eService {
    List<Evl1105eVo> getInfo(Map<String, Object> so) throws Exception;
    
    List<Evl1104eVo> getApplicants(Map<String, Object> so) throws Exception;
    
    List<TbSelEvalJdgVo> getJudges(Map<String, Object> so) throws Exception;
    
    int updatePassStep(Map<String, Object> so) throws Exception;
    
    Evl1105p1Vo getResultInfo(Map<String, Object> so) throws Exception;
    
    List<TbEvalScoreVo> getResultScores(Map<String, Object> so) throws Exception;
} 