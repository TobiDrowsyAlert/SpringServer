package com.exbyte.insurance.user.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import com.exbyte.insurance.api.domain.RequestSleepAnalyze;
import com.exbyte.insurance.api.domain.ResponseDTO;
import com.exbyte.insurance.api.service.LogService;
import com.exbyte.insurance.user.service.PersonalService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.user.domain.UserVO;
import com.exbyte.insurance.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final PersonalService personalService;
	private final LogService logService;
	private Gson gson;
	
	@Inject
	public UserController(UserService userService, PersonalService personalService, LogService logService) {
		this.userService = userService;
		this.personalService = personalService;
		this.logService = logService;
		gson = new GsonBuilder().create();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody String requestLoginDTO) {

		double avgStage;
		ResponseEntity<String> response = null;
		UserVO userVO = gson.fromJson(requestLoginDTO, UserVO.class);
		try {
			response = userService.login(requestLoginDTO);



			//취약 시간 체크
			SimpleDateFormat hourFormat = new SimpleDateFormat ( "HH");
			String currentTime = hourFormat.format (System.currentTimeMillis());
			System.out.println("현재 시간 : " + currentTime);
			avgStage = logService.getCurrentStageAvg(userVO);
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setAvgStage(avgStage);
			String json = gson.toJson(responseDTO);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			response = new ResponseEntity<String>(json, headers, response.getStatusCode());
			personalService.update(userVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody UserVO userVO) {
		
		ResponseEntity<String> response = null;
		UserVO hashUser = null;
		try{
			response = userService.create(userVO);
			personalService.create(userVO);
		}catch(Exception e){
			e.printStackTrace();
		}

		System.out.println("userId" + userVO.getUserId());

		return response;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> logout(@RequestBody String requestLoginDTO){

		ResponseEntity<String> result = null;
		try {
			result = userService.logout(requestLoginDTO);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return result;
	}

	
}
