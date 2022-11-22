package com.server.base.controller;

import com.server.base.common.constants.Constants;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.EncryptResponse;
import com.server.base.common.responseContainer.Response;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Tag(name = "/api/user", description = "회원")
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "로그인", httpMethod = "GET")
    @GetMapping(value = "/signIn")
    public Response signIn(@ModelAttribute UserDto userDto,
                           HttpServletResponse response) throws ServiceException {
        UserDto result = userService.getUser(userDto);
        response.addHeader(Constants.REFRESH_TOKEN, result.getAuthEntity().getRefreshToken());
        return new EncryptResponse( response, result,null);
    };

    @ApiOperation("회원가입")
    @PostMapping("/signUp")
    public Response signOut(@RequestBody UserDto userDto, HttpServletResponse response) throws ServiceException{
        log.error("WORK!");
        UserDto result = userService.saveUser(userDto);
        response.addHeader(Constants.REFRESH_TOKEN, result.getAuthEntity().getRefreshToken());
        return new EncryptResponse(response, result, null);

    }


}
