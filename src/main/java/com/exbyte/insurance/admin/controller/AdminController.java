package com.exbyte.insurance.admin.controller;

import javax.inject.Inject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.LoginDTO;
import com.exbyte.insurance.admin.service.AdminMailService;
import com.exbyte.insurance.admin.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	private final AdminService adminService;
	private final AdminMailService adminMailService;
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Inject
	public AdminController(AdminService adminService, AdminMailService adminMailService) {
		this.adminMailService = adminMailService;
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
	public String registerPOST(AdminVO adminVO, HttpServletRequest request ,RedirectAttributes redirectAttributes) throws Exception {
		
		// 암호화
		String hashPw = BCrypt.hashpw(adminVO.getAdminPw(), BCrypt.gensalt());
		adminVO.setAdminPw(hashPw);
		// DB 아이디 등록
		adminService.create(adminVO);
		
		// 인증 메일 보내기,
		adminMailService.mailSendWithUserKey(adminVO, request);
		redirectAttributes.addFlashAttribute("msg", "REGISTERED");
		
		return "redirect:/admin/login";
	}
	
	// 아이디 중복 체크
	@RequestMapping(value = "/checkId", method = RequestMethod.GET)
	@ResponseBody
	public int checkIdPOST(@RequestParam("adminId") String adminId) throws Exception{
		
		// 1: 중복 , 0: 중복아님
		return adminService.checkOverId(adminId);
		
	}
	
	
	// 이메일 중복 체크
	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	@ResponseBody
	public int checkEmailPOST(@RequestParam("adminEmail") String adminEmail) throws Exception{
		
		// 1: 중복, 0: 중복아님
		return adminService.checkOverEmail(adminEmail);
	}
	
	// 이메일 인증
	@RequestMapping(value = "/auth", method = RequestMethod.GET )
	public String authEmailGET(AdminVO adminVO, String authKey, Model model) throws Exception{
		String receiveAuthKey = adminService.checkAuthKey(adminVO.getAdminId());
		// 성공
		if(receiveAuthKey.equals(authKey)) {
			adminVO.setAdminAuthKey("Y");
			adminService.updateAuthKey(adminVO);
		}
		model.addAttribute("msg", "AUTHSUCCESS");
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
	public String loginPOST(LoginDTO loginDTO, Model model, HttpSession httpSession) throws Exception {

		AdminVO adminVO = adminService.login(loginDTO);

		// 아이디나 비밀번호가 잘못된 경우
		if(adminVO == null || !BCrypt.checkpw(loginDTO.getAdminPw(), adminVO.getAdminPw())) {
			model.addAttribute("msg", "FAIL");
			return "/admin/login";
		}
		// 이메일 인증이 안된 경우
		else if(!adminVO.getAdminAuthKey().contentEquals("Y")) {
			model.addAttribute("adminEmail", adminVO.getAdminEmail());
			return "/admin/emailSend";
		}
		
		// 로그인 성공, loginInterceptor postHandler로 쿠키 생성
		
		adminService.keepSession(adminVO.getAdminId() , httpSession.getId());
		
		logger.info("userVO : " + loginDTO.getAdminId() + ", loginDTO : ", loginDTO.getAdminId());
		logger.info("checkpw Boolean : " + BCrypt.checkpw(loginDTO.getAdminPw(), adminVO.getAdminPw()));
		
		model.addAttribute("msg", "SUCCESS");
		model.addAttribute("admin", adminVO);
		
		return "/commons/index";
	}
	
	// 이메일 전송 확인 페이지 이동
	@RequestMapping(value = "/emailSend", method = RequestMethod.GET)
	public String emailSend(Model model,@RequestParam("adminEmail") String adminEmail) throws Exception {
		return "/admin/emailSend";
	}
	
	// 이메일 재전송
	@RequestMapping(value = "/emailSend", method = RequestMethod.POST)
	public String emailSend(@RequestParam("adminEmail") String adminEmail, HttpServletRequest request) throws Exception {
		
		AdminVO adminVO = adminService.findAccountById(adminEmail);
		
		if(adminVO != null) {
			// 이메일 재송신
			adminMailService.mailSendWithUserKey(adminVO, request);
			return "/";
		}
		
		return "/";
	}
	
	// 계정찾기 페이지 이동
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String findGET() {
		return "/admin/find";
	}
	
	// 계정 찾기 신청
	@RequestMapping(value = "/findPOST", method = RequestMethod.POST)
	public String findPOST(@RequestParam("adminEmail") String adminEmail, Model model ,HttpServletRequest request) throws Exception {
		AdminVO adminVO = adminService.findAccountById(adminEmail);
		model.addAttribute("adminEmail", adminEmail);

		adminMailService.mailSendWithAccount(adminVO, request);
		
		return "/admin/findResult";
	}
	
	// 이메일을 통한 비밀번호 변경 페이지 이동
	@RequestMapping(value = "/updatePw", method = RequestMethod.GET)
	public String updatePwGET(@RequestParam("adminId") String adminId, @RequestParam("authKey") String authKey,  Model model) throws Exception {

		model.addAttribute("adminId", adminId);
		
		
		return "/admin/updatePw";
	}
	
	// 이메일을 통한 비밀번호 변경 처리
	@RequestMapping(value = "/updatePwPOST", method = RequestMethod.POST)
	public String updatePwPOST(LoginDTO loginDTO, Model model) throws Exception {
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminId(loginDTO.getAdminId());
		adminVO.setAdminPw(loginDTO.getAdminPw());
		
		logger.info(loginDTO.toString());

		// 암호화
		String hashPw = BCrypt.hashpw(adminVO.getAdminPw(), BCrypt.gensalt());
		adminVO.setAdminPw(hashPw);
		
		adminService.updatePw(adminVO);
	
		return "/admin/login";
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
