package com.exbyte.insurance.commons.interception;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.service.AdminService;

public class AuthEmailInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(AuthEmailInterceptor.class);
	
	@Inject
	private AdminService adminService;
	

	// 이메일 인증 키 검사
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("AuthEmailInterceptor Work...");
		
		if(adminService == null) {
			logger.info("adminService is Null");
		}
		
		String adminId = request.getParameter("adminId");
		logger.info("adminID : " + adminId);
		
		AdminVO adminVO = adminService.read(adminId);
		
		String authKey = adminService.checkAuthKey(adminVO.getAdminId());
		logger.info("adminVO : "+ adminVO.toString());
		logger.info("request authKey : "+ request.getParameter("authKey"));
		logger.info("adminVO authKey : "+ adminVO.getAdminAuthKey());
		
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
