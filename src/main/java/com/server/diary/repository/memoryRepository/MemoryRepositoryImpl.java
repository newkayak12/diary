package com.server.diary.repository.memoryRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.diary.repository.dto.MemoryDto;
import com.server.diary.repository.dto.QMemoryDto;
import com.server.diary.repository.dto.QPhotoDto;
import com.server.diary.repository.dto.SearchParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

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

    public Page<MemoryDto> fetchMemoryList(Pageable pageable, SearchParameter searchParameter) {
        searchParameter.calculate();

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(memory.user.userNo.eq(searchParameter.getUserNo()));

        if (Objects.nonNull(searchParameter.getStartDate()) && Objects.nonNull(searchParameter.getEndDate())){
            builder.and(memory.regDate.between(searchParameter.getStartDate(), searchParameter.getEndDate()));
        }
        if (Objects.nonNull(searchParameter.getAddress()) && !StringUtils.isEmpty(searchParameter.getAddress())){
            builder.and(memory.address.contains(searchParameter.getAddress()));
        }
        if (Objects.nonNull(searchParameter.getCategory())){
            builder.and(memory.category.eq(searchParameter.getCategory()));
        }
        if (Objects.nonNull(searchParameter.getSearchText()) && !StringUtils.isEmpty(searchParameter.getSearchText())){
            builder.and(memory.contents.contains(searchParameter.getSearchText()));
        }


                List<MemoryDto> queryResult =
                        query.select(new QMemoryDto( memory.memoryNo,
                                new QPhotoDto(memory.firstPhoto.photoNo, memory.firstPhoto.photoUrl),
                                new QPhotoDto(memory.secondPhoto.photoNo, memory.secondPhoto.photoUrl),
                                new QPhotoDto(memory.thirdPhoto.photoNo, memory.thirdPhoto.photoUrl),
                                memory.contents,
                                memory.regDate,
                                memory.address,
                                memory.category))
                .from(memory)
                .leftJoin( memory.firstPhoto, photo).on(photo.photoNo.eq(memory.firstPhoto.photoNo))
                .leftJoin( memory.secondPhoto, photo).on(photo.photoNo.eq(memory.secondPhoto.photoNo))
                .leftJoin( memory.thirdPhoto, photo).on(photo.photoNo.eq(memory.thirdPhoto.photoNo))
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(memory.memoryNo.desc())
                .fetch();
        JPAQuery<Long> count = query.select(memory.count()).from(memory).where(builder);
//        query.select(memory.count()).from(memory).where(builder).fetchOne()

        return PageableExecutionUtils.getPage(queryResult, pageable, count::fetchOne);
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
                    .leftJoin( memory.firstPhoto, photo).on(photo.photoNo.eq(memory.firstPhoto.photoNo))
                    .leftJoin( memory.secondPhoto, photo).on(photo.photoNo.eq(memory.secondPhoto.photoNo))
                    .leftJoin( memory.thirdPhoto, photo).on(photo.photoNo.eq(memory.thirdPhoto.photoNo))
                    .where(memory.memoryNo.eq(memoryNo))
                    .fetchOne();
    }
}
