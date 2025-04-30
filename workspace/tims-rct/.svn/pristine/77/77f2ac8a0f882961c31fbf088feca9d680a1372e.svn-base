/*************************************************************
  프로그램명 : PaginationAspect.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.common.aop;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.egovframe.rte.fdl.property.EgovPropertyService;

import com.atsys.base.ConstantsUtils;
import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.vo.Pagination;
import com.atsys.search.ListSo;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PaginationAspect {

	@Resource(name="propertiesService")
	private EgovPropertyService service;
 
	
	public void listBefore(Object param) throws Exception {
		if(Utilities.getRequest() == null) {
			return;
		}
		if(param instanceof Pagination) {
			listBefore((Pagination)param);
		} else if(param instanceof Map) {
			listBefore((Map)param);
		}
	}
	
	public void listBefore(Pagination page) throws Exception{
		if(Utilities.getRequest() == null) {
			return;
		}
		if(page.getCurrentPageNo() == 0) {
			page.setCurrentPageNo(service.getInt("currentPageNo", 1));
		}
		if(page.getRecordCountPerPage() == 0) {
			page.setRecordCountPerPage(service.getInt("recordCountPerPage", 2));
		}
		if(page.getPageSize() == 0) {
			page.setPageSize(service.getInt("pageSize", 10));
		}
		if(page instanceof ListSo){
			ListSo so = (ListSo)page;
			int cuurentPage = Utilities.parseInt(so.getCurrentPageNo());
			int recordCountPerPage = Utilities.parseInt(so.getRecordCountPerPage());
			if(cuurentPage <= 0) {
				cuurentPage = 1;
			}
			if(recordCountPerPage <= 0) {
				recordCountPerPage = service.getInt("recordCountPerPage", 2);
			}
			so.setCurrentPageNo(cuurentPage);
			so.setOnepagerow(recordCountPerPage + "");
		}
		
		Utilities.getRequest().setAttribute(ConstantsUtils.getPagenationInfo(), page);
	}
	
	public void listBefore(Map map) throws Exception{
		if(Utilities.getRequest() == null) {
			return;
		}
		Pagination pagination = (Pagination)map.get(ConstantsUtils.getPagenationInfo());
		if(pagination != null) {
			return;
		}
		ListSo page = (ListSo)Utilities.mapToBean(map, ListSo.class);
		listBefore(page);

		if(map != null) {
			map.put(ConstantsUtils.getPagenationInfo(), page);
			map.putAll(Utilities.beanToMap(page));
		}
	}
	
	public void selectListBefore(JoinPoint jp) throws Exception {
		if(Utilities.getRequest() == null) {
			return;
		}
		Object page = findPagination(jp.getArgs());
		if(page == null) {
			return;
		}
		listBefore(page);
	}
	
	private Object findPagination(Object[] args) {
		if(args == null || args.length == 0) {
			return null;
		}
		for(Object arg : args) {
			if(arg instanceof Pagination) {
				return (Pagination)arg;
			}
			if(arg instanceof Map) {
				return (Map)arg;
			}
		}
		return null;
	}
}