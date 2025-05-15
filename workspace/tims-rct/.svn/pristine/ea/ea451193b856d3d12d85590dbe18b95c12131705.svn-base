/*************************************************************
 프로그램명 : Rct1101eDao.java
 설명 : 공고관리-초빙(임용)분야
 작성자 : 최연재
 일자 : 2025.05.12
*************************************************************/
package com.atsys.tims.rct.dao;


import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.DeptVo;
import com.atsys.model.Rct1101eVo;
import com.atsys.model.TbNtcFieldVo;
import com.atsys.model.TbNtcStepVo;

@Repository
@Mapper 
public interface Rct1101eDao {

	public List<TbNtcFieldVo> selectList(Map<String, Object> map);
	public List<DeptVo> selectDeptList(Map<String, Object> map);
	
	public String selectNtcType(Map<String, Object> map);
	
	public int save(Map<String, Object> map);
	
	public List<TbNtcStepVo> selectNtcList(Map<String, Object> map);
	
	public int insertField(Rct1101eVo vo);
	
	public int selectApp(Rct1101eVo vo);
	
	public int deleteYnField(Rct1101eVo vo);
}
