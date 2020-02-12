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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.exception.InvalidAuthKeyAccessException;
import com.exbyte.insurance.admin.service.AdminMailService;
import com.exbyte.insurance.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
	
	
	private final AdminService adminService;
	private final AdminMailService adminMailService;
	
	final String STRING_LOGIN_SUCCESS = "로그인 성공";
	final String STRING_EMAIL_SEND = "이메일로 인증메일을 전송했습니다. 확인해주세요.";
	final String STRING_FAIL = "작업이 실패했습니다.";
	final String STRING_AUTH_FAIL = "이메일 인증이 필요합니다.";
	final String STRING_AUTH_SUCCESS = "이메일 인증이 롼료되었습니다.";
	final String STRING_NOT_HASH_PW = "암호화되지 않은 계정, 비밀번호 변경이 필요합니다.";
	final String STRING_INVAILD_PW = "잘못된 비밀번호 입니다.";
	final String TEST_VALID_EMAIL = "Y";
	final String STRING_NULL = "NULL";

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	public AdminLoginController(AdminService adminService, AdminMailService adminMailService) {
		this.adminService = adminService;
		this.adminMailService = adminMailService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO, 
			Model model,
			@CookieValue(value="loginCookie", required=false) Cookie loginCookie) throws Exception {
		
		if(loginCookie != null) {
			String savedAdminId = adminService.checkSession(loginCookie.getValue());
			model.addAttribute("adminId", savedAdminId);
			
		}

		return "/admin/login";
	}
	
	// ProvideLoginSessionInterceptor : postHandler 호출 - 쿠키 생성
	@SuppressWarnings("unused")
	@RequestMapping(value = "/loginPOST", method = RequestMethod.POST)
	public String loginPOST(LoginDTO loginDTO, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession httpSession)
	throws Exception {

		AdminVO adminVO = null;
		Logger logger = LoggerFactory.getLogger(AdminController.class);
		logger.warn("loginDTO : " + loginDTO.toString());
	
		try {
			adminVO = adminService.login(loginDTO);
			logger.info("loginPost logger : " + adminVO.toString());
			
			if(adminService.countSession(adminVO.getAdminId()) > 0) {
				throw new Exception();
			}
			else if(adminVO == null) {
				throw new NullPointerException();
			}
			else if(adminVO.getAdminPoint() != loginDTO.getAdminPoint()) {
				throw new Exception();
			}
			
			adminService.keepSession(adminVO.getAdminId() , httpSession.getId());
		}
		// Auth Key 실패
		catch (InvalidAuthKeyAccessException e) {
			adminVO = adminService.read(loginDTO.getAdminId());
			redirectAttributes.addAttribute("adminEmail", adminVO.getAdminEmail());
			logger.info("adminEmail : " + adminVO.getAdminEmail());
			e.printStackTrace();
			return "redirect:/admin/email";
			
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", STRING_INVAILD_PW);
			return "redirect:/admin/login";
		}
		catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", STRING_FAIL);
			return "redirect:/admin/login";
		}

		model.addAttribute("msg", STRING_LOGIN_SUCCESS);
		model.addAttribute("admin", adminVO);
		
		return "/commons/index";
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
		return "redirect:/";
	}
	
	
	
}
