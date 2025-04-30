/*************************************************************
  프로그램명 : OrderSo.java
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


import org.egovframe.rte.fdl.string.EgovStringUtil;
import org.springframework.util.StringUtils;

import com.atsys.nxf.app.vo.BaseBean;

/**
 * @Class Name : OrderSo.java
 * @Description : OrderSo class
 * @Modification Information
 *
 * @author frida
 * @since 2014. 4. 29.
 * @version 1.0
 * @see
 *  
 *  Copyright (C) at-sys 2013. All right reserved.
 */

public class OrderSo extends BaseBean {
	
	private static final long serialVersionUID = -1171546767539145519L;
    private String column;
    private String order;
	
	private static final String DFT_COLUMN = "updDtm";
	private static final String ORD_ASC = "asc";
	private static final String ORD_DESC = "desc";

	public OrderSo(){
		this( getDefaultOrder());
	}
	public OrderSo( String ordstr){
		this( StringUtils.hasText(ordstr) ? ordstr.split(" ") : getDefaultOrder());
	}
	private OrderSo(String[] ord){
		String[] ord2 = ord;
		if(ord2 == null) {
			ord2 = getDefaultOrder();
		}
		if(ord2.length > 0 && StringUtils.hasText(ord2[0])) {
			this.column = trimWord(ord2[0].replace(" ", ""), DFT_COLUMN);
			if(ord2.length > 1) {
				this.order = trimWord(ord2[1].replace(" ", ""), ORD_ASC);
			}
		}
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = trimWord(column.replace(" ", ""), DFT_COLUMN).toUpperCase();
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = trimWord(order.replace(" ", ""), ORD_ASC).toUpperCase();
	}
	
	private static String trimWord( String text, String dft){
		String text2 = text;
		if( !StringUtils.hasText(text2)) 
			text2 = dft;
		
		return EgovStringUtil.convertToUnderScore(text2.trim()).replaceAll("\\W", "");
	}
	
	private static String[] getDefaultOrder(){
		return new String[]{DFT_COLUMN, ORD_DESC};
	}

}
