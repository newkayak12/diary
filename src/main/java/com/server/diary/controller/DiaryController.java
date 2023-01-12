package com.server.diary.controller;

import com.server.diary.common.exception.ServiceException;
import com.server.diary.common.responseContainer.Response;
import com.server.diary.repository.dto.DiaryDto;
import com.server.diary.repository.dto.UserDto;
import com.server.diary.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Tag(name = "/api/diary", description = "다이어리")
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/diary")
@Slf4j
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation(value = "캘린더 리스트")
    @GetMapping(value = "/list")
    public Response getList(@ModelAttribute DiaryDto diaryDto){
        return new Response(1,null, diaryService.getList(diaryDto));
    }

    @ApiOperation(value = "캘린더 내역 하나")
    @GetMapping(value = "/{diaryNo}")
    public Response getOne(@PathVariable Long diaryNo) throws ServiceException {
        return new Response(1, null, diaryService.getOne(diaryNo));
    }


    @ApiOperation(value = "캘린더 등록/수정")
    @PostMapping(value = "/save")
    public Response saveDiary(@RequestBody DiaryDto diaryDto){
        diaryService.save(diaryDto);
        return new Response(1, Objects.nonNull(diaryDto.getDiaryNo()) ? "수정됐습니다.":"등록됐습니다.", null);
    }
    @ApiOperation(value = "캘린더 삭제")
    @DeleteMapping(value = "/remove/{diaryNo}")
    public Response removeDiary(@PathVariable Long diaryNo) {
        diaryService.removeDiary(diaryNo);
        return new Response(1, "삭제됐습니다.", null);
    }


}
