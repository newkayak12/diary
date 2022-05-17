package com.server.base.common.responseContainer;

import com.server.base.common.authorizations.TokenManager;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenResponse {
    public TokenResponse(Object tokenDto, HttpServletResponse response, TokenManager tokenManager) {
        this.userData = tokenDto;
        response.setHeader(HttpHeaders.AUTHORIZATION, tokenManager.encrypt(tokenDto));
    }
    private Object userData;
}
