package com.server.diary.repository.userRepository;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    private JPAQueryFactory query;
    @Autowired
    public UserRepositoryImpl(JPAQueryFactory query) {
        super(User.class);
        this.query = query;
    }






}
