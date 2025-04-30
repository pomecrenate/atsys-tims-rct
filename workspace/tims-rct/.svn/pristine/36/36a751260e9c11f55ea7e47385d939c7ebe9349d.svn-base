/*************************************************************
  프로그램명 : TimsExceptionResolver.java
  설명 : error handler	
  작성자 : 김성태
  소속 : AT-SYS
  일자 : 2017.09.28
  프로그램설명
  **프로그램이력**
   수정일             작업근거                 유지보수담당
*************************************************************/
package com.atsys.nxf.core.web.servlet.handler.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.atsys.base.util.Utilities;
import com.atsys.nxf.core.web.servlet.handler.util.RequestUtils;

@Component
public class TimsExceptionResolver extends AbstractHandlerExceptionResolver {

	private Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			if(ex != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", true);
				String message = "";

				if(ex instanceof ServiceException) {
					message = ex.getMessage();
					if(message == null) {
						message = "";
					} else {
						LOGGER.info(message);
					}
				} else {
					String errorMsg = "에러 " + (ex.getMessage() == null ? "error" : ex.getMessage());
					LOGGER.error(errorMsg);
				}

				map.put("message", message);
				response.setStatus(500);
				if(RequestUtils.isAjaxRequest(request)) {
					return new ModelAndView(new MappingJackson2JsonView(), map);
				} else {
					String errorPage = "tims/common/error/error";
					int errorCode = Utilities.parseInt(request.getAttribute("errorCode"));
					switch (errorCode) {
						case 450:
						case 530:
						case 531:
						case 532:
						case 533:
						case 534:
						case 535:
						case 536:
							errorPage = "tims/common/error/" + errorCode;
							break;
						default:
							return new ModelAndView(errorPage, map);
					}
					return new ModelAndView(errorPage, map);
				}
			}
		} catch (NumberFormatException e) {
			LOGGER.info("info to NumberFormatException", e); 
		} catch (Exception e) {
			LOGGER.info("info to Exception", e); 
		}
		return new ModelAndView("tims/common/error/error", new HashMap<String, Object>());
	}

}
