/*************************************************************
  프로그램명 : Pagination.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.app.vo;


import java.io.Serializable;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;

public class Pagination implements Serializable {
	
	private static final long serialVersionUID = -9216871599083738516L; 
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	private TimsPagination paginationInfo;
	
	public Pagination() {
		paginationInfo = new TimsPagination();

		//FIXME property service 를 활용하여 초기값이 등록될 수 있도록 향후 수정 
		setCurrentPageNo(1);
		setRecordCountPerPage(10);
		setPageSize(5);
	}

	public TimsPagination getPaginationInfo() {
		return paginationInfo;
	}
	
	public void setPaginationInfo(TimsPagination paginationInfo) {
		this.paginationInfo = paginationInfo;
	}

	public int getCurrentPageNo() {
		return paginationInfo.getCurrentPageNo();
	}

	public void setCurrentPageNo(int currentPageNo) {
		paginationInfo.setCurrentPageNo(currentPageNo);
	}

	public int getRecordCountPerPage() {
		return paginationInfo.getRecordCountPerPage();
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		paginationInfo.setRecordCountPerPage(recordCountPerPage);
	}

	public int getPageSize() {
		return paginationInfo.getPageSize();
	}

	public void setPageSize(int pageSize) {
		paginationInfo.setPageSize(pageSize);
	}

	public int getTotalRecordCount() {
		return paginationInfo.getTotalRecordCount();
	}

	public void setTotalRecordCount(int totalRecordCount) {
		paginationInfo.setTotalRecordCount(totalRecordCount);
	}
	
	public int getTotalPageCount() {
		return paginationInfo.getTotalPageCount();
	}
	
	public int getFirstPageNo(){
		return paginationInfo.getFirstPageNo();
	}
	
	public int getLastPageNo(){
		return paginationInfo.getLastPageNo();		
	}
	
	public int getFirstPageNoOnPageList() {
		return paginationInfo.getFirstPageNoOnPageList();
	}
	
	public int getLastPageNoOnPageList() {
		return paginationInfo.getLastPageNoOnPageList();
	}

	public int getFirstRecordIndex() {
		return paginationInfo.getFirstRecordIndex();
	}

	public int getLastRecordIndex() {
		return paginationInfo.getLastRecordIndex();
	}	

}