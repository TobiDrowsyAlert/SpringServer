package com.exbyte.insurance.consulting.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.commons.paging.PageMaker;
import com.exbyte.insurance.consulting.domain.ConsultingModifyDTO;
import com.exbyte.insurance.consulting.domain.ConsultingVO;
import com.exbyte.insurance.consulting.service.ConsultingService;

@Controller
@RequestMapping("/consulting")
public class ConsultingController {
	
	private static Logger logger = LoggerFactory.getLogger(ConsultingController.class);
	
	private String STRING_SUCCESS = "SUCCESS";
	private String STRING_FAIL = "FAIL";
	
	@Inject
	private ConsultingService consultingService;
	

	@RequestMapping(value="/create/out", method = RequestMethod.GET)
	@ResponseBody
	public int createOutGET(ConsultingVO consulting) throws Exception {
		
		logger.info("create Request : " + consulting.toString());
		try {
			consulting.setConsultingRegion("경기도");
			consulting.setConsultingRemarks("");
			consultingService.create(consulting);
		}catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
		
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createPOST(ConsultingVO consulting, RedirectAttributes redirectAttributes) throws Exception {
		
		logger.info("create Request : " + consulting.toString());
		try {
			consulting.setConsultingRegion("경기도");
			consulting.setConsultingRemarks("");
			consultingService.create(consulting);
			redirectAttributes.addFlashAttribute("msg", STRING_SUCCESS);
		}catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("msg", STRING_FAIL);
			return "redirect:/";
		}
		return "redirect:/";
	}
	
	// 상담 리스트 페이지 이동
	// Admin에 대한 처리도 필요해졌다.. 객체지향 관점에서 틀린 부분 리팩토링 필요 // 여기서 read하는게 아니면 된다.
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGET(Model model, HttpServletRequest request, @ModelAttribute("criteria") Criteria criteria) throws Exception {

		HttpSession httpSession = request.getSession();
		
		AdminVO admin = (AdminVO) httpSession.getAttribute("login");
		List<ConsultingVO> consultings = null;
		logger.info(admin.toString());
		
		consultings = consultingService.selectAll(criteria, admin);
		
		//파라미터인 criteria는 list에서 값 삽입, (form)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalPageNum(consultingService.countAll(criteria, admin));
		
		model.addAttribute("consultings", consultings);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute(criteria);
		
		
		for(ConsultingVO consultingVO : consultings) {
			logger.info("consultings Status : " + consultingVO.toString());
		}
		logger.info("pageMaker Status : " + pageMaker.toString());
		
		return "/consulting/list2";
	}
	
	// 상담 리스트 읽기 ( 값 수정을 위해서 들어감 )
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Model model, @RequestParam("consultingNo") int consultingNo) throws Exception {
		
		model.addAttribute("consulting", consultingService.read(consultingNo));
		
		return "/consulting/read";
	}
	
	// 수정 페이지 이동
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model, int consultingNo) throws Exception {
		
		model.addAttribute("consulting", consultingService.read(consultingNo));
		
		return "/cosulting/modify";
	}
	
	// 상담 리스트 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(@ModelAttribute("criteria") Criteria criteria, @RequestParam(value="chkbox[]") List<String> arr,
			RedirectAttributes redirectAttributes) throws Exception {
		
		int consultingNo;
		
		redirectAttributes.addAttribute("page", criteria.getPage());
		redirectAttributes.addAttribute("perPageNum", criteria.getPerPageNum());
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
		
		// return 0;
		
		for(String i : arr) {
			consultingNo = Integer.parseInt(i);
			consultingService.delete(consultingNo);
		}
		
		// AJAX로 호출해서, return은 동작 안한다.
		return "redirect:/consulting/list";
	}
	
	// 상담 리스트 1차 콜 // 최종 확인
	@RequestMapping(value = "/updateCheck", method = RequestMethod.POST)
	@ResponseBody
	public int updateCheck(Model model, @RequestParam(value="chkbox[]") List<String> arr,
			ConsultingVO consulting) throws Exception {
		
		int consultingNo = 0;
		
		// return 0;
		
		for(String i : arr) {
			consultingNo = Integer.parseInt(i);
			consultingService.delete(consultingNo);
		}
		
		return 1;
	}
	

	@RequestMapping(value = "/sort", method = RequestMethod.GET)
	@ResponseBody
	public List<ConsultingVO> sortGET(List<ConsultingVO> currentList) {
		
		
		return currentList;
	}
	
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.GET)
	@ResponseBody
	public int updateCousltingAdmin(Model model, @RequestParam(value="chkbox[]") List<String> arr,
			@RequestParam(value="adminId") String adminId) {
		if(adminId.equals("")) {
			return 0;
		}
		
		try {	
			int consultingNo;
			
			for(String i : arr) {
				consultingNo = Integer.parseInt(i);
				ConsultingVO consultingVO = consultingService.read(consultingNo);
				consultingVO.setAdminId(adminId);
				consultingService.update(consultingVO);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public int updateGET(Model model, @RequestParam(value="arr") ConsultingModifyDTO arr) {
		logger.info("updateGET 확인");
		
		try {	
			int consultingNo;
			
			logger.info(arr.toString());
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public int updatePOST(Model model, @RequestBody List<ConsultingModifyDTO> modifyArray) {
		
		try {	
			int consultingNo;
			
			for(ConsultingModifyDTO modifyDTO : modifyArray) {
				consultingNo = modifyDTO.getConsultingNo();
				ConsultingVO consultingVO = consultingService.read(consultingNo);
				
				consultingVO.setConsultingIsCall(modifyDTO.getConsultingIsCall());
				consultingVO.setConsultingIsEnd(modifyDTO.getConsultingIsEnd());
				consultingVO.setConsultingRemarks(modifyDTO.getConsultingRemarks());
				consultingService.update(consultingVO);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}
