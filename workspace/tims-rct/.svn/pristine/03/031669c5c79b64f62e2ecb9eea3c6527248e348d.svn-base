/*************************************************************
  프로그램명 : CodeCollectionService.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.tims.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.model.CodeSelectionVo;
import com.atsys.search.CodeSelectionSo;

/**
 * @Class Name : CodeCollectionService.java
 * @Description : CodeCollectionService class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 5. 6.
 * @version 1.0
 * @see
 *
 *  Copyright (C)  All right reserved.
 */
@Transactional(readOnly = true)
public interface CodeCollectionService {

	public Map<String, List<CodeSelectionVo>> selectCodeList(CodeSelectionSo so) throws Exception;
	public Map<String, String> selectList(CodeSelectionSo so) throws Exception;

}
