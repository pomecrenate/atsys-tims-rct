/*************************************************************
  프로그램명 : IDatabaseErrorMessageTranslator.java
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

import org.springframework.dao.DataAccessException;


public interface IDatabaseErrorMessageTranslator {
	
	public enum Database {
		DB2, DERBY, H2, HSQL, INFORMIX, MSSQL, MYSQL, ORACLE, POSTRRE_SQL, SYBASE;

	@Override
	public String toString() {
		
		switch (this) {
		case DERBY:
			return "Derby";
		case INFORMIX:
			return "Informix";
		case MSSQL:
			return "MS-SQL";
		case MYSQL:
			return "MySQL";
		case ORACLE:
			return "Oracle";
		case POSTRRE_SQL:
			return "PostgreSQL";
		case SYBASE:
			return "Sybase";

		default:
			return name();
		}
	}}
	
	boolean canTranslate(Database db);
	
	DatabaseErrorMessage translateMessage(int errorCode, String state, String errorMessage, DataAccessException dae);

}
