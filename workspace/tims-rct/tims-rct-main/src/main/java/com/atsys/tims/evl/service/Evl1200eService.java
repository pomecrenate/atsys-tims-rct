/*************************************************************
 프로그램명 : Evl1200eService.java
 설명 : 합격관리
 작성자 : 백세진
 일자 : 2025.04.09
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbNtcStepVo;

@Transactional(readOnly = true)
public interface Evl1200eService { 
	
	List<TbNtcStepVo> selectList(Map<String, Object> so) throws Exception;

	int selectTotalCount(Map<String, Object> so); 
	
}
