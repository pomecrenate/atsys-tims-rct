/*************************************************************
 프로그램명 : Rct1101eService.java
 설명 : 공고관리-초빙(임용)분야
 작성자 : 최연재
 일자 : 2025.05.12
*************************************************************/
package com.atsys.tims.rct.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.DeptVo;
import com.atsys.model.Rct1101eVo;
import com.atsys.model.TbNtcFieldVo;
import com.atsys.model.TbNtcStepVo;

@Transactional(readOnly = true)
public interface Rct1101eService {
	
	public List<TbNtcFieldVo> selectList(Map<String, Object> map) throws Exception;
	public List<DeptVo> selectDeptList(Map<String, Object> map) throws Exception;
	
	public String selectNtcType(Map<String, Object> map) throws Exception;
	
	public int save(Map<String, Object> map) throws Exception;
	
	public List<TbNtcStepVo> selectNtcList(Map<String, Object> map) throws Exception;
	
	public int addField(Rct1101eVo vo) throws Exception;
	
	public int checkApp(Rct1101eVo vo) throws Exception;
	
	public int deleteField(Rct1101eVo vo) throws Exception;
}	
