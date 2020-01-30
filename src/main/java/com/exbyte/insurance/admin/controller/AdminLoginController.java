package com.exbyte.insurance.admin.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	
	private final AdminService adminService;
	final String STRING_SUCCESS = "success";
	final String STRING_FAIL = "fail";
	final String STRING_AUTH_FAIL = "auth_fail";
	final String STRING_NOT_HASH_PW = "not_hash_pw";
	final String TEST_VALID_EMAIL = "Y";
	final String STRING_NULL = "null";

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	public AdminLoginController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO, 
			Model model,
			@CookieValue(value="loginCookie", required=false) Cookie loginCookie) throws Exception {
		
		if(loginCookie != null) {
			String savedAdminId = adminService.checkSession(loginCookie.getValue());
			model.addAttribute("adminId", savedAdminId);
		}

		return "/admin/login2";
	}
	
	// ProvideLoginSessionInterceptor : postHandler 호출 - 쿠키 생성
	@RequestMapping(value = "/loginPOST", method = RequestMethod.POST)
	public void loginPOST(LoginDTO loginDTO, Model model, HttpSession httpSession) {

		AdminVO adminVO = null;
		Logger logger = LoggerFactory.getLogger(AdminController.class);
		logger.warn("loginDTO : " + loginDTO.toString());
	
		try {
			adminVO = adminService.login(loginDTO);
			
			if(adminService.countSession(adminVO.getAdminId()) > 0) {
				throw new Exception();
			}
			
			adminService.keepSession(adminVO.getAdminId() , httpSession.getId());
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			model.addAttribute("msg", STRING_NOT_HASH_PW);
			return;
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", STRING_FAIL);
			return;
		}

		model.addAttribute("msg", STRING_SUCCESS);
		model.addAttribute("admin", adminVO);
	}
	
	// 로그아웃 처리 구현
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
		throws Exception{
		
		Object object = httpSession.getAttribute("login");
		if(object != null) {
			httpSession.removeAttribute("login");
			httpSession.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
			// 세션 키 초기화, 
			
		}
		return "/admin/logout";
	}
	
	
	
}
