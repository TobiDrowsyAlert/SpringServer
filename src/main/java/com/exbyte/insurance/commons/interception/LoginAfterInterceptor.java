package com.exbyte.insurance.commons.interception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginAfterInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		
		// 로그인 후 회원가입-로그인 페이지 접근 시 
		if(session.getAttribute("login") != null) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		return true;	
	}
	
}
