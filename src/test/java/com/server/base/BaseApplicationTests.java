package com.server.base;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.repository.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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

        UserDto v = new UserDto();
//                String token = TokenManager.encrypt(v);
        String token = TokenManager.encrypt(v);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(null, httpHeaders);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Map> response = rt.exchange("http://localhost:8080/api/test/", HttpMethod.GET, entity, Map.class);
        System.out.println(response.getHeaders());
        System.out.println(">>>>>>>>>>");
        System.out.println(response.getBody());
        TokenManager.isExpired(response.getHeaders().get("Authorization").get(0));
    }

}
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZDIiOjEsImlkMSI6IjEifQ.V-ZLc4KmCSHkANCS6Q5GGPzBG6V5uv43Ev11WS6F40Gti9Zf02_3EpkjvVu0qGFZ4j3xcForvly2ogaAm-RhOA
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZDIiOjEsImlkMSI6IjEifQ.V-ZLc4KmCSHkANCS6Q5GGPzBG6V5uv43Ev11WS6F40Gti9Zf02_3EpkjvVu0qGFZ4j3xcForvly2ogaAm-RhOA
//eyJ0eXAiOiJhYyIsImFsZyI6IkhTNTEyIn0.eyJpZDIiOjEsImlkMSI6IjEifQ.-Y1C_kE7QVLqJ_N48TGWdqj36QcQ-SPaNCUM2Tkn1dJ155RFA3p-NFQjtiGqEY8yzGfe4OLMMsS-Kvd_51-Ufw