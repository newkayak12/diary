package com.server.base.repository.testRepository;

import com.server.base.repository.entity.Test;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class TestRepositoryImpl extends QuerydslRepositorySupport implements TestRepositoryCustom{
    public TestRepositoryImpl() {
        super(Test.class);
    }
}
