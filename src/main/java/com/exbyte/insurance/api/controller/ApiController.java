package com.exbyte.insurance.api.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.api.domain.Point;
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
	
	@RequestMapping(value = "/value", method = RequestMethod.GET)
	@ResponseBody
	public List<Point> ResponseAPI(@RequestBody List<Point> points) {
		
		log.info("전달받은 결과 값 " + points.toString());
		
		return points;
	}
	
	@RequestMapping(value = "/value", method = RequestMethod.POST)
	@ResponseBody
	public List<Point> ResponseAPIPost(@RequestBody List<Point> points) {
		
		log.info("전달받은 결과 값 " + points.toString());
	
		try {
		Object result = apiService.getItemsForOpenApi("50","check");
		log.info("data Test " + result.toString());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return points;
	}
	
	
}
