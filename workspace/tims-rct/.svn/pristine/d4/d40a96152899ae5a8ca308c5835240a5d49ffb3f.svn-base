/*************************************************************
 프로그램명 : Bas1100eService.java
 설명 : 코드관리
 작성자 : 최연재
 일자 : 2025.04.10
*************************************************************/
package com.atsys.tims.bas.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.TbCmmVo;

@Transactional (readOnly = true)
public interface Bas1100eService {
	
	/* 공통코드 저장 */
	@Transactional
	public Map<String, Object> save(TbCmmVo vo) throws Exception;
	
	/* 대구분 조회 */
	public int selectTotalCount(Object so) throws Exception;
	List<TbCmmVo> selectTbCommonCdList(Object so) throws Exception;
	
	/* 소구분 조회 */
	public int selectSmallCdTotalCount(Object so) throws Exception;
	List<TbCmmVo> selectSmallCdList(Object so) throws Exception;
}
