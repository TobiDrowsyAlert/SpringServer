package com.exbyte.insurance.api.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import com.exbyte.insurance.commons.utils.ConnectionRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiService {
	
	//private final RestTemplate restTemplate;
	
	private final int INT_BLINK = 100;
	private final int INT_BLIND = 101;
	private final int INT_YAWN = 200;
	private final int INT_DRIVER_AWAY = 300;
	private final int INT_DRIVER_AWARE_FAIL = 301;
	private final int C_NORMAL = 400;


	public ResponseEntity<String> transferLandmark(String jsonData) throws UnsupportedEncodingException{
		String url = "/set_face";
		return ConnectionRestTemplate.connect(jsonData,url);
	}

	public ResponseEntity<String> dropSleepStep(String jsonData) throws UnsupportedEncodingException{
		String url = "/drop";
		return ConnectionRestTemplate.connect(jsonData, url);
	}
	
	public ResponseEntity<String> resetSleepStep(String jsonData) throws UnsupportedEncodingException{
		String url = "/reset";
		return ConnectionRestTemplate.connect(jsonData, url);
	}
	
	public ResponseEntity<String> feedbackSleepStep(String jsonData) throws UnsupportedEncodingException{
		String url = "/feedback";
		return ConnectionRestTemplate.connect(jsonData, url);
	}
	
	public ResponseEntity<String> timer(String jsonData) throws UnsupportedEncodingException{
		String url = "/timer";
		return ConnectionRestTemplate.connect(jsonData, url);
	}

}




