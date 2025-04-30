/*************************************************************
  프로그램명 : ContextDirtyManager.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.servlet.handler.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("dirtyManager")
public class ContextDirtyManager {
	
	private final Map<String, Long> map;
	
	public ContextDirtyManager() {
		map = new HashMap<String, Long>();
	}
	
	public void setDirtyStatus(String contextName) {
		map.put(contextName, System.currentTimeMillis());
	}
	
	public Long getContextTimestamp(String context) {
		Map<String, Long> ret = new HashMap<String, Long>();
		ret = this.map;
		return ret.get(context);
	}
	
	public boolean isDirty(String contextName, Long timeStamp) {
		Long value = map.get(contextName);
		
		if (value == timeStamp) {
			return false;
		} else {
			return true;
		}
	}

}
