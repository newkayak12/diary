package com.server.base.controller;

import com.server.base.common.authorizations.Authorization;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.responseContainer.EncryptResponse;
import com.server.base.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api")
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private TestService testService;
    @Authorization
    @GetMapping(value = "/test")
    public Response tst(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object token, HttpServletResponse res){
        return new EncryptResponse( res, token,123123123);
    };

}
