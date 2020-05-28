package com.exbyte.insurance.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	final String STRING_LOGIN_SUCCESS = "로그인 성공";
	final String STRING_EMAIL_SEND = "이메일로 인증메일을 전송했습니다. 확인해주세요.";
	final String STRING_FAIL = "작업이 실패했습니다.";
	final String STRING_AUTH_FAIL = "이메일 인증이 필요합니다.";
	final String STRING_AUTH_SUCCESS = "이메일 인증이 롼료되었습니다.";
	final String STRING_NOT_HASH_PW = "NOT_HASH_PW";
	final String STRING_INVAILD_PW = "INVAILD_PW";
	final String TEST_VALID_EMAIL = "Y";
	final String STRING_NULL = "NULL"; 

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	@Inject
	public AdminController(AdminService adminService, AdminMailService adminMailService) {
		this.adminMailService = adminMailService;
		this.adminService = adminService;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(Model model) throws Exception {
		return;
	}
	
	@RequestMapping(value = "/registerPOST", method = RequestMethod.POST)
	public String registerPOST(AdminVO adminVO, HttpServletRequest request ,RedirectAttributes redirectAttributes) throws Exception {
		try {
			AdminVO hashAdminVO = adminService.registerAccount(adminVO);
			adminMailService.mailSend(adminVO, adminMailService.urlMaker(request));
			redirectAttributes.addFlashAttribute("msg", STRING_EMAIL_SEND);
		}catch (NullPointerException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", STRING_NULL);
		}catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", STRING_FAIL);
			e.printStackTrace();
		}
		
		
		return "redirect:/admin/login";
	}
	

	// CheckEmailInterceptor : preHandler 호출 - 이메일 키 인증
	@RequestMapping(value = "/confirm", method = RequestMethod.GET )
	public String confirmEmail(AdminVO adminVO, String authKey, Model model) throws Exception {

		model.addAttribute("msg", STRING_AUTH_SUCCESS);
		
		return "/admin/login";
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public String emailSend(@RequestParam("adminEmail") String adminEmail, Model model) throws Exception {
		model.addAttribute("adminEmail", adminEmail);
		return "/admin/email";
	}
	
	
	// 이메일 재송신
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String emailResend(@RequestParam("adminEmail") String adminEmail, RedirectAttributes redirectAttribute,
			HttpServletRequest request) throws Exception {
		
		AdminVO adminVO;
		
		try {
			adminVO = adminService.selectAdminByEmail(adminEmail);
			adminMailService.mailSend(adminVO, adminMailService.urlMaker(request));
			redirectAttribute.addFlashAttribute("msg", STRING_EMAIL_SEND);
			return "redirect:/";
		}catch (NullPointerException e) {
			// TODO: handle exception
			redirectAttribute.addFlashAttribute("msg", STRING_FAIL);
			return "redirect:/";
		}


	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public void findGET() {
		return;
	}
	
	@RequestMapping(value = "/findPOST", method = RequestMethod.POST)
	public String findPOST(@RequestParam("adminEmail") String adminEmail, Model model ,HttpServletRequest request) throws Exception {
		
		AdminVO adminVO = adminService.selectAdminByEmail(adminEmail);
		
		model.addAttribute("adminEmail", adminEmail);
		if(adminVO == null) {
			model.addAttribute("msg", "FAIL");
			return "/admin/find";
		}
		adminMailService.mailSendFindPw(adminVO, adminMailService.urlMaker(request));
		
		return "/admin/findResult";
	}
	
	// 이메일을 통한 비밀번호 변경 페이지 이동
	@RequestMapping(value = "/updatePw", method = RequestMethod.GET)
	public String updatePwGET(@RequestParam("adminId") String adminId, @RequestParam("authKey") String authKey,  Model model) throws Exception {

		// 잘못된 인증키 
		if(!authKey.equals(adminService.read(adminId).getAdminAuthKey())) {
			return "/admin/login";
		}

		model.addAttribute("adminId", adminId);
		model.addAttribute("authKey", authKey);
		
		return "/admin/updatePw";
	}
	
	// 이메일을 통한 비밀번호 변경 처리
	@RequestMapping(value = "/updatePw", method = RequestMethod.POST)
	public String updatePwPOST(LoginDTO loginDTO, Model model) throws Exception {
		AdminVO adminVO = adminService.read(loginDTO.getAdminId());
		
		// 암호화
		String hashPw = BCrypt.hashpw(loginDTO.getAdminPw(), BCrypt.gensalt());
		adminVO.setAdminPw(hashPw);
		adminVO.setAdminAuthKey("Y");
		
		adminService.update(adminVO);
	
		return "/commons/index";
	}
	
	// 회원정보 전달
	@RequestMapping(value = "/listAdmin", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<AdminVO>> listAdmin(@RequestParam(value = "pointNo") String pointNo) throws Exception {
		int pointNoInt = Integer.parseInt(pointNo);
		ResponseEntity<List<AdminVO>> entity = null;
		// 이건 비즈니스 로직
		List<AdminVO> list = adminService.selectAllAdmin();
		List<AdminVO> listAdmin = new ArrayList<>();
		for(AdminVO adminVO : list) {
			if(adminVO.getAdminPoint() == pointNoInt) {
				listAdmin.add(adminVO);
			}
		}

		try {
			entity = new ResponseEntity<>(listAdmin, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 개인정보 페이지
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model, @RequestParam("adminId") String adminId) throws Exception {
		
		model.addAttribute("adminVO", adminService.read(adminId)); 
		
		return "/admin/account";
	}
	
	// 회원탈퇴
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteAdmin(@RequestParam(value="adminId") String adminId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		adminService.delete(adminService.read(adminId));
		HttpSession session = request.getSession();
		
		// 쿠키 및 세션 초기화 작업, 추후 Interception으로 일괄처리하도록 변경
		Object object = session.getAttribute("login");
		if(object != null) {
			session.removeAttribute("login");
			session.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
		}
		
		return "/commons/index";
	}
	
	// AJAX 값 반환용
	// 아이디 중복 체크
	// 수정 전 : @RequestParam("adminId") String adminId
	@RequestMapping(value = "/checkId", method = RequestMethod.GET)
	@ResponseBody
	public int checkIdPOST(@RequestParam("adminId") String adminId) throws Exception{
		
		// 1: 중복 , 0: 중복아님
		return adminService.countId(adminId);
		
	}

	
	// AJAX 값 반환용
	// 아이디 중복 체크
	// 수정 전 : @RequestParam("adminId") String adminId
	@RequestMapping(value = "/checkPosition", method = RequestMethod.GET)
	@ResponseBody
	public int checkAdminPosition(@RequestParam("adminPosition") String adminPosition) throws Exception{
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminPosition(adminPosition);
		
		// 1: 거부 , 0: 승인
		return adminService.countPosition(adminVO);
		
	}


	@RequestMapping(value = "/{adminId}/adminId", method = RequestMethod.GET)
	@ResponseBody
	public int countId(@PathVariable("adminId") String adminId) throws Exception{
		
		// 1: 중복 , 0: 중복아님
		return adminService.countId(adminId);
		
	}
	
	// 이메일 중복 체크
	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	@ResponseBody
	public int checkEmailPOST(@RequestParam("adminEmail") String adminEmail) throws Exception{
		
		// 1: 중복, 0: 중복아님
		return adminService.countEmail(adminEmail);
	}

	
	
	// 중복체크
	// 위의 일일이 체크하는 값들을 하나의 메소드로 해결할 수 있도록 구성한 것이다.
	@RequestMapping(value = "/duplicateCheck", method = RequestMethod.GET)
	public int duplicateCheck(AdminVO adminVO, @RequestParam("checkType") String checkType) throws Exception {
		
		return adminService.count(adminVO, checkType);
		
	}
	
	// 회원 정보 변경
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateAdmin(Model model, AdminVO adminVO) throws Exception {
		
		AdminVO currentAdminVO = adminService.read(adminVO.getAdminId());
		currentAdminVO.setAdminEmail(adminVO.getAdminEmail());
		currentAdminVO.setAdminName(adminVO.getAdminName());
		
		model.addAttribute("adminId", adminVO.getAdminId());
		
		adminService.update(currentAdminVO);
		
		return "/admin/account";
	}
	
	
}
