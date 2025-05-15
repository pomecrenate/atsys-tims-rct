/*************************************************************
 프로그램명 : NtcInfoService.java
 설명 : 공고관리-공고정보
 작성자 : 최연재
 일자 : 2025.05.12
*************************************************************/
package com.atsys.tims.rct.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbNtcInfoVo;

@Transactional(readOnly = true)
public interface NtcInfoService {
	
	public int save(Map<String, Object> map) throws Exception;
	
	public TbNtcInfoVo search(Map<String, Object> map) throws Exception;
}	
