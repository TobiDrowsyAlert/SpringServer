package com.exbyte.insurance.user.service;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import javax.inject.Inject;

import com.exbyte.insurance.admin.exception.InvalidAuthKeyAccessException;
import com.exbyte.insurance.commons.utils.ConnectionRestTemplate;
import com.exbyte.insurance.user.dao.PersonalDAO;
import com.exbyte.insurance.user.domain.UserVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.exbyte.insurance.user.dao.UserDAO;

@Service
public class UserService {

	UserDAO userDAO;
	
	@Inject
	UserService(UserDAO userDAO){
		this.userDAO = userDAO;
	}
	
	public ResponseEntity<String> login(String requestLoginDTO) throws Exception{
		String url = "/login";
		Gson gson = new GsonBuilder().create();
		UserVO userVO = gson.fromJson(requestLoginDTO, UserVO.class);

		UserVO savedUser = userDAO.select(userVO);

		if(savedUser == null)
			throw new Exception();


		String loginPw = userVO.getUserPassword();
		String databasePw = savedUser.getUserPassword();


		if(!BCrypt.checkpw(loginPw, databasePw)) {
			throw new Exception();
		}


		return ConnectionRestTemplate.connect(requestLoginDTO, url);
	}
	public ResponseEntity<String> logout(String requestLogoutDTO) throws Exception{
		String url = "/logout";
		return ConnectionRestTemplate.connect(requestLogoutDTO, url);
	}

	public ResponseEntity<String> create(UserVO userVO) throws Exception {

		if(!isCorrectNamingRule(userVO)){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		String hashedPassword = BCrypt.hashpw(userVO.getUserPassword(), BCrypt.gensalt());
		userVO.setUserPassword(hashedPassword);
		userDAO.create(userVO);

		return new ResponseEntity<String>("{ \"msg\": \"success\"",HttpStatus.OK);
	}

	public Boolean isCorrectNamingRule(UserVO userVO){
		if(!Pattern.matches("^[a-zA-Z0-9]{6,18}$", userVO.getUserId())) {
			System.out.println("아이디 규칙이 틀렸습니다.");
			return false;
		}

		if(!Pattern.matches("^[a-zA-Z0-9~`!@#$%&*()-]{6,18}$", userVO.getUserPassword())) {
			System.out.println("비밀번호 규칙이 틀렸습니다.");
			return false;
		}

		return true;
	}

}
