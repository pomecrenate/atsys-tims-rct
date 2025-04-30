package com.atsys.base.ui;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ViewHelper {
	
	public ModelAndView createModelAndView(String bodyPage) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("tims/common/layout/layout");  // 레이아웃 페이지
        mav.addObject("body", bodyPage);  // body를 동적으로 지정
        return mav;
    }
	
	public ModelAndView createModelAndViewPopup(String bodyPage) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("tims/common/layout/popupLayout");  // 레이아웃 페이지
        mav.addObject("body", bodyPage);  // body를 동적으로 지정
        System.out.println(mav);
        return mav;
    }
}
