/*************************************************************
  프로그램명 : ListSo.java
  설명 : 공지사항
 작성자 : 구탁수
 소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.atsys.base.util.Utilities;
import com.atsys.nxf.app.vo.Pagination;

/**
 * @Class Name : ListSo.java
 * @Description : ListSo class
 * @Modification Information
 *
 * @author Yoo Hong Yeol
 * @since 2014. 4. 29.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */
public class ListSo extends Pagination  {

	private static final long serialVersionUID = 3355673309365654569L;
	private String qryUserId;
	
    private List<OrderSo> qrySorts;
    
    private String sidx;
    private String sord;
    private int start;
	private int end;

	private String currentPage;
    private String onepagerow;
    private String iborderby;
    private int intOnepagerow;
    
    private String adminAuth;
    private boolean ready = false;
    private String chgFlag;
    private String selectParam;
    private String selmode;
    private String qryIdOrName;
    private String qryProgramId;
    private String qryMenuCount;
    private String qryWinMode;
	private String qryFileKey;
	private String qryFileSeq;
	private String qryPopTitle;
	private String qrySatDiv;
	private String qrySearchYn;
	private String qryDpsStat;
	
	public int getIntOnepagerow() {
		return intOnepagerow;
	}

	public void setIntOnepagerow(int intOnepagerow) {
		this.intOnepagerow = intOnepagerow;
	}

	public String getQryDpsStat() {
		return qryDpsStat;
	}

	public void setQryDpsStat(String qryDpsStat) {
		this.qryDpsStat = qryDpsStat;
	}

	public String getQrySatDiv() {
		return qrySatDiv;
	}

	public void setQrySatDiv(String qrySatDiv) {
		this.qrySatDiv = qrySatDiv;
	}

	public String getQryFileKey() {
		return qryFileKey;
	}

	public void setQryFileKey(String qryFileKey) {
		this.qryFileKey = qryFileKey;
	}

	public String getQryFileSeq() {
		return qryFileSeq;
	}

	public void setQryFileSeq(String qryFileSeq) {
		this.qryFileSeq = qryFileSeq;
	}

	public String getQryWinMode() {
		return qryWinMode;
	}

	public void setQryWinMode(String qryWinMode) {
		this.qryWinMode = qryWinMode;
	}

	public String getQryMenuCount() {
		return qryMenuCount;
	}

	public void setQryMenuCount(String qryMenuCount) {
		this.qryMenuCount = qryMenuCount;
	}

	public String getQryProgramId() {
		return qryProgramId;
	}

	public void setQryProgramId(String qryProgramId) {
		this.qryProgramId = qryProgramId;
	}

	public ListSo(){
		qrySorts = new ArrayList<OrderSo>();
	}

	public List<OrderSo> getQrySorts() {
		List<OrderSo> ret = null;
		if(this.qrySorts != null) {
			ret = new ArrayList<OrderSo>();
			ret = this.qrySorts; 
		}
		return ret;
//		return qrySorts;
	}

	public void setQrySorts(List<OrderSo> qrySorts) {
		if(qrySorts != null) {
			this.qrySorts = new ArrayList<OrderSo>();
			this.qrySorts.addAll(qrySorts);
		}
//		this.qrySorts = qrySorts;
	};
	
	private void addQrySorts(OrderSo order){
		this.qrySorts.add(order);
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSort() {
		return sord;
	}

	public void setSort(String sord) {
		this.sord = sord;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public String getOnepagerow() {
		return onepagerow;
	}

	public String getIborderby() {
		return iborderby;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
		super.setCurrentPageNo(Utilities.parseInt(currentPage));
		makeRecordIndex();
	}

	public void setOnepagerow(String onepagerow) {
		this.onepagerow = onepagerow;
		super.setRecordCountPerPage(Utilities.parseInt(onepagerow));
		makeRecordIndex();
	}

	public void setIborderby(String iborderby) {
		this.iborderby = iborderby;
		makeOrders();
	}
	@Override
	public void setCurrentPageNo(int currentPageNo) {
		setCurrentPage(currentPageNo+"");
	}
	
	@Override
	public void setRecordCountPerPage(int recordCountPerPage) {
		setOnepagerow(recordCountPerPage+"");
	}
	private void makeOrders(){
		if( !(StringUtils.hasText(iborderby))) {
			return;
		}

//		IBSheetUtil ibUtil = new IBSheetUtil();
//		String concat = ibUtil.makeOrderBy(iborderby).trim();
//		
//		if( StringUtils.hasText(concat)) {
//			qrySorts = new ArrayList<OrderSo>();
//			String[] arr = concat.split(",");
//			for (String str : arr) {
//				if( StringUtils.hasText(str)) {
//					String[] str2 = str.split(" ");
//					String strData = str2[0] + " " + str2[1].toLowerCase();
//					addQrySorts( new OrderSo( strData));
//				}
//			}
//		}
	}
	
	private void makeRecordIndex() {
		if( !(StringUtils.hasText(currentPage) && StringUtils.hasText(onepagerow))) {
			return;
		}
		
		int intCurrentPage = Integer.parseInt(currentPage);
		int intOnepagerow = Integer.parseInt(onepagerow);
		
		String dbtype = Utilities.getProperty("dbtype");
		
		int firstRecordIdx = 0;
		int lastRecordIndex = 0;
		if(dbtype.equals("oracle")) {
			firstRecordIdx = intCurrentPage > 1 ? ((intCurrentPage - 1) * intOnepagerow) + 1 : intCurrentPage;
			lastRecordIndex = firstRecordIdx + (intOnepagerow - 1);
		} else if(dbtype.equals("maria") || dbtype.equals("mysql")) {
			firstRecordIdx = intCurrentPage > 1 ? ((intCurrentPage - 1) * intOnepagerow) : intCurrentPage - 1;
			lastRecordIndex = firstRecordIdx + intOnepagerow;
			setIntOnepagerow(intOnepagerow);
		} else if(dbtype.equals("tibero")) {
			firstRecordIdx = intCurrentPage > 1 ? ((intCurrentPage - 1) * intOnepagerow) + 1 : intCurrentPage;
			lastRecordIndex = firstRecordIdx + (intOnepagerow - 1);
		}
		setStart(firstRecordIdx); 
		setEnd(lastRecordIndex);
	}

	public String getChgFlag() {
		return chgFlag;
	}
	public void setChgFlag(String chgFlag) {
		this.chgFlag = chgFlag;
	}

	public String getAdminAuth() {
		return adminAuth;
	}

	public void setAdminAuth(String adminAuth) {
		this.adminAuth = adminAuth;
	}

	public String getSelectParam() {
		return selectParam;
	}

	public void setSelectParam(String selectParam) {
		this.selectParam = selectParam;
	}

	public String getSelmode() {
		return selmode;
	}

	public void setSelmode(String selmode) {
		this.selmode = selmode;
	}

	public String getQryIdOrName() {
		return qryIdOrName;
	}

	public void setQryIdOrName(String qryIdOrName) {
		this.qryIdOrName = qryIdOrName;
	}

	public String getQryPopTitle() {
		return qryPopTitle;
	}

	public void setQryPopTitle(String qryPopTitle) {
		this.qryPopTitle = qryPopTitle;
	}

	public String getQrySearchYn() {
		return qrySearchYn;
	}

	public void setQrySearchYn(String qrySearchYn) {
		this.qrySearchYn = qrySearchYn;
	}

	public String getQryUserId() {
		return qryUserId;
	}

	public void setQryUserId(String qryUserId) {
		this.qryUserId = qryUserId;
	}

}
