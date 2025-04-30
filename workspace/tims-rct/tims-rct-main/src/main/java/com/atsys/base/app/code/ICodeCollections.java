/*************************************************************
  프로그램명 : ICodeCollections.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.app.code;

import com.atsys.search.CodeSelectionSo;


public interface ICodeCollections {

	CodeSelectionSo getCodeColls() throws Exception;

}
