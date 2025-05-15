/*************************************************************
 프로그램명 : NtcInfoDao.java
 설명 : 공고관리-공고정보
 작성자 : 최연재
 일자 : 2025.05.12
*************************************************************/
package com.atsys.tims.rct.dao;


import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.TbNtcInfoVo;

@Repository
@Mapper 
public interface NtcInfoDao {

	public int save(Map<String, Object> map);
	public int update(Map<String, Object> map);
	
	public TbNtcInfoVo selectOne (Map<String, Object> map);
}
