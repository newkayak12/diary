package com.server.base.common.authorizations;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Authentication {
    TokenManager tokenManager = new TokenManager();
}
