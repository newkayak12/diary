package com.server.diary.diaryTest;

import com.server.diary.baseTest.BaseTest;
import com.server.diary.repository.diaryRepository.Diary;
import com.server.diary.repository.dto.DiaryDto;
import org.junit.Test;

import java.time.LocalDate;

public class DiaryTest extends BaseTest {

    @Test
   public void saveDiary(){
        DiaryDto diaryDto = new DiaryDto();
        diaryDto.setUserNo(12L);
        diaryDto.setContents("HELLO");
        diaryDto.setRegDate(LocalDate.parse("2023-01-01"));
        diaryDto.setMonth(1);
        diaryDto.setYear(2023);
        post("/diary/save", diaryDto);
    }

    @Test
    public void getList(){
        DiaryDto diaryDto = new DiaryDto();
        diaryDto.setUserNo(12L);
        diaryDto.setContents("HELLO");
        diaryDto.setRegDate(LocalDate.parse("2023-01-01"));
        diaryDto.setMonth(1);
        diaryDto.setYear(2023);
        get("/diary/list", diaryDto);
    }

    @Test
    public void getOne(){
        DiaryDto diaryDto = new DiaryDto();
        diaryDto.setDiaryNo(3L);
//        diaryDto.setUserNo(12L);
//        diaryDto.setContents("HELLO");
//        diaryDto.setRegDate(LocalDate.parse("2023-01-01"));
//        diaryDto.setMonth(1);
//        diaryDto.setYear(2023);
        get("/diary/3", diaryDto);
    }

    @Test
    public void removeDiary(){
        DiaryDto diaryDto = new DiaryDto();
        diaryDto.setDiaryNo(3L);
        delete("/diary/remove/3", diaryDto);
    }

}
