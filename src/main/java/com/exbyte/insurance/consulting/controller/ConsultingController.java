package com.exbyte.insurance.consulting.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exbyte.insurance.commons.paging.Criteria;
import com.exbyte.insurance.commons.paging.PageMaker;
import com.exbyte.insurance.consulting.domain.ConsultingVO;
import com.exbyte.insurance.consulting.service.ConsultingService;

@Controller
@RequestMapping("/consulting")
public class ConsultingController {
	
	private static Logger logger = LoggerFactory.getLogger(ConsultingController.class);
	
	@Inject
	private ConsultingService consultingService;
	
	
	
	// 상담 리스트 페이지 이동
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGET(Model model, @ModelAttribute("criteria") Criteria criteria) throws Exception {

		List<ConsultingVO> consultings =  consultingService.selectAll();
		
		//파라미터인 criteria는 list에서 값 삽입, (form)
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalPageNum(consultingService.countAll());
		
		model.addAttribute("consultings", consultings);
		model.addAttribute("pageMaker", pageMaker);

		for(ConsultingVO consultingVO : consultings) {
			logger.info("consultings Status : " + consultingVO.toString());
		}
		logger.info("pageMaker Status : " + pageMaker.toString());
		
		return "/consulting/list";
	}
	
	// 상담 리스트 읽기 ( 값 수정을 위해서 들어감 )
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Model model, @RequestParam("consultingNo") int consultingNo) throws Exception {
		
		model.addAttribute("consulting", consultingService.read(consultingNo));
		
		return "/consulting/read";
	}
	
	
	
	
	
	
}
