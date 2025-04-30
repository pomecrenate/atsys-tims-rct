/*************************************************************
  프로그램명 : GenericUtils.java
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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils { 

	public static Class<?> findTypeClass(Class<?> clazz) {
		Class<?> classT = null;
		
		ParameterizedType genericSuperclass = (ParameterizedType) clazz.getGenericSuperclass();
		 
		Type type = genericSuperclass.getActualTypeArguments()[0];
		
		if (type instanceof ParameterizedType) {
			classT = (Class<?>) ((ParameterizedType) type).getRawType();
		} else {
			classT = (Class<?>) type;
		}
		
		return classT;
		
	} 
}
