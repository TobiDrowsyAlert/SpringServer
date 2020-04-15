package com.exbyte.insurance.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
	
	public Object getItemsForOpenApi(String regid, String time, String jsonData) throws UnsupportedEncodingException{
		String url = "http://15.165.116.82:1234/set_face";
		String serviceKey = "서비스키";
		String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		HashMap<String,Landmark> param = new HashMap<String, Landmark>();
		
		//String json = "data";
		Point point = new Point(10,10);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity = new HttpEntity<String>(jsonData,headers);
		
		
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		Object obj = parser.parse(jsonData);
		
		JSONParser simpleParser = new JSONParser();
		Object obj2 = new Object();
		try {
			obj2 = simpleParser.parse(jsonData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject obj3 = new JSONObject();
		obj3.put("name", "key");
		
		
		System.out.println("JsonData Value : " + obj2.toString());
		System.out.println("JsonData Value : " + obj3.toString());
		
		
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.build(false);
		
		Object response = restTemplate.postForObject(url, entity, String.class);
		
		
		response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity ,String.class);
		System.out.print("result " + response.toString());
		
		return response;
	}
	
}




