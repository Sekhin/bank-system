package com.javastart.commonservice.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestService {

    private final RestTemplate restTemplate;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> postForEntity(String json, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        ResponseEntity<String> responseEntity = null;
        if (json != null){
            responseEntity = restTemplate.postForEntity(url, entity, String.class);
        }
        return responseEntity;
    }

    public void put(String json, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        if (json != null) {
            restTemplate.put(url, entity);
        }
    }

    public ResponseEntity<String> getForEntity(String url) {
        return restTemplate.getForEntity(url, String.class);
    }

    public void delete(String url) {
        restTemplate.delete(url);
    }
}
