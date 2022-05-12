package com.server.base.common.configurations;

import com.server.base.common.exception.ServiceException;
import com.server.base.common.responseContainer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerAdvisor {
    @ResponseBody
    @ExceptionHandler({ServiceException.class})
    public Response ServiceExceptionHandler(ServiceException e){
        return new Response(e.getCode(), e.getMsg(), null);
    }
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Response ExceptionHandler(Exception e){
        return new Response(0, e.getMessage(), e.getCause());
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response notFoundHandler(){
        return new Response(-404, "요청 주소가 잘못됐습니다.", null);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response internalServerErrorHandler(){
        return new Response(-500, "일시적인 오류입니다.", null);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Response unauthorizedHandler(){
        return new Response(-403, "인증이 필요합니다.", null);
    }
}
