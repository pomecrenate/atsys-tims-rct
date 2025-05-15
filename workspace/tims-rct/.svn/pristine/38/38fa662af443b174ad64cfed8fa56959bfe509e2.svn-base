/*************************************************************
 프로그램명 : Bas1200eService.java
 설명 : 알림 문구 관리
 작성자 : 최연재
 일자 : 2025.05.07
*************************************************************/
package com.atsys.tims.bas.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbAnnVo;

@Transactional (readOnly = true)
public interface Bas1200eService {
	
	@Transactional
	public int save(Map<String, Object> so) throws Exception;
	
	public List<TbAnnVo> selectList(Map<String, Object> so) throws Exception;
}