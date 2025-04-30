/*************************************************************
  프로그램명 : CodeCollectionDao.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.tims.sys.dao;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.springframework.stereotype.Repository;

import com.atsys.model.CodeSelectionVo;
import com.atsys.search.CodeSelectionSo;


/**
 *
 * @Class Name : CodeCollectionDao.java
 * @Description : CodeCollectionDao class
 * @Modification Information
 *
 * @author frida
 * @since 2014. 4. 29.
 * @version 1.0
 * @see
 *
 *  Copyright (C) at-sys 2013. All right reserved.
 */
@Repository
@Mapper
public interface CodeCollectionDao
{
	/**
	 * 조회 조건으로 검색된 List중 지정된 페이지의 List<CodeSelectionVo> 반환
	 *
	 * @return List<CodeSelectionVo>
	 */
	List<CodeSelectionVo> selectList(CodeSelectionSo so);


}