package com.server.diary.controller;

import com.server.diary.common.constants.Constants;
import com.server.diary.common.exception.ServiceException;
import com.server.diary.common.responseContainer.EncryptResponse;
import com.server.diary.common.responseContainer.Response;
import com.server.diary.repository.dto.UserDto;
import com.server.diary.service.UserService;
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
    @GetMapping(value = "/in")
    public Response signIn(@ModelAttribute UserDto userDto,
                           HttpServletResponse response) throws ServiceException {
        UserDto result = userService.getUser(userDto);
        response.addHeader(Constants.REFRESH_TOKEN, result.getAuthEntity().getRefreshToken());
        return new EncryptResponse( response, result,null);
    };
//
    @ApiOperation("회원가입")
    @PostMapping("/up")
    public Response signUp(@RequestBody UserDto userDto, HttpServletResponse response) throws ServiceException{
        UserDto result = userService.saveUser(userDto);
        return new EncryptResponse(response, result, null);
    }
}
