/*************************************************************
  프로그램명 : OracleErrorMessageTranslator.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.jdbc.support;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;


public class OracleErrorMessageTranslator implements IDatabaseErrorMessageTranslator {
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Override
	public boolean canTranslate(Database db) {
		if (db == Database.ORACLE) {
			return true;
		}
		
		return false;
	}

	@Override
	public DatabaseErrorMessage translateMessage(int errorCode, String state, String errorMessage, DataAccessException dae) {

		String message = null;
		
		if (errorCode > 0) {
			message = messageSource.getMessage(Database.ORACLE.toString() + "." + generateRealErrorCode(errorCode), 
					null, errorMessage, LocaleContextHolder.getLocale());
		}
		
		return new DatabaseErrorMessage(errorCode, state, message);
	}
	
	private String generateRealErrorCode(int errorCode) {
		
		String realErrorCode = ""+errorCode;
		int digit = (""+realErrorCode).length();
		
		for (int i = 0; i < 5-digit; i++) {
			realErrorCode = "0" + realErrorCode;
		}
		
		return "ORA-" + realErrorCode;
		
	}

}
