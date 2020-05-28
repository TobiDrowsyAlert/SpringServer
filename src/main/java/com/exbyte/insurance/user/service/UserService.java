package com.exbyte.insurance.user.service;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import javax.inject.Inject;

import com.exbyte.insurance.user.domain.UserVO;
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
	
	public ResponseEntity<String> login(String requestLoginDTO) throws UnsupportedEncodingException{
		String url = "http://15.165.116.82:1234/login";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(requestLoginDTO,headers);

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.build(false);
		
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity ,String.class);
		System.out.print("result " + response.toString());
	
		
		
		return response;
	}

	public ResponseEntity<String> create(UserVO userVO) throws Exception {

		if(!isCorrectNamingRule(userVO)){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		String hashedPassword = BCrypt.hashpw(userVO.getUserPassword(), BCrypt.gensalt());
		userVO.setUserPassword(hashedPassword);
		userDAO.create(userVO);

		return null;
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
