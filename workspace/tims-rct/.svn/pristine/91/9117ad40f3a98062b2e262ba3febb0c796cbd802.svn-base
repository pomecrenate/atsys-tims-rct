/*************************************************************
  프로그램명 : TemporaryUriMapper.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.servlet.handler.util;

import java.util.HashMap;
import java.util.Map;

public class TemporaryUriMapper {
	private final static String TEMP_URI = "/temp";
	
	private static Map<String, String> mapping = new HashMap<String, String>();
	
	static {
		mapping.put("/adm/bbsBoardMgr.do", "/bbs");
		mapping.put("/adm/zipcodeMgr.do", "/zip");
		mapping.put("/adm/userMgr.do", "/ugm/user");
		mapping.put("/adm/groupMgr.do", "/group");
	}
	
	public static String toUccUri(String uri) {
		if (uri == null) {
			return null;
		}
		
		String uccUri = mapping.get(uri);
		
		if (uccUri == null) {
			uccUri = TEMP_URI;
		}
		
		return uccUri;
	}
}
