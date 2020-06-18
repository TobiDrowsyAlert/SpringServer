package com.exbyte.insurance.user.controller;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import com.exbyte.insurance.user.service.PersonalService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
	private Gson gson;
	
	@Inject
	public UserController(UserService userService, PersonalService personalService) {
		this.userService = userService;
		this.personalService = personalService;
		gson = new GsonBuilder().create();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody String requestLoginDTO) {

		ResponseEntity<String> response = null;
		UserVO userVO = gson.fromJson(requestLoginDTO, UserVO.class);
		try {
			response = userService.login(requestLoginDTO);
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

	
}
