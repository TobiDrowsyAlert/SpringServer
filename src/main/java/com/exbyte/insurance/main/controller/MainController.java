package com.exbyte.insurance.main.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exbyte.insurance.admin.controller.AdminController;

@Controller
@RequestMapping("/main")
public class MainController {
	
	
//	private final AdminService adminService;
//	private final AdminMailService adminMailService;
	final String STRING_SUCCESS = "success";
	final String STRING_FAIL = "fail";
	final String STRING_AUTH_FAIL = "auth_fail";
	final String STRING_NOT_HASH_PW = "not_hash_pw";
	final String TEST_VALID_EMAIL = "y";
	final String STRING_NULL = "null";

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	public MainController() {
		
	}


//	
//	@RequestMapping(value = "/register", method = RequestMethod.GET)
//	public void registerGET(Model model) throws Exception {
//		
//		List<PointVO> points = adminService.selectAllPoint();
//		model.addAttribute("points", points);
//		
//		return;
//	}
//	
//	@RequestMapping(value = "/registerPOST", method = RequestMethod.POST)
//	public String registerPOST(AdminVO adminVO, HttpServletRequest request ,RedirectAttributes redirectAttributes) throws Exception {
//		try {
//			AdminVO hashAdminVO = adminService.registerAccount(adminVO);
//			adminMailService.mailSend(adminVO, request.getContextPath());
//			redirectAttributes.addFlashAttribute("msg", STRING_SUCCESS);
//		}catch (NullPointerException e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("msg", STRING_NULL);
//		}catch (Exception e) {
//			redirectAttributes.addFlashAttribute("msg", STRING_FAIL);
//		}
//		
//		
//		return "redirect:/admin/login";
//	}
//	
//
//	// CheckEmailInterceptor : preHandler 호출 - 이메일 키 인증
//	@RequestMapping(value = "/confirm", method = RequestMethod.GET )
//	public String confirmEmail(AdminVO adminVO, String authKey, Model model) throws Exception {
//
//		model.addAttribute("msg", STRING_SUCCESS);
//		
//		return "/admin/login";
//	}
//	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public void loginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO, 
//			Model model,
//			@CookieValue(value="loginCookie", required=false) Cookie loginCookie) throws Exception {
//		
//		if(loginCookie != null) {
//			String savedAdminId = adminService.checkSession(loginCookie.getValue());
//			model.addAttribute("adminId", savedAdminId);
//		}
//
//		model.addAttribute("msg", STRING_SUCCESS);
//		return;
//	}
//	
//	// ProvideLoginSessionInterceptor : postHandler 호출 - 쿠키 생성
//	@RequestMapping(value = "/loginPOST", method = RequestMethod.POST)
//	public String loginPOST(LoginDTO loginDTO, Model model, HttpSession httpSession) {
//
//		AdminVO adminVO = null;
//		Logger logger = LoggerFactory.getLogger(AdminController.class);
//		logger.warn("loginDTO : " + loginDTO.toString());
//	
//		try {
//			adminVO = adminService.login(loginDTO);
//			
//			if(adminService.countSession(adminVO.getAdminId()) > 0) {
//				return "/admin/login";
//			}
//			
//			adminService.keepSession(adminVO.getAdminId() , httpSession.getId());
//		}
//		catch (IllegalArgumentException e) {
//			e.printStackTrace();
//			model.addAttribute("msg", STRING_NOT_HASH_PW);
//		  
//			return "/admin/login";
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg",STRING_FAIL);
//			return "/admin/login";
//		}
//		
//		model.addAttribute("msg", STRING_SUCCESS);
//		model.addAttribute("admin", adminVO);
//		
//		return "/commons/index";
//	}
//	
//	@RequestMapping(value = "/email", method = RequestMethod.GET)
//	public void emailSend(@RequestParam("adminEmail") String adminEmail) throws Exception {
//		return;
//	}
//	
//	
//	// 이메일 재송신
//	@RequestMapping(value = "/email", method = RequestMethod.POST)
//	public String emailResend(@RequestParam("adminEmail") String adminEmail, RedirectAttributes redirectAttribute,
//			HttpServletRequest request) throws Exception {
//		
//		AdminVO adminVO;
//		
//		try {
//			adminVO = adminService.selectAdminByEmail(adminEmail);
//			adminMailService.mailSend(adminVO, request.getContextPath());
//			redirectAttribute.addFlashAttribute("msg", STRING_SUCCESS);
//			return "redirect:/";
//		}catch (NullPointerException e) {
//			// TODO: handle exception
//			redirectAttribute.addFlashAttribute("msg", STRING_FAIL);
//			return "redirect:/";
//		}
//
//
//	}
//	
//	@RequestMapping(value = "/find", method = RequestMethod.GET)
//	public void findGET() {
//		return;
//	}
//	
//	@RequestMapping(value = "/findPOST", method = RequestMethod.POST)
//	public String findPOST(@RequestParam("adminEmail") String adminEmail, Model model ,HttpServletRequest request) throws Exception {
//		
//		AdminVO adminVO = adminService.selectAdminByEmail(adminEmail);
//		
//		model.addAttribute("adminEmail", adminEmail);
//		if(adminVO == null) {
//			model.addAttribute("msg", "FAIL");
//			return "/admin/find";
//		}
//		adminMailService.mailSend(adminVO, request.getContextPath());
//		
//		return "/admin/findResult";
//	}
//	
//	// 이메일을 통한 비밀번호 변경 페이지 이동
//	@RequestMapping(value = "/updatePw", method = RequestMethod.GET)
//	public void updatePwGET(@RequestParam("adminId") String adminId, @RequestParam("authKey") String authKey,  Model model) throws Exception {
//
//		model.addAttribute("adminId", adminId);
//		
//		return;
//	}
//	
//	// 이메일을 통한 비밀번호 변경 처리
//	@RequestMapping(value = "/updatePwPOST", method = RequestMethod.POST)
//	public String updatePwPOST(LoginDTO loginDTO, Model model) throws Exception {
//		AdminVO adminVO = new AdminVO();
//		adminVO.setAdminId(loginDTO.getAdminId());
//		adminVO.setAdminPw(loginDTO.getAdminPw());
//
//		// 암호화
//		String hashPw = BCrypt.hashpw(adminVO.getAdminPw(), BCrypt.gensalt());
//		adminVO.setAdminPw(hashPw);
//		
//		adminService.updatePw(adminVO);
//	
//		return "/admin/login";
//	}
//	
//	// 로그아웃 처리 구현
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logoutGET(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
//		throws Exception{
//		
//		Object object = httpSession.getAttribute("login");
//		if(object != null) {
//			httpSession.removeAttribute("login");
//			httpSession.invalidate();
//			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
//			if(loginCookie != null) {
//				loginCookie.setMaxAge(0);
//				response.addCookie(loginCookie);
//			}
//			// 세션 키 초기화, 
//			
//		}
//		return "/admin/logout";
//	}
//	
//	
//	// 지사 목록 전달
//	@RequestMapping(value = "/listPoint", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<List<PointVO>> listPoint() throws Exception {
//		ResponseEntity<List<PointVO>> entity = null;
//		
//		List<PointVO> list = adminService.selectAllPoint();
//		try {
//			for(PointVO pointVO : list) {
//				entity = new ResponseEntity<>(list, HttpStatus.OK);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		logger.info(entity.toString());
//		
//		return entity;
//	}
//	
//
//	
//	// 회원정보 전달
//	@RequestMapping(value = "/listAdmin", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<List<AdminVO>> listAdmin(@RequestParam(value = "pointNo") String pointNo) throws Exception {
//		int pointNoInt = Integer.parseInt(pointNo);
//		ResponseEntity<List<AdminVO>> entity = null;
//		// 이건 비즈니스 로직
//		List<AdminVO> list = adminService.selectAllAdmin();
//		List<AdminVO> listAdmin = new ArrayList<>();
//		for(AdminVO adminVO : list) {
//			if(adminVO.getAdminPoint() == pointNoInt) {
//				listAdmin.add(adminVO);
//			}
//		}
//
//		try {
//			entity = new ResponseEntity<>(listAdmin, HttpStatus.OK);
//		}catch (Exception e) {
//			e.printStackTrace();
//			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		
//		return entity;
//	}
//	
//	// 개인정보 페이지
//	@RequestMapping(value = "/account", method = RequestMethod.GET)
//	public void account(Model model, @RequestParam("adminId") String adminId) throws Exception {
//		
//		model.addAttribute("adminVO", adminService.read(adminId)); 
//		
//		return;
//	}
//	
//	// 회원탈퇴
//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public String deleteAdmin(@RequestParam(value="adminId") String adminId, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		adminService.delete(adminId);
//		HttpSession session = request.getSession();
//		
//		// 쿠키 및 세션 초기화 작업, 추후 Interception으로 일괄처리하도록 변경
//		Object object = session.getAttribute("login");
//		if(object != null) {
//			session.removeAttribute("login");
//			session.invalidate();
//			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
//			if(loginCookie != null) {
//				loginCookie.setMaxAge(0);
//				response.addCookie(loginCookie);
//			}
//		}
//		
//		return "/commons/index";
//	}
//	
//	// AJAX 값 반환용
//	// 아이디 중복 체크
//	// 수정 전 : @RequestParam("adminId") String adminId
//	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
//	@ResponseBody
//	public int checkIdPOST(@RequestBody(required = true) AdminVO adminVO) throws Exception{
//		
//		// 1: 중복 , 0: 중복아님
//		return adminService.countId(adminVO.getAdminId());
//		
//	}
//
//	
//
//	@RequestMapping(value = "/{adminId}/adminId", method = RequestMethod.GET)
//	@ResponseBody
//	public int countId(@PathVariable("adminId") String adminId) throws Exception{
//		
//		// 1: 중복 , 0: 중복아님
//		return adminService.countId(adminId);
//		
//	}
//	
//	// 이메일 중복 체크
//	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
//	@ResponseBody
//	public int checkEmailPOST(@RequestParam("adminEmail") String adminEmail) throws Exception{
//		
//		// 1: 중복, 0: 중복아님
//		return adminService.countEmail(adminEmail);
//	}
//
//	// 중복체크
//	// 위의 일일이 체크하는 값들을 하나의 메소드로 해결할 수 있도록 구성한 것이다.
//	@RequestMapping(value = "/duplicateCheck", method = RequestMethod.GET)
//	public int duplicateCheck(AdminVO adminVO, @RequestParam("checkType") String checkType) throws Exception {
//		
//		return adminService.count(adminVO, checkType);
//		
//	}
//	
//	// 회원 정보 변경
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String updateAdmin(AdminVO adminVO) throws Exception {
//		
//		AdminVO currentAdminVO = adminService.read(adminVO.getAdminId());
//		currentAdminVO.setAdminEmail(adminVO.getAdminEmail());
//		currentAdminVO.setAdminName(adminVO.getAdminName());
//		
//		adminService.update(currentAdminVO);
//		
//		return "/commons/index";
//	}
//	
	
}



