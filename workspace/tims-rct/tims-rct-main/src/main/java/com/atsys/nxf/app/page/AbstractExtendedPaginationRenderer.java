/*************************************************************
  프로그램명 : AbstractExtendedPaginationRenderer.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.app.page;

import java.text.MessageFormat;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class AbstractExtendedPaginationRenderer extends AbstractPaginationRenderer {
	
	private boolean renderFpnlAlways;

	public boolean isRenderFpnlAlways() {
		return renderFpnlAlways;
	}

	public void setRenderFpnlAlways(boolean renderFpnlAlways) {
		this.renderFpnlAlways = renderFpnlAlways;
	}
	
	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction){
		
		StringBuffer strBuff = new StringBuffer();
		
		boolean renderFpnl = renderFpnlAlways || (paginationInfo.getTotalPageCount() > paginationInfo.getPageSize());
		
		strBuff.append(renderFirstPrevious(paginationInfo, jsFunction, renderFpnl))
				.append(renderBody(paginationInfo, jsFunction))
				.append(renderNextLast(paginationInfo, jsFunction, renderFpnl));
		
		return strBuff.toString();
	}
	
	protected StringBuffer renderFirstPrevious(PaginationInfo paginationInfo, String jsFunction, boolean renderFpnl) {

		StringBuffer strBuff = new StringBuffer();
		
		int firstPageNo = paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		
		if (renderFpnl) {
			strBuff.append(MessageFormat.format(firstPageLabel, new Object[]{jsFunction,Integer.toString(firstPageNo)}));
			
			if (firstPageNoOnPageList > paginationInfo.getPageSize()) {
				strBuff.append(MessageFormat.format(previousPageLabel, new Object[]{jsFunction,Integer.toString(firstPageNoOnPageList-1)}));
	        } else {
				strBuff.append(MessageFormat.format(previousPageLabel, new Object[]{jsFunction,Integer.toString(firstPageNo)}));
	        }
		}
		
		return strBuff;
	}
	
	protected StringBuffer renderBody(PaginationInfo paginationInfo, String jsFunction) {
		
		StringBuffer strBuff = new StringBuffer();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		
		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if (i == currentPageNo) {
        		strBuff.append(MessageFormat.format(currentPageLabel, new Object[]{Integer.toString(i)}));
        	} else {
        		strBuff.append(MessageFormat.format(otherPageLabel, new Object[]{jsFunction,Integer.toString(i),Integer.toString(i)}));
        	}
        }
		
		return strBuff;
	}
	
	protected StringBuffer renderNextLast(PaginationInfo paginationInfo, String jsFunction, boolean renderFpnl) {

		StringBuffer strBuff = new StringBuffer();
		
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int lastPageNo = paginationInfo.getLastPageNo();
		
		if (renderFpnl) {
			if (lastPageNoOnPageList < paginationInfo.getTotalPageCount()) {
	        	strBuff.append(MessageFormat.format(nextPageLabel, new Object[]{jsFunction,Integer.toString(lastPageNoOnPageList + paginationInfo.getPageSize())}));
			} else {
				strBuff.append(MessageFormat.format(nextPageLabel, new Object[]{jsFunction,Integer.toString(lastPageNo)}));
			}
			
			strBuff.append(MessageFormat.format(lastPageLabel, new Object[]{jsFunction,Integer.toString(lastPageNo)}));
		}
		
		return strBuff;
	}
	

}
