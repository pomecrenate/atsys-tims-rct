/*************************************************************
 프로그램명 : PkSeqService.java
 설명 : (과정, 차수)pk 생성 service
 작성자 : 김병주
 일자 : 2021.10.27 
*************************************************************/
package com.atsys.tims.sys.service;

import java.util.Map;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UniqIdService {
	
	public EgovMap selectTbUniqId(Object so) throws Exception;
	
	@Transactional
	public Map<String, Object> selectPkCd(Map<String, Object> so) throws Exception;
}
