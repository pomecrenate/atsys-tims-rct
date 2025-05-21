/*************************************************************
 프로그램명 : Evl1103eService.java
 설명 : 심사관리
 작성자 : 이예찬
 일자 : 2025.05.16
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.Evl1103eVo;
import com.atsys.model.TbEvalResultVo;
import com.atsys.model.TbEvalScoreVo;

@Transactional(readOnly = true)
public interface Evl1103eService {
    List<Evl1103eVo> getInfo(Map<String, Object> so) throws Exception;
    
    List<TbEvalResultVo> getApplicants(Map<String, Object> so) throws Exception;
    
    List<TbEvalScoreVo> getEvalSheetWithScores(Map<String, Object> so) throws Exception;
    
    @Transactional
    List<Integer> saveAndConfirmResult(Map<String, Object> result, List<Map<String, Object>> scores) throws Exception;
    
    @Transactional
    int cancelConfirmResult(Map<String, Object> so) throws Exception;
    
    String getResultCd(Map<String, Object> so) throws Exception;
} 