/*************************************************************
 프로그램명 : Evl1104eService.java
 설명 : 사정관리
 작성자 : 이예찬
 일자 : 2025.05.22
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.Evl1104eVo;
import com.atsys.model.TbEvalTotalScoreVo;
import com.atsys.model.TbSelEvalJdgVo;

@Transactional(readOnly = true)
public interface Evl1104eService {
    List<TbEvalTotalScoreVo> getInfo(Map<String, Object> so) throws Exception;
    
    List<Evl1104eVo> getApplicants(Map<String, Object> so) throws Exception;
    
    List<TbSelEvalJdgVo> getJudges(Map<String, Object> so) throws Exception;
    
    String getTotalCd(Map<String, Object> so) throws Exception;
    
    @Transactional
    List<Integer> calcScores(Map<String, Object> so) throws Exception;
    
    @Transactional
    int cancelCalcScores(Map<String, Object> so) throws Exception;
} 