/*************************************************************
 프로그램명 : Bas1300eService.java
 설명 : 메뉴관리
 작성자 : 최연재
 일자 : 2025.04.22
*************************************************************/
package com.atsys.tims.bas.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atsys.base.model.ITreeVo;
import com.atsys.model.TbSysMenuVo;

@Transactional (readOnly = true)
public interface Bas1300eService {
	
	// 프로그램 메뉴 트리 조회
	List<ITreeVo> selectTreeList(Object so) throws Exception;
	
	// 프로그램 메뉴 조회
	List<TbSysMenuVo> selectList(Object so) throws Exception;
	int selectTotalCount(Object so) throws Exception;
}