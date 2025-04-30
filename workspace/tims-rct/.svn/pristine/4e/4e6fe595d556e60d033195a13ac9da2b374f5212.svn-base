/*************************************************************
  프로그램명 : BaseSortedSo.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.model;

import java.util.ArrayList;
import java.util.List;

import com.atsys.nxf.app.vo.Pagination;

/**
 * @Class Name : BaseSortedSo.java
 * @Description : BaseSortedSo class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 7. 31.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public abstract class BaseSortedSo extends Pagination {

	private static final long serialVersionUID = 3953039446020777044L;

	protected int[] defaultOrderBy;
	protected List<String> searchOrderByList = new ArrayList<String>();
	protected int searchOrderColumn;
	protected int searchOrderDirection;
	
	
	public BaseSortedSo(){
		setDefaultOrderBy();
	}

	protected abstract void setDefaultOrderBy();

	protected abstract String getSearchOrderBy(int i, int j);

	public final List<String> getSearchOrderByList() {
		searchOrderByList.add( getSearchOrderBy(getSearchOrderColumn(), getSearchOrderDirection()));
		if( defaultOrderBy != null && defaultOrderBy.length == 2 && getSearchOrderColumn() != defaultOrderBy[0])
			searchOrderByList.add( getSearchOrderBy(defaultOrderBy[0], defaultOrderBy[1]));
		return searchOrderByList;
	}

	public final int getSearchOrderColumn() {
		return searchOrderColumn;
	}
	public final void setSearchOrderColumn(int searchOrderColumn) {
		this.searchOrderColumn = searchOrderColumn;
	}
	public final int getSearchOrderDirection() {
		return searchOrderDirection;
	}
	public final void setSearchOrderDirection(int searchOrderDirection) {
		this.searchOrderDirection = searchOrderDirection;
	}
    
}
