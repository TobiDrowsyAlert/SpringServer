package com.exbyte.insurance.api.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import com.exbyte.insurance.user.domain.UserVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
		ResponseEntity<String> response = null;
		Gson gson = new GsonBuilder().create();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		UserVO userVO = gson.fromJson(landmarks, UserVO.class);
		
		try {
			result = apiService.transferLandmark(landmarks);
			
			//json >> object
			String data = result.getBody();

			ResponseDTO responseDTO = gson.fromJson(data, ResponseDTO.class);
			responseDTO.setCurTime(format.format(new Date()));
			int currentStatus = responseDTO.getStatus_code();
			LogVO logVO = new LogVO(responseDTO, userVO.getUserId());
			
		if(currentStatus != INT_NORMAL ) {
			// 데이터베이스 기록
			responseDTO.setLogNo(logService.create(logVO).getLogNo());
			String jsonDataWithTime = gson.toJson(responseDTO);

				response = new ResponseEntity<String>(jsonDataWithTime ,headers, result.getStatusCode());
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

		System.out.println("responseAndroid : " + response.toString());
		
		return response;
	}

	@RequestMapping(value = "/drop", method = RequestMethod.POST)
	@ResponseBody
	public Object dropSleepStep(@RequestBody String jsonData) {
		ResponseEntity<String> result = null;
		try {
			result = apiService.dropSleepStep(jsonData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	@ResponseBody
	public Object resetSleepStep(@RequestBody String json) {
		ResponseEntity<String> result = null;
		try {
			result = apiService.resetSleepStep(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	@ResponseBody
	public Object feedbackSleepStep(@RequestBody String json) {
		ResponseEntity<String> result = null;
		RequestFeedback requestFeedback = gson.fromJson(json, RequestFeedback.class);

		try {
			logService.updateFeedabck(requestFeedback);

			if(!requestFeedback.getIsCorrect()){
				result = apiService.feedbackSleepStep(json);
			}
			else{
				result = new ResponseEntity<>(HttpStatus.OK);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}


		
		return result;
	}

	@RequestMapping(value = "/timer", method = RequestMethod.POST)
	@ResponseBody
	public Object timer(@RequestBody String json) {
		ResponseEntity<String> result = null;
		ResponseEntity<String> responseAndroid = null;
		Gson gson = new GsonBuilder().create();
		UserVO userVO = gson.fromJson(json, UserVO.class);
		
		try {
			result = apiService.timer(json);
			String data = result.getBody();
			ResponseDTO responseDTO = gson.fromJson(data, ResponseDTO.class);
		
			
			responseDTO.setCurTime(format.format(new Date()));
			String jsonDataWithTime = gson.toJson(responseDTO);
			
			System.out.println("Timer : " + responseDTO.getCurTime());

			LogVO logVO = new LogVO(responseDTO, userVO.getUserId());

			// 데이터베이스 기록
			minuteLogService.create(logVO);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public String chart(@RequestParam String userId, Model model){

		//UserVO userVO = gson.fromJson(json, UserVO.class);
		UserVO userVO = new UserVO();
		userVO.setUserId(userId);

		if(userVO != null)
			System.out.println(userVO.getUserId());
		try{
			model.addAttribute("logArray",logService.countDrowsy(userVO));
			model.addAttribute("successRate", logService.calculateSuccessRate(userVO));
		}catch (Exception e){
			e.printStackTrace();
		}

		return "/user/chartjs";
	}
	
}
