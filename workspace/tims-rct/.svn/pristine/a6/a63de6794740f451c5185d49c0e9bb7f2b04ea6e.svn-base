/*************************************************************
  프로그램명 : ImagePaginationRenderer.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.ui;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.atsys.nxf.app.page.AbstractExtendedPaginationRenderer;

/**
 * @Class Name : ImagePaginationRenderer.java
 * @Description : ImagePagination Renderer class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 5. 20.
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class ImagePaginationRenderer extends AbstractExtendedPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;
	
	public ImagePaginationRenderer() {
	
	}
	
	public void initVariables(){
		firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/admin/btn_fist.gif\" alt=\"첫번째 페이지로 이동\" border=\"0\"/></a>";
        previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/admin/btn_pre.gif\"  alt=\"이전페이지로 이동\"   border=\"0\"/></a>";
        currentPageLabel  = "<a href=\"?pageIndex={0}\" onclick=\"return false; \" class=\"on\"><span>{0}</span></a>";
        otherPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><span>{2}</span></a>";
        nextPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/admin/btn_next.gif\" alt=\"다음페이지로 이동\"   border=\"0\"/></a>";
        lastPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/admin/btn_last.gif\" alt=\"마지막 페이지로 이동\" border=\"0\"/></a>";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
