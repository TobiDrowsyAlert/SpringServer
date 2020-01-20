package com.exbyte.insurance.commons.interception;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.service.AdminService;

public class CheckEmailInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(CheckEmailInterceptor.class);
	
	@Inject
	private AdminService adminService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		String adminId = request.getParameter("adminId");
		AdminVO adminVO = adminService.read(adminId);
		String authKey = adminService.checkAuthKey(adminVO.getAdminId());

		
		// 인증 키 값이 다르다면 거절
		if(!authKey.equals(request.getParameter("authKey"))) {
			logger.warn("AuthEmailInterceptor : 인증 키 거부");
			response.sendRedirect("/");
			return false;
		}
		
		logger.info("인증 키 인증 완료");
		adminVO.setAdminAuthKey("Y");
		adminService.updateAuthKey(adminVO);
		return true;
	}
	
	
}
