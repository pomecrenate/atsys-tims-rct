package com.atsys.common.base;


import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class PaginRow extends PagingRow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3360302547257200614L;

	public PaginationInfo getPaginationInfo() {
		PaginationInfo info = new PaginationInfo();
		
		info.setCurrentPageNo(getCurrentPageNo());
		info.setRecordCountPerPage(getRecordCountPerPage());
		info.setPageSize(getPageSize());
		info.setTotalRecordCount(getTotalRecordCount());
		return info;
	}
	
}
