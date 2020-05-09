package com.exbyte.insurance.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.exbyte.insurance.api.domain.Landmark;

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
	
	class Point {
		int x;
		int y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		Point getPoint() {
			return this;
		}
	}
	
	public Object getItemsForOpenApi(String regid, String jsonData) throws UnsupportedEncodingException{
		String url = "http://15.165.116.82:1234/set_face";
		String serviceKey = "서비스키";
		String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		HashMap<String,Landmark> param = new HashMap<String, Landmark>();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// 전송을 위한 http 엔티티
		HttpEntity<String> entity = new HttpEntity<String>(jsonData,headers);
		
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.build(false);
		
		//postForObject 결과를 객체로, exchange 결과를 HTTPResponseEntitiy로 받는다 + Http Header 수정 가능 
		
		// 전송 방법 1.
		//Object response = restTemplate.postForObject(url, entity, String.class);
		
		// 전송 방법 2.
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity ,String.class);
		System.out.print("result " + response.toString());
		
		return response;
	}

	public Object dropSleepStep(String regid, String jsonData) throws UnsupportedEncodingException{
		String url = "http://15.165.116.82:1234/drop";
		String serviceKey = "서비스키";
		String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		HashMap<String,Landmark> param = new HashMap<String, Landmark>();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonData,headers);
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.build(false);
		
		//postForObject 결과를 객체로, exchange 결과를 HTTPResponseEntitiy로 받는다 + Http Header 수정 가능 

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity ,String.class);
		System.out.print("result " + response.toString());
		
		return response;
	}
	
	public Object resetSleepStep(String regid, String jsonData) throws UnsupportedEncodingException{
		String url = "http://15.165.116.82:1234/reset";
		String serviceKey = "서비스키";
		String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		HashMap<String,Landmark> param = new HashMap<String, Landmark>();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonData,headers);
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.build(false);
		
		//postForObject 결과를 객체로, exchange 결과를 HTTPResponseEntitiy로 받는다 + Http Header 수정 가능 

		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity ,String.class);
		System.out.print("result " + response.toString());
		
		return response;
	}
	
	public Object feedbackSleepStep(String regid, String jsonData) throws UnsupportedEncodingException{
		String url = "http://15.165.116.82:1234/feedback";
		String serviceKey = "서비스키";
		String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		HashMap<String,Landmark> param = new HashMap<String, Landmark>();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonData,headers);

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.build(false);
		
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity ,String.class);
		System.out.print("result " + response.toString());
		
		return response;
	}
}




