package com.server.base;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.controller.TestController;
import com.server.base.repository.dto.TestDto;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureWebMvc
//@WebMvcTest
class BaseApplicationTests {
//    @Autowired
//    private MockMvc mvc;
//    @MockBean
//    private TokenManager tokenManager;

//    @BeforeEach
//    public void setup(){
//        MockRestServiceServer.createServer(new RestTemplate());
//        System.out.println("1");
//    }

    @Test
    void aopTest() throws Exception {

        TestDto v = TestDto.builder().id1("1").id2(1).id3(new Long(3)).id4(LocalDateTime.now().minusDays(1)).build();
        String token = TokenManager.encrypt(v);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(null, httpHeaders);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Map> response = rt.exchange("http://localhost:8080/api/test/", HttpMethod.GET, entity, Map.class);
        System.out.println(response.getHeaders());
        System.out.println(">>>>>>>>>>");
        System.out.println(response.getBody());
    }

}
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZDIiOjEsImlkMSI6IjEifQ.V-ZLc4KmCSHkANCS6Q5GGPzBG6V5uv43Ev11WS6F40Gti9Zf02_3EpkjvVu0qGFZ4j3xcForvly2ogaAm-RhOA