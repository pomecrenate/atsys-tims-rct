/*************************************************************
  프로그램명 : BitwiseUtils.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.app.util;

/**
 * @Class Name : BitwiseUtils.java
 * @Description : BitwiseUtils class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 11. 21.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public class BitwiseUtils {
	
	public final static boolean match(long a, long b, boolean eq){
		if( eq)
			return (a & b) == b ? true : false;
		else
			return (a & b) > 0 ? true : false;
	}
	
	
	public final static long bitor( long... a){
		long bits = 0l;
		for (long l : a) {
			bits |= l;
		}
		return bits;
	}

}
