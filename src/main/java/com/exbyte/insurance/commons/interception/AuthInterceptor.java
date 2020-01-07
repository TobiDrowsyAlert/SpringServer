package com.exbyte.insurance.commons.interception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	// 로그인 안 했으면 접근 거부
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession httpSession = request.getSession();
		
		// 로그인 X
		if(httpSession.getAttribute("login") == null) {
			logger.info("not logged");
			response.sendRedirect("/admin/login");
			return false;
		}
		
		return true;
	}
	
	
}
