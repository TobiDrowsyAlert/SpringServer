package com.exbyte.insurance.api.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.api.domain.LogVO;
import com.exbyte.insurance.api.domain.RequestFeedback;
import com.exbyte.insurance.api.domain.ResponseDTO;
import com.exbyte.insurance.api.service.ApiService;
import com.exbyte.insurance.api.service.LogService;
import com.exbyte.insurance.api.service.MinuteLogService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController {
	
	ApiService apiService;
	LogService logService;
	MinuteLogService minuteLogService;
	Gson gson;			
	SimpleDateFormat format;
	
    // 상태 코드 상수
    private final int INT_BLINK = 100;
    private final int INT_BLIND = 101;
    private final int INT_YAWN = 200;
    private final int INT_DRIVER_AWAY = 300;
    private final int INT_DRIVER_AWARE_FAIL = 301;
    private final int INT_NORMAL = 400;
	
	@Inject
	public ApiController(ApiService apiService, LogService logService, MinuteLogService minuteLogService) {
		this.apiService = apiService;
		this.logService = logService;
		this.minuteLogService = minuteLogService;
		gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
	
	// 데이터 전송
	@RequestMapping(value = "/value", method = RequestMethod.POST)
	@ResponseBody
	public Object ResponseAPIPost(@RequestBody String landmarks) {
		ResponseEntity<String> result = null;
		ResponseEntity<String> responseAndroid = null;
		Gson gson = new GsonBuilder().create();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		try {
			result = apiService.getItemsForOpenApi("regid", landmarks);
			String data = result.getBody();
			System.out.println("result.getBody() : " + data.toString());
			
			/*
			 * JsonReader reader = new JsonReader(new StringReader(result.getBody()));
			 * reader.setLenient(true);
			 */
			ResponseDTO responseDTO = gson.fromJson(data, ResponseDTO.class);
			System.out.println("responseDTO : " + responseDTO.toString());
			responseDTO.setCurTime(format.format(new Date()));
			String jsonDataWithTime = gson.toJson(responseDTO);
			System.out.println("time" + responseDTO.getCurTime());
			System.out.println("jsonDateWithTime : " + jsonDataWithTime);

			
			int currentStatus = responseDTO.getStatus_code();
			LogVO logVO = new LogVO(responseDTO, "admin");
			responseAndroid = new ResponseEntity<String>(jsonDataWithTime ,headers,result.getStatusCode());
			System.out.println("responseAndroid : " + responseAndroid.toString());
			
		if(currentStatus != INT_NORMAL ) {
			// 데이터베이스 기록
			logService.create(logVO);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return responseAndroid;
	}
	// 데이터 전송
	@RequestMapping(value = "/valueResult", method = RequestMethod.POST)
	@ResponseBody
	public Object ResponseAPIPostResult(@RequestBody String landmarks) {
		ResponseEntity<String> result = null;
		ResponseEntity<String> responseAndroid = null;
		
		try {
			result = apiService.getItemsForOpenApi("regid", landmarks);
			String data = result.getBody();
			System.out.println("result.getBody() : " + data.toString());
			/*
			 * JsonReader reader = new JsonReader(new StringReader(result.getBody()));
			 * reader.setLenient(true);
			 */
			ResponseDTO responseDTO = gson.fromJson(data, ResponseDTO.class);
			System.out.println("responseDTO : " + responseDTO.toString());
			responseDTO.setCurTime(format.format(new Date()));
			int currentStatus = responseDTO.getStatus_code();
			LogVO logVO = new LogVO(responseDTO, "admin");

		if(currentStatus != INT_NORMAL ) {
			// 데이터베이스 기록
			logService.create(logVO);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 데이터 전송
	@RequestMapping(value = "/drop", method = RequestMethod.POST)
	@ResponseBody
	public Object dropSleepStep(@RequestBody String jsonData) {
		ResponseEntity<String> result = null;
		try {
			result = apiService.dropSleepStep("regid", jsonData);
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
	public Object feedbackSleepStep(@RequestBody String json) {
		ResponseEntity<String> result = null;
		RequestFeedback requestFeedback = gson.fromJson(json, RequestFeedback.class);
		
		try {
		result = apiService.feedbackSleepStep();
		logService.updateFeedabck(requestFeedback);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

	// 데이터 전송
	@RequestMapping(value = "/timer", method = RequestMethod.POST)
	@ResponseBody
	public Object timer(@RequestBody String json) {
		ResponseEntity<String> result = null;
		ResponseEntity<String> responseAndroid = null;
		
		try {
			result = apiService.timer("regid", json);
			String data = result.getBody();
			ResponseDTO responseDTO = gson.fromJson(data, ResponseDTO.class);
		
			
			responseDTO.setCurTime(format.format(new Date()));
			String jsonDataWithTime = gson.toJson(responseDTO);
			
			System.out.println("Timer : " + responseDTO.getCurTime());
			
			int currentStatus = responseDTO.getStatus_code();
			LogVO logVO = new LogVO(responseDTO, "admin");
			responseAndroid = new ResponseEntity<String>(jsonDataWithTime ,result.getHeaders(),result.getStatusCode());
			

			// 데이터베이스 기록
			minuteLogService.create(logVO);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
