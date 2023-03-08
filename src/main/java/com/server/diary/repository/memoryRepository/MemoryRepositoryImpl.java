package com.server.diary.repository.memoryRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.diary.repository.dto.*;
import com.server.diary.repository.photoRepository.Photo;
import com.server.diary.repository.photoRepository.QPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

import static com.server.diary.repository.memoryRepository.QMemory.memory;
import static com.server.diary.repository.photoRepository.QPhoto.photo;

public class MemoryRepositoryImpl extends QuerydslRepositorySupport implements MemoryRepositoryCustom{
    private JPAQueryFactory query;

    public MemoryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Memory.class);
        this.query = jpaQueryFactory;
    }

    @Override

    public Page fetchMemoryList(Pageable pageable, SearchParameter searchParameter) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(memory.user.userNo.eq(searchParameter.getUserNo()));

        if (Objects.nonNull(searchParameter.getStartDate()) && Objects.nonNull(searchParameter.getEndDate())){
            builder.and(memory.regDate.between(searchParameter.getStartDate(), searchParameter.getEndDate()));
        }
        if (Objects.nonNull(searchParameter.getAddress())){
            builder.and(memory.address.contains(searchParameter.getAddress()));
        }
        if (Objects.nonNull(searchParameter.getCategory())){
            builder.and(memory.category.eq(searchParameter.getCategory()));
        }


                List<MemoryDto> list =
                        query.select(new QMemoryDto( memory.memoryNo,
                                new QPhotoDto(memory.firstPhoto.photoNo, memory.firstPhoto.photoUrl),
                                new QPhotoDto(memory.secondPhoto.photoNo, memory.secondPhoto.photoUrl),
                                new QPhotoDto(memory.thirdPhoto.photoNo, memory.thirdPhoto.photoUrl),
                                memory.contents,
                                memory.regDate,
                                memory.address,
                                memory.category))
                .from(memory)
                .leftJoin(photo).on(photo.photoNo.eq(memory.firstPhoto.photoNo))
                .leftJoin(photo).on(photo.photoNo.eq(memory.secondPhoto.photoNo))
                .leftJoin(photo).on(photo.photoNo.eq(memory.thirdPhoto.photoNo))
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(memory.memoryNo.desc())
                .fetch();
        return new PageImpl(list, pageable, query.selectFrom(memory).fetchCount());
    }

    @Override
    public MemoryDto fetchMemory(Long memoryNo) {
        return query.select(new QMemoryDto( memory.memoryNo,
                                new QPhotoDto(memory.firstPhoto.photoNo, memory.firstPhoto.photoUrl),
                                new QPhotoDto(memory.secondPhoto.photoNo, memory.secondPhoto.photoUrl),
                                new QPhotoDto(memory.thirdPhoto.photoNo, memory.thirdPhoto.photoUrl),
                                memory.contents,
                                memory.regDate,
                                memory.address,
                                memory.category))
                    .from(memory)
                    .leftJoin(photo).on(photo.photoNo.eq(memory.firstPhoto.photoNo))
                    .leftJoin(photo).on(photo.photoNo.eq(memory.secondPhoto.photoNo))
                    .leftJoin(photo).on(photo.photoNo.eq(memory.thirdPhoto.photoNo))
                    .where(memory.memoryNo.eq(memoryNo))
                    .fetchOne();
    }
}
