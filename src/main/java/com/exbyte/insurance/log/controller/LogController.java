package com.exbyte.insurance.log.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.log.domain.LogVO;
import com.exbyte.insurance.log.service.LogService;

@Controller
@RequestMapping(value = "/log")
public class LogController {

	LogService logService;
	
	@Inject
	LogController(LogService logService){
		this.logService = logService;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public String create(LogVO logVO){
		
		return "";
	}
	
	

}
