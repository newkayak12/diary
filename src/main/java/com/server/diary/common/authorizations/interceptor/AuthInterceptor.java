package com.server.diary.common.authorizations.interceptor;

import com.server.diary.common.authorizations.TokenManager;
import com.server.diary.common.constants.Constants;
import com.server.diary.common.exception.Exceptions;
import com.server.diary.common.exception.ServiceException;
import com.server.diary.repository.dto.UserDto;
import com.server.diary.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

//@RequiredArgsConstructor
@NoArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private  UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(Constants.IS_DEV_MODE){
            System.out.println(" _____         _                                   _                ");
            System.out.println("|_   _|       | |                                 | |               ");
            System.out.println("  | |   _ __  | |_   ___  _ __   ___   ___  _ __  | |_   ___   _ __ ");
            System.out.println("  | |  | '_ \\ | __| / _ \\| '__| / __| / _ \\| '_ \\ | __| / _ \\ | '__|");
            System.out.println(" _| |_ | | | || |_ |  __/| |   | (__ |  __/| |_) || |_ | (_) || |   ");
            System.out.println(" \\___/ |_| |_| \\__| \\___||_|    \\___| \\___|| .__/  \\__| \\___/ |_|   ");
            System.out.println("                                           | |                      ");
            System.out.println("                                           |_|                      ");
        }

            String accessToken = request.getHeader("Authorization");
            String refreshToken = request.getHeader(Constants.REFRESH_TOKEN);
            Boolean isExpired = null;
            try{
                isExpired = TokenManager.isExpired(accessToken);
            } catch (NullPointerException e){
                isExpired = true;
            }

            if(Objects.isNull(accessToken)||isExpired){ //access ????????? ????????? ???????????????
                if(Objects.isNull(refreshToken)){ //refresh ????????? ?????????
                    throw new ServiceException(Exceptions.TOKEN_EXPIRED); //error
                } else {//refresh ????????? ?????????
                    UserDto userDto = TokenManager.decrypt(new UserDto(), accessToken);//    ?????? decrypt
                    userDto = userService.getRefreshToken(userDto.getUserNo());//  userNo ????????????
                    String userToken  = userDto.getAuthEntity().getRefreshToken();
                    //DB?????? userNo??? refresh token??? ????????????
                    if(!Objects.isNull(userToken)&&userToken.equals(refreshToken)){ ///DB??? ????????? ?????????
                        String accessTokenRemade = TokenManager.encrypt(userDto); //   ?????????
                        response.addHeader(Constants.REFRESH_TOKEN, accessTokenRemade);
                        return HandlerInterceptor.super.preHandle(request, response, handler);
                    } else {
                        throw new ServiceException(Exceptions.TOKEN_EXPIRED);
                    }
                }
            }

            //access ????????? ?????? ????????? ????????????
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
}
