package com.server.diary.repository.memoryRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemoryRepositoryImpl extends QuerydslRepositorySupport implements MemoryRepositoryCustom{
    private JPAQueryFactory query;
    public MemoryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Memory.class);
        this.query = jpaQueryFactory;
    }
}
