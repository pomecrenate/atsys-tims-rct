/*************************************************************
  프로그램명 : ICodeSupport.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.app.code;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

public interface ICodeSupport {
	@SuppressWarnings("rawtypes")
	Map<String, List> getCodeList(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception;
}
