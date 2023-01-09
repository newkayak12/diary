package com.server.diary.repository.diaryRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.diary.repository.dto.DiaryDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;
import static com.server.diary.repository.diaryRepository.QDiary.diary;
public class DiaryRepositoryImpl extends QuerydslRepositorySupport implements DiaryRepositoryCustom{
    private JPAQueryFactory query;
    public DiaryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Diary.class);
        this.query = jpaQueryFactory;
    }

    @Override
    public List<Diary> getList(DiaryDto diaryDto) {
        return query.select(diary).from(diary)
                .where(diary.user.userNo.eq(diaryDto.getUserNo())
                        .and(diary.regDate.year().eq(diaryDto.getYear()))
                        .and(diary.regDate.month().eq(diaryDto.getMonth()))
                )
                .fetch();
    }

    @Override
    public Optional<Diary> getDiaryOne(Long diaryNo) {
        return Optional.ofNullable(query.select(diary)
                .from(diary).where(diary.diaryNo.eq(diaryNo)).fetchOne());
    }

}
