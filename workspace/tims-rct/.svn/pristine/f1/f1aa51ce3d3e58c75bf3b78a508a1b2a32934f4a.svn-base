/*************************************************************
 프로그램명 : Evl1101eService.java
 설명 : 평가위원관리
 작성자 : 이예찬
 일자 : 2025.05.08
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.Evl1101eVo;
import com.atsys.model.TbNtcFieldVo;
import com.atsys.model.TbSelEvalJdgVo;

@Transactional(readOnly = true)
public interface Evl1101eService {
    
	Evl1101eVo getInfo(Map<String, Object> so) throws Exception;
    
    List<TbNtcFieldVo> getFields(Map<String, Object> so) throws Exception;
    
    List<TbSelEvalJdgVo> getSelectedJudges(Map<String, Object> so) throws Exception;
    
    List<TbSelEvalJdgVo> getJudgeListByType(String jdgDiv) throws Exception;
    
    List<Integer> insertJudges(List<Map<String, Object>> judges) throws Exception;
    
    int confirmJudges(List<String> usrCds) throws Exception;
    
    int cancelConfirmJudges(List<String> usrCds) throws Exception;
} 