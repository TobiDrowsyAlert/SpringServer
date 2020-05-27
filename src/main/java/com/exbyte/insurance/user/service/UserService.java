package com.exbyte.insurance.user.service;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
}
