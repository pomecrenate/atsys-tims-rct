/*************************************************************
 프로그램명 : Evl1100eService.java
 설명 : 평가관리
 작성자 : 이예찬
 일자 : 2025.04.29
*************************************************************/
package com.atsys.tims.evl.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbNtcStepVo;


@Transactional(readOnly = true)
public interface Evl1100eService { 
	
	List<TbNtcStepVo> selectList(Map<String, Object> so) throws Exception;

	int selectTotalCount(Map<String, Object> so); 
	
}
