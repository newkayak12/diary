package com.server.base.common.authorizations;

import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
@Aspect
public class Authentication {
    TokenManager tokenManager = new TokenManager();

    @Around("@annotation(com.server.base.common.authorizations.Authorization)")
    public Object decrypt(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] parameterValues = joinPoint.getArgs();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        Reflections reflections = new Reflections("com.server.base.repository.dto");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(AuthorizeDto.class);

        Iterator<Class<?>> targetOne = classSet.iterator();
        Class<?> target = targetOne.next();
        for(int i = 0; i<parameters.length; i++){
            Long isExist = Arrays.asList(parameters[i].getDeclaredAnnotations())
                    .stream().filter(item-> {
                        return item.annotationType().equals(org.springframework.web.bind.annotation.RequestHeader.class);
                    }).count();
            if(isExist>0){
                Object  token = parameterValues[i];
                if(Objects.isNull(token)){
                    throw new ServiceException(Exceptions.INVALID_ACCESS);
                }
                parameterValues[i] = tokenManager.decrypt(target.getConstructor().newInstance(), (String) token);
                break;
            }
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
