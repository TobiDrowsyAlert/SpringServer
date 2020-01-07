package com.exbyte.insurance.admin.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	// 관리자 관리 게시판
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGET() throws Exception {
		return "/admin/list";
	}
	
	// 관리자 회원가입 페이지
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET() throws Exception {
		return "/admin/register";
	}
	
	// 관리자 회원가입
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(AdminVO adminVO, RedirectAttributes redirectAttributes) throws Exception {
		
		// 암호화
		String hashPw = BCrypt.hashpw(adminVO.getAdminPw(), BCrypt.gensalt());
		adminVO.setAdminPw(hashPw);
		adminService.create(adminVO);
		
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/admin/login";
	}
	
	// 관리자 로그인 페이지
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO, 
			Model model,
			HttpServletRequest request) throws Exception {
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); 
		// 쿠기가 존재한다면, 
		if(loginCookie != null) {
			// 중복되는 세션 값이 없다면,
			if(adminService.checkDuplicateSession(loginCookie.getValue()) == 1) {
				String adminId = adminService.checkSession(loginCookie.getValue());
				model.addAttribute("adminId", adminId);
			}
			else {
				// 중복된 세션 제거 처리 ( Later ) 
			}
		}
		
		return "/admin/login";
	}
	
	// 로그인 요청
	@RequestMapping(value = "/loginPOST", method = RequestMethod.POST)
	public void loginPOST(LoginDTO loginDTO, Model model, HttpSession httpSession) throws Exception {

		AdminVO adminVO = adminService.login(loginDTO);

		// 아이디나 비밀번호가 잘못된 경우
		if(adminVO == null || !BCrypt.checkpw(loginDTO.getAdminPw(), adminVO.getAdminPw())) {
			model.addAttribute("msg", "FAIL");
			return;
		}
		
		// 로그인 성공, loginInterceptor postHandler로 쿠키 생성
		
		adminService.keepSession(adminVO.getAdminId() , httpSession.getId());
		
		logger.info("userVO : " + loginDTO.getAdminId() + ", loginDTO : ", loginDTO.getAdminId());
		logger.info("checkpw Boolean : " + BCrypt.checkpw(loginDTO.getAdminPw(), adminVO.getAdminPw()));
		
		
		model.addAttribute("msg", "SUCCESS");
		model.addAttribute("admin", adminVO);
	}
	
	// 비밀번호 찾기
	@RequestMapping(value = "/findPOST", method = RequestMethod.GET)
	public void findPw() {
		
		AdminVO adminVO;
		
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
