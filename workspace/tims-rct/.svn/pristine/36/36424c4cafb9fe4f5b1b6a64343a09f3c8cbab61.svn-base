/*************************************************************
  프로그램명 : DatabaseErrorMessageHelper.java
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Component;

import com.atsys.nxf.core.jdbc.support.IDatabaseErrorMessageTranslator.Database;

@Component
public class DatabaseErrorMessageHelper {

	@Resource(name = "dataSource")
	private DataSource dataSource;

	private List<IDatabaseErrorMessageTranslator> translators;

	private Logger LOGGER = Logger.getLogger(this.getClass());

	public List<IDatabaseErrorMessageTranslator> getTranslators() {
		List<IDatabaseErrorMessageTranslator> ret = null;
		if(this.translators != null) {
			ret = new ArrayList<IDatabaseErrorMessageTranslator>();
			ret = this.translators; 
		}
		return ret; 
	}

	public void setTranslators(List<IDatabaseErrorMessageTranslator> translators) {
		if(translators != null) {
			this.translators = new ArrayList<IDatabaseErrorMessageTranslator>();
			this.translators.addAll(translators);
			this.translators.add(new DefaultDatabaseErrorMessageTranslator());
		} 
	}
	
	public DatabaseErrorMessage getErrorMessage(DataAccessException daex) {

		DatabaseErrorMessage translated = null;

		SQLException se = (SQLException)daex.getRootCause();
		int errorCode = se.getErrorCode();
		String errorState = se.getSQLState();
		String errorMessage = se.getMessage();
		Database db = findCurrentDatabase();
		
		for (IDatabaseErrorMessageTranslator translator : translators) {
			if (translator.canTranslate(db)) {
				translated = translator.translateMessage(errorCode, errorState, errorMessage, daex);
			}
			
			if (translated != null) {
				break;
			}
		}
		
		return translated;
	}
	
	@SuppressWarnings("deprecation")
	private Database findCurrentDatabase() {
		
		String dbName = null;

		try {
			dbName = (String) JdbcUtils.extractDatabaseMetaData(dataSource, "getDatabaseProductName");
		} catch (MetaDataAccessException e) {
			LOGGER.debug("Fail to find database name form database meta. Using default database...", e);
		}
		
		return dbName == null ? Database.ORACLE : Database.valueOf(dbName);
	}
}
