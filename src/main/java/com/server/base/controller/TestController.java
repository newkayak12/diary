package com.server.base.controller;

import com.server.base.common.authorizations.Authorization;
import com.server.base.repository.dto.TestDto;
import com.server.base.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private TestService testService;
    @Authorization
    public void tst(@RequestHeader String token){
        System.out.println(token);
    };

}
