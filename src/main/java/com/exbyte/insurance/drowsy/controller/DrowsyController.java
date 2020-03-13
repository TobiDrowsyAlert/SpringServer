package com.exbyte.insurance.drowsy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.drowsy.domain.PersonalVO;
import com.exbyte.insurance.drowsy.service.DrowsyService;

@Controller
@RequestMapping(value = "/drowsy")
public class DrowsyController {

	DrowsyService drowsyService;
	
	DrowsyController(DrowsyService drowsyService){
		this.drowsyService = drowsyService;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String create(PersonalVO personalVO){
		
		drowsyService.create(personalVO);
		
		return "";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	@ResponseBody
	public String read(String userId) {
		
		drowsyService.read(userId);
		
		return "";
	}
	
}
