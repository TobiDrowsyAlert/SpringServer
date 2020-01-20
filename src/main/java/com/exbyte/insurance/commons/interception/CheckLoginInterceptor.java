package com.exbyte.insurance.commons.interception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckLoginInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(CheckLoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();
		
		if(httpSession.getAttribute("login") == null) {
			logger.info("not logged");
			response.sendRedirect("/admin/login");
			return false;
		}
		

		return true;
	}
	
	
}
