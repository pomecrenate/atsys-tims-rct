/*************************************************************
  프로그램명 : DefaultDatabaseErrorMessageTranslator.java
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


public class DefaultDatabaseErrorMessageTranslator implements IDatabaseErrorMessageTranslator {
	
	private static final String DEFAULT_ERRORCODE_PREFIX = "database";
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	protected enum DataAccessExceptionType {
		BAD_SQL_GRAMMAR, INVALID_RESULT_SET_ACCESS, DUPLICATE_KEY, 
		DATA_INTEGRITY_VIOLATION, PERMISSION_DENIED, DATA_ACCESS_RESOURCE_FAILURE, 
		TRANSIENT_DATA_ACCESS_RESOURCE, CANNOT_ACQUIRE_LOCK, DEADLOCK_LOSER,
		CANNOT_SERIALIZE_TRANSACTION, UNKNOWN;
	}

	@Override
	public boolean canTranslate(Database db) {
		return true;
	}

	@Override
	public DatabaseErrorMessage translateMessage(int errorCode, String state, String errorMessage, DataAccessException daex) {

		String message = null;
		
		if (daex != null) {
			message = generateErrorMessage(daex);
		}
		
		if (message == null) {
			message = errorMessage;
		}
		
		return new DatabaseErrorMessage(errorCode, state, generateErrorMessage(daex));
	}
	
	private String generateErrorMessage(DataAccessException daex) {
		
		DataAccessExceptionType daetype = DataAccessExceptionType.UNKNOWN;
		
		if(daex == null) {
			return null;
		}
		String name = daex.getClass().getName();
		
		for (DataAccessExceptionType type : DataAccessExceptionType.values()) {
			if (type.name().equalsIgnoreCase(name)) {
				daetype = type;
				break;
			}
		}
		
		String errorMessageCode = DEFAULT_ERRORCODE_PREFIX + daetype.name().toLowerCase();
		
		return messageSource.getMessage(errorMessageCode, null, LocaleContextHolder.getLocale());
	}

}
