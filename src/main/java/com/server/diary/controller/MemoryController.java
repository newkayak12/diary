package com.server.diary.controller;

import com.server.diary.common.dto.PagingDto;
import com.server.diary.common.responseContainer.Response;
import com.server.diary.repository.dto.SearchParameter;
import com.server.diary.service.MemoryService;
import io.micrometer.core.instrument.search.Search;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "/api/user", description = "추억")
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/memory")
@Slf4j
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;

    @ApiOperation(value = "캘린더 리스트")
    @GetMapping(value = "/")
    public Response fetchMemoryList(@ModelAttribute SearchParameter searchParameter){
        return new Response(1,null,  memoryService.fetchMemoryList(searchParameter));
    }
}
