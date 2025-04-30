/*************************************************************
  프로그램명 : BindingResultExceptionResolver.java
  설명 : 공지사항
  작성자 : 구탁수
  소속 : AT-SYS
  일자 : 2017.06.19
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
 '17.06.19            신규작성                 AT-SYS 구탁수 
*************************************************************/
package com.atsys.nxf.core.web.servlet.handler.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.atsys.nxf.core.validation.exception.NxFBindingResultException;
import com.atsys.nxf.core.validation.support.BindingResultHelper;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;

public class BindingResultExceptionResolver extends AbstractHandlerExceptionResolver {
	
	@Resource(name="bindingResultHelper")
	private BindingResultHelper helper;
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		if (!(ex instanceof NxFBindingResultException) || !RequestUtils.isAjaxRequest(request)) {
			return null;
		}
		
		NxFBindingResultException e = (NxFBindingResultException) ex;
		
		if(log.isDebugEnabled()) log.debug("resolve binding result as response text messages.");
		
		response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		
		return new ModelAndView(new MappingJackson2JsonView(), helper.buildValidationMessage(e.getBindingResult(), e.isGroupedField()));
	}
	
}
