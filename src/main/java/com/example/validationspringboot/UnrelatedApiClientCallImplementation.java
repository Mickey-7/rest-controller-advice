package com.example.validationspringboot;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UnrelatedApiClientCallImplementation {

    public static <T> ResponseEntity<T> makeMainframeCall(
            String url,
            HttpMethod httpMethod,
            HttpEntity<Object> requestEntity,
            Class<T> responseType
    ){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url,httpMethod,requestEntity,responseType);
    }

    public static <T> ResponseEntity<T> makeMainframeCallPost(
            String url,
            HttpMethod httpMethod,
            HttpEntity<Object> requestEntity,
            Class<T> responseType
    ){
        return makeMainframeCall(url,httpMethod,requestEntity,responseType);
    }
}
