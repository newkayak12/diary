package com.server.base.controller;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.authorizations.annotations.Authorization;
import com.server.base.common.constants.Constants;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import com.server.base.common.responseContainer.EncryptResponse;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation("로그인")
    @GetMapping(value = "/signIn")
    public Response signIn(@ModelAttribute UserDto userDto,
                           HttpServletResponse response) throws ServiceException {
        log.error("WORK!");
        UserDto result = userService.getUser(userDto);
        response.addHeader(Constants.REFRESH_TOKEN, result.getRefreshToken());
        return new EncryptResponse( response, result,null);
//        return new Response(200, "200", null);
    };

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public Response signOut(@RequestBody UserDto userDto, HttpServletResponse response) throws ServiceException{
        log.error("WORK!");
        UserDto result = userService.saveUser(userDto);
        response.addHeader(Constants.REFRESH_TOKEN, result.getRefreshToken());
        return new EncryptResponse(response, result, null);

    }


}
