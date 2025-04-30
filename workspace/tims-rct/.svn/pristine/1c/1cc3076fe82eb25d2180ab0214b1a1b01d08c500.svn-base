/*************************************************************
  프로그램명 : IContextSupport.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.atsys.nxf.core.web.bind.support.MoveMethod.Move;

public interface IContextSupport {

	String[] getStorableContextNamesOnModel(Move move);
	
	ModelMap getModel();
	
	Map<String, Object> getRecoveredDataOnException(Move move, HttpServletRequest request, ModelMap model) throws Exception;
}
