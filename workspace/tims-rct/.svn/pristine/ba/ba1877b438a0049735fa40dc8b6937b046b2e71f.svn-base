/*************************************************************
  프로그램명 : HandlerUtils.java
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

import org.springframework.web.method.HandlerMethod;

public class HandlerUtils {

	// for spring 3.1.x
	public static boolean isInstance(Object handler, Class<?> clazz) {
		if (!(handler instanceof HandlerMethod)) {
			return false;
		}
		
		Object object = ((HandlerMethod) handler).getBean();
		if (clazz.isInstance(object)) {
			return true;
		}
		
		return false;
	}
	
	// for spring 3.1.x
	@SuppressWarnings("unchecked")
	public static <T> T toHandlerClass(Object handler, Class<T> t) {
		if (handler instanceof HandlerMethod) {
			Object object = ((HandlerMethod) handler).getBean();
			
			if (t.isInstance(object)) {
				return (T)object;
			}
		}
		
		return null;
	}
}
