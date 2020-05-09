package com.exbyte.insurance.api.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.api.service.ApiService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController {
	
	ApiService apiService;
	
	@Inject
	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/value", method = RequestMethod.POST)
	@ResponseBody
	public Object ResponseAPIPost(@RequestBody String landmarks) {
		Object result = null;
		log.info("전달받은 결과 값 " + landmarks.toString());
	
		try {
		result = apiService.getItemsForOpenApi("regid", landmarks);
		log.info("data Test " + result.toString());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/drop", method = RequestMethod.POST)
	@ResponseBody
	public Object dropSleepStep() {
		Object result = null;
	
		try {
		result = apiService.dropSleepStep("regid", null);
		log.info("data Test " + result.toString());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public Object resetSleepStep() {
		Object result = null;
	
		try {
		result = apiService.resetSleepStep("regid", null);
		log.info("data Test " + result.toString());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	@ResponseBody
	public Object feedbackSleepStep() {
		Object result = null;
	
		try {
		result = apiService.feedbackSleepStep("regid", null);
		log.info("data Test " + result.toString());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
