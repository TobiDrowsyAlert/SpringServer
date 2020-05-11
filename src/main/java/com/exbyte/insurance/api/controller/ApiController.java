package com.exbyte.insurance.api.controller;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.api.domain.LogVO;
import com.exbyte.insurance.api.domain.ResponseDTO;
import com.exbyte.insurance.api.service.ApiService;
import com.exbyte.insurance.api.service.LogService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController {
	
	ApiService apiService;
	LogService logService;
	
    // 상태 코드 상수
    private final int INT_BLINK = 100;
    private final int INT_BLIND = 101;
    private final int INT_YAWN = 200;
    private final int INT_DRIVER_AWAY = 300;
    private final int INT_DRIVER_AWARE_FAIL = 301;
    private final int INT_NORMAL = 400;
	
	@Inject
	public ApiController(ApiService apiService, LogService logService) {
		this.apiService = apiService;
		this.logService = logService;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/value", method = RequestMethod.POST)
	@ResponseBody
	public Object ResponseAPIPost(@RequestBody String landmarks) {
		ResponseEntity<String> result = null;
		log.info("전달받은 결과 값 " + landmarks.toString());
	

		try {
			result = apiService.getItemsForOpenApi("regid", landmarks);
			String data = result.getBody();
			Gson gson = new GsonBuilder().create();
			
			ResponseDTO responseDTO = gson.fromJson(data, ResponseDTO.class);
			int currentStatus = responseDTO.getStatus_code();
			
			LogVO logVO = new LogVO(responseDTO, "admin");
			
			if(currentStatus != INT_NORMAL ) {
				// 데이터베이스 기록
				logService.create(logVO);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/drop", method = RequestMethod.POST)
	@ResponseBody
	public Object dropSleepStep() {
		ResponseEntity<String> result = null;
	

		try {
			result = apiService.dropSleepStep("regid", null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public Object resetSleepStep() {
		ResponseEntity<String> result = null;
	
		try {
		result = apiService.resetSleepStep("regid", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	@ResponseBody
	public Object feedbackSleepStep() {
		ResponseEntity<String> result = null;
	
		try {
		result = apiService.feedbackSleepStep("regid", null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
