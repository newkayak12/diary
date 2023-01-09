package com.server.diary.baseTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.server.diary.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootTest
@ActiveProfiles(value = "local")
//@RunWith(SpringRunner.class)
//@AutoConfigureWebMvc
//@WebMvcTest
@Slf4j
public class BaseTest {
    ObjectMapper objectMapper;
    HttpHeaders httpHeaders;
    RestTemplate rt;
    String authorization =  Constants.TEST_ACCESS_TOKEN;
    String refresh = Constants.TEST_REFRESH_TOKEN;
    String prefix = "http://localhost:8081/api";


    public void setUp(){
        System.out.println("BEFOREEACH");
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.set(Constants.REFRESH_TOKEN, refresh);
//        httpHeaders.set(HttpHeaders.AUTHORIZATION, authorization);
        rt = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
    protected void get(String url, Object object){
        setUp();
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(prefix+url);

            Field[] fields = object.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                builder.queryParam(field.getName(), field.get(object));
            }
            String uri = builder.toUriString();
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(uri, HttpMethod.GET, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    protected void post(String url, Object object){
        setUp();
        try {
            System.out.println("POST");
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(prefix+url, HttpMethod.POST, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    protected void patch(String url, Object object){
        setUp();
        try {
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(prefix+url, HttpMethod.PATCH, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
        }
    }
    protected void delete(String url, Object object){
        setUp();
        try {
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(prefix+url, HttpMethod.DELETE, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
        }
    }
}
