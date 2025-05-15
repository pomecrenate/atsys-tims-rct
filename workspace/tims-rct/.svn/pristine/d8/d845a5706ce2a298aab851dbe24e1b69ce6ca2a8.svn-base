/*************************************************************
 프로그램명 : Evl1102eService.java
 설명 : 평가표관리
 작성자 : 이예찬
 일자 : 2025.05.13
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbEvalSheetVo;

@Transactional(readOnly = true)
public interface Evl1102eService {
    
	List<TbEvalSheetVo> getInfo(Map<String, Object> so) throws Exception;
    
	Map<String, Object> getEvalSheetWithQsts(Map<String, Object> so) throws Exception;
    
    List<TbEvalSheetVo> selectEvalSheetList(Map<String, Object> so) throws Exception;
    
    @Transactional
    int deleteEvalSheet(List<String> sheetCds) throws Exception;
    
    @Transactional
    List<Integer> saveEvalSheet(Map<String, Object> sheet, List<Map<String, Object>> qsts) throws Exception;
} 