package com.atsys.common.base;
import java.util.Map;

import com.atsys.base.util.Utilities;

public class PagingRow extends Row {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3360302547257200614L;

	public PagingRow() { 
		set("currentPageNo", 1);
		set("recordCountPerPage", 10);
		set("pageSize", 10);
		set("totalRecordCount", 0);
	}
	
	public int getCurrentPageNo() {
		return getInt("currentPageNo");
	}
	
	public void setCurrentPageNo(int currentPageNo) {
		set("currentPageNo", currentPageNo);
	}
	
	public int getRecordCountPerPage() {
		return getInt("recordCountPerPage");
	}
	
	public void setRecordCountPerPage(int recordCountPerPage) {
		int recordCountPerPage2 = recordCountPerPage;
		if(recordCountPerPage2 == 0) {
			recordCountPerPage2 = 10;
		}
		set("recordCountPerPage", recordCountPerPage2);
	}
	
	public int getPageSize() {
		return getInt("pageSize");
	}
	
	public void setPageSize(int pageSize) {
		set("pageSize", pageSize);
	}
	
	public int getTotalRecordCount() {
		return getInt("totalRecordCount");
	}
	
	public void setTotalRecordCount(int totalRecordCount) {
		set("totalRecordCount", totalRecordCount);
	}
	
	public int getTotalPageCount() {
		return ((getTotalRecordCount( ) -1) / getRecordCountPerPage()) + 1;
	}

	public int getFirstPageNoOnPageList() {
		return ((getCurrentPageNo() - 1) / getPageSize()) * getPageSize() + 1;
	}
	
	public int getLastPageNoOnPageList() {
		int lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;		
		if(lastPageNoOnPageList > getTotalPageCount()) {
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}
	
	public int getFirstRecordIndex() {
		return (getCurrentPageNo() - 1) * getRecordCountPerPage();
	}
	
	public int getLastRecordIndex() {
		return getCurrentPageNo() * getRecordCountPerPage();
	}
	
	public int getFirstPageNo() {
		return 1;
	}
	
	public int getLastPageNo() {
		return getTotalPageCount();		
	}
	
	public Map<String, Object> getProperties() {
		return this;
	}
	
	@Override
	public Object put(String key, Object value) {
		String key2 = key;
		if(key2.startsWith("properties['")) {
			key2 = key2.substring("properties['".length(), key2.length() - 2);
		}
	
		if("currentPageNo".equalsIgnoreCase(key2) || "pageSize".equalsIgnoreCase(key2)|| "recordCountPerPage".equalsIgnoreCase(key2)|| "totalRecordCount".equalsIgnoreCase(key2)) {
			return super.put(key2, Utilities.parseInt(value.toString()));
		} else {
			return super.put(key2, value);
		}
	}
	
	@Override
	public boolean containsKey(Object key) {
		Object key2 = key;
		if(key2.toString().startsWith("properties['")) {
			String strKey = key2.toString();
			strKey = strKey.substring("properties['".length(), strKey.length() - 2);
			key2 = strKey;
		}
		return super.containsKey(key2);
	}
	
	@Override
	public Object remove(Object key) {
		Object key2 = key;
		if(key2.toString().startsWith("properties['")) {
			String strKey = key2.toString();
			strKey = strKey.substring("properties['".length(), strKey.length() - 2);
			key2 = strKey;
		}		
		return super.remove(key2);
	}
}
