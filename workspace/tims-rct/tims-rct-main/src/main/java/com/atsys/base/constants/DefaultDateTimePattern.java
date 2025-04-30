/*************************************************************
  프로그램명 : DateTimePattern.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.constants;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @Class Name : DateTimePattern.java
 * @Description : DateTimePattern class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 5. 28.
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public final class DefaultDateTimePattern {
	private static final String DATE_PLAIN ="yyyyMMdd";
	private static final String YEAR ="yyyy"; 
	
	public static final String dPlain( Date date){
		if( date == null)
			return null;

		return DateFormatUtils.format( date, DATE_PLAIN);
	}
	public static final String year( Date date){
		if( date == null)
			return null;

		return DateFormatUtils.format( date, YEAR);
	}
}
