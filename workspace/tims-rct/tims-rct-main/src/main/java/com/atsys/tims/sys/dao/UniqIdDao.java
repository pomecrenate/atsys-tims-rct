/*************************************************************
 프로그램명 : PkSeqDao.java
 설명 : (과정, 차수)pk 생성 Dao
 작성자 : 김병주
 일자 : 2021.10.27 
*************************************************************/
package com.atsys.tims.sys.dao;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UniqIdDao {
	public EgovMap selectTbUniqId(Object vo);
	
	public int updatePkSeq(Object vo);
}