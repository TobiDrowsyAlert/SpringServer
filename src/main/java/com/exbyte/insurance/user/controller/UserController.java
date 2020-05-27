package com.exbyte.insurance.user.controller;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

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
	
	@Inject
	public UserController(UserService userService){
		this.userService = userService;
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestBody String requestLoginDTO) {
		
		ResponseEntity<String> response = null;
		
		try {
			response = userService.login(requestLoginDTO);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> register(@RequestBody UserVO userVO){
		
		ResponseEntity<String> response = null;
		
		
		return response;
		
	}
	
	
}
