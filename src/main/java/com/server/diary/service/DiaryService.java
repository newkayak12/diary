package com.server.diary.service;

import com.server.diary.common.exception.Exceptions;
import com.server.diary.common.exception.ServiceException;
import com.server.diary.repository.diaryRepository.Diary;
import com.server.diary.repository.diaryRepository.DiaryRepository;
import com.server.diary.repository.dto.DiaryDto;
import com.server.diary.repository.userRepository.User;
import com.server.diary.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
    private final ModelMapper mapper;
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<DiaryDto> getList(DiaryDto diaryDto) {
        return diaryRepository.getList(diaryDto).stream().map(i-> mapper.map(i, DiaryDto.class)).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public DiaryDto getOne(Long diaryNo) throws ServiceException {
        return mapper.map(diaryRepository.getDiaryOne(diaryNo).orElseThrow(()-> new ServiceException(Exceptions.INVALID_DATA)), DiaryDto.class);
    }
    @Transactional
    public void save(DiaryDto diaryDto) {
        Diary diaryEntity = mapper.map(diaryDto, Diary.class);
        diaryEntity.setUser(userRepository.getUserByUserNo(diaryDto.getUserNo()));
        Diary diary = diaryRepository.save(diaryEntity);
    }

    @Transactional
    public void removeDiary(Long diaryNo) {
        diaryRepository.deleteDiaryByDiaryNo(diaryNo);
    }
}
