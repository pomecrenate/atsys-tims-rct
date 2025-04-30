/*************************************************************
  프로그램명 : BaseBean.java
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;




public class BaseBean implements Serializable,IBaseBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6891597128825095014L;
	private String ticket;
	
	@Override
	public String getTicket() {
		return ticket;
	}

	@Override
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getSecureKey()
	{
		return getTicket();
	}
	public void setSecureKey(String secureKey)
	{
		setTicket(secureKey);
	}
	@Override
	public boolean canSave() {
		String sTicket = getServerTicket();
		if(sTicket== null || sTicket.length()==0)
			return false;
		return sTicket.equals(getTicket());
	}

	
	
	private HttpServletRequest getRequest()
	{
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	private HttpSession getSession()
	{
		HttpServletRequest request = getRequest();
		if(request == null)
			return null;
		return request.getSession();
	}
	private String getServerTicket()
	{
		HttpSession session = getSession();
		if(session == null)
			return null;
		return (String)session.getAttribute("secureKey");
	}

}