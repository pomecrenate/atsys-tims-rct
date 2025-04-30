/*************************************************************
  프로그램명 : MailService.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.base.app.mail;

import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

//import com.atsys.cmm.user.model.TuUserVo;

/**
 * @Class Name : MailService.java
 * @Description : MailService class
 * @Modification Information
 *
 * @author frida
 * @since 2013. 5. 30.
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Component("mailService")
public class MailService {

	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name="mailSender")
	private MailSender mailSender;
	
	@Resource(name="outgoingMessage")
	private SimpleMailMessage message;

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private Locale locale = LocaleContextHolder.getLocale();

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void send(String[] to, String subject, String content) {
		message.setSubject(subject);
		message.setTo(to);
		message.setText(content);

		try {
			mailSender.send(message);
		} catch (MailException ex){
			logger.error("Fail to transfer mail.",ex);
		}catch (Exception e) {
			logger.error("Fail to transfer mail.",e);
		}		
	}
}
