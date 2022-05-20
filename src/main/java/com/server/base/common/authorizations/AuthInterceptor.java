package com.server.base.common.authorizations;

import com.server.base.common.constants.Constants;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");
        String refreshToken = request.getHeader(Constants.REFRESH_TOKEN);
        Boolean isExpired = TokenManager.isExpired(accessToken);
        if(Objects.isNull(accessToken)||isExpired){ //access 토큰이 없거나 만료됐다면
            if(Objects.isNull(refreshToken)){ //refresh 토큰이 없다면
                throw new ServiceException(Exceptions.TOKEN_EXPIRED); //error
            } else {//refresh 토큰이 있다면

//                    토큰 decrypt
//                    userNo 가져와서

                //DB에서 userNo로 refresh token을 가져오고
                if(true){ ///DB의 토큰과 같다면

//                    재발급
//                    토큰 Request에 심고
                    return super.preHandle(request, response, handler);

                } else {
                    throw new ServiceException(Exceptions.TOKEN_EXPIRED);
                }
            }
        }

        //access 토큰이 있다면
            return super.preHandle(request, response, handler);
    }
}
