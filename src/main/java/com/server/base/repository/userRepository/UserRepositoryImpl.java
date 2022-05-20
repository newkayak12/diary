package com.server.base.repository.userRepository;

import com.server.base.repository.entity.Test;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryImpl() {
        super(Test.class);
    }
}
