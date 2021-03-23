package com.example.pocwiremock.helper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateHelper {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();

    public <T> T get(Class<T> clazz, String url, Object... uriVariables){
       return restTemplate.getForObject(url, clazz, uriVariables);
    }

    public <T, R> T post(Class<T> clazz, String url, R body){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<T> response = restTemplate.postForEntity(url, request, clazz);
        return response.getBody();
    }

}
