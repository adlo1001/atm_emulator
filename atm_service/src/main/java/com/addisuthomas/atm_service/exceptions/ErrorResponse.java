package com.addisuthomas.atm_service.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@Controller("error")
public class ErrorResponse {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception ex) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		return mv;
	}

}
