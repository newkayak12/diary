package com.server.diary.repository.diaryRepository;

import com.server.diary.repository.dto.DiaryDto;

import java.util.List;
import java.util.Optional;

public interface DiaryRepositoryCustom {

    public List<Diary> getList(DiaryDto diaryDto);
    public Optional<Diary> getDiaryOne(Long diaryNo);
}
