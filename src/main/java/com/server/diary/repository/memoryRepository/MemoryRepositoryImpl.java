package com.server.diary.repository.memoryRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.diary.repository.dto.MemoryDto;
import com.server.diary.repository.dto.PhotoDto;
import com.server.diary.repository.dto.SearchParameter;
import com.server.diary.repository.photoRepository.QPhoto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<MemoryDto> fetchMemoryList(Pageable pageable, SearchParameter searchParameter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (Objects.nonNull(searchParameter.getStartDate()) && Objects.nonNull(searchParameter.getEndDate())){
            builder.and(memory.regDate.between(searchParameter.getStartDate().atStartOfDay(), searchParameter.getEndDate().plusDays(1).atStartOfDay().minusNanos(1)));
        }
        if (Objects.nonNull(searchParameter.getAddress())){
            builder.and(memory.address.contains(searchParameter.getAddress()));
        }
        if (Objects.nonNull(searchParameter.getCategory())){
            builder.and(memory.category.eq(searchParameter.getCategory()));
        }



                QueryResults<MemoryDto> result = query.select(Projections.bean(MemoryDto.class,
                                memory.memoryNo,
                                memory.category,
                                memory.contents,
                                memory.address,
                                memory.regDate,
                                memory.firstPhoto,
                                memory.secondPhoto,
                                memory.thirdPhoto
                        ))
//                .select(Projections.bean(PhotoDto.class, memory.firstPhoto))
//                .select(Projections.bean(PhotoDto.class, memory.secondPhoto))
//                .select(Projections.bean(PhotoDto.class, memory.thirdPhoto))
                .from(memory)
                .leftJoin(memory.firstPhoto)
                .leftJoin(memory.secondPhoto)
                .leftJoin(memory.thirdPhoto)
//                .leftJoin(photo).on(memory.firstPhoto.photoNo.eq(photo.photoNo))
//                .leftJoin(photo).on(memory.secondPhoto.photoNo.eq(photo.photoNo))
//                .leftJoin(photo).on(memory.thirdPhoto.photoNo.eq(photo.photoNo))
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(memory.memoryNo.asc())
                .fetchResults();


        return new PageImpl<MemoryDto>(result.getResults(), pageable, result.getTotal());
    }
}
