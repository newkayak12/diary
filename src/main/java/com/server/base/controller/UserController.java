package com.server.base.controller;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.constants.Constants;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.responseContainer.EncryptResponse;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin(origins = "*", allowCredentials ="true")
@RequestMapping(value = "/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Authorization
    @GetMapping(value = "/signIn")
    public Response SignIn(@RequestHeader(value = HttpHeaders.AUTHORIZATION) Object token,
                           @ModelAttribute UserDto userDto,
                           HttpServletResponse response) throws ServiceException {
        UserDto result = userService.getUser(userDto);
        response.addHeader(Constants.REFRESH_TOKEN, TokenManager.refreshEncrypt(result.getUserNo()));
        return new EncryptResponse( response, token,null);
    };



}
