package com.exbyte.insurance.commons.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class ConnectionRestTemplate {

    private static final String hostUrl = "http://15.165.116.82:1234";
    private static RestTemplate restTemplate;
    private static HttpHeaders headers;

    ConnectionRestTemplate(){
        restTemplate = new RestTemplate();
    }

    static public ResponseEntity<String> connect(String json, String url){
        String connectUrl = hostUrl + url;
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(connectUrl)
                .build(false);

        ResponseEntity<String> response =
                restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity ,String.class);

        return response;
    }

}
