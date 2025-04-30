/*************************************************************
  프로그램명 : ResultMap.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.result;

import java.io.Serializable;
import java.util.HashMap;
import com.atsys.nxf.util.web.MsgUtil;


public class ResultMap extends HashMap<String, Object> implements Serializable
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9064857326517890928L;
	private String messageCode;
	private Object[] params;
	
	public ResultMap()
	{
		put("success", "ok");
	}

	public ResultMap(String messageCode)
	{
		this.params = null;
		this.messageCode = messageCode;

		put("message", MsgUtil.getMessage(this.messageCode, this.params));
		put("success", "ok");
	}
	
	public ResultMap(String messageCode, Object... params)
	{
		this.params = params;
		this.messageCode = messageCode;

		put("message", MsgUtil.getMessage(this.messageCode, this.params));
		put("success", "ok");
	}
}