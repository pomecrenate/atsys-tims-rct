/*************************************************************
 프로그램명 : Evl1100eDao.java
 설명 : 평가관리
 작성자 : 이예찬
 일자 : 2025.04.29
*************************************************************/
package com.atsys.tims.evl.dao;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.TbNtcStepVo;

@Repository
@Mapper 
public interface Evl1100eDao {

	List<TbNtcStepVo> selectList(Object so);

	int selectTotalCount(Object so); 

}
