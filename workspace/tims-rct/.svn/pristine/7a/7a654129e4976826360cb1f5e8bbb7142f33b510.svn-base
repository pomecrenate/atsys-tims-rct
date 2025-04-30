/*************************************************************
 프로그램명 : Rct1100eService.java
 설명 : 공고관리
 작성자 : 최연재
 일자 : 2025.04.29
*************************************************************/
package com.atsys.tims.rct.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbNtcVo;

@Transactional(readOnly = true)
public interface Rct1100eService {

	@Transactional
	public int save(TbNtcVo vo) throws Exception;
	
	public List<TbNtcVo> selectList(Map<String, Object> map) throws Exception;
}
