package com.exbyte.insurance.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiService {
	
	//private final RestTemplate restTemplate;
	
	
	public Object getItemsForOpenApi(String regid, String time) throws UnsupportedEncodingException{
		String url = "http://127.0.0.1:5000/api";
		String serviceKey = "서비스키";
		String decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		
		String json = "data";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("json", json)
				.build(false);
		
		Object response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<String>(headers),String.class);
		return response;
	}
}
