package com.server.diary.repository.diaryRepository;

import com.server.diary.repository.dto.DiaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long>, DiaryRepositoryCustom {
    public void deleteDiaryByDiaryNo(Long diaryNo);
}
