package com.server.diary.controller;

import com.server.diary.common.dto.PagingDto;
import com.server.diary.common.exception.ServiceException;
import com.server.diary.common.responseContainer.Response;
import com.server.diary.repository.dto.MemoryDto;
import com.server.diary.repository.dto.SearchParameter;
import com.server.diary.service.MemoryService;
import io.micrometer.core.instrument.search.Search;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "/api/user", description = "추억")
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/memory")
@Slf4j
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;

    @ApiOperation(value = "추억 리스트")
    @GetMapping(value = "/")
    public Response fetchMemoryList(@ModelAttribute SearchParameter searchParameter){
        return new Response(1,null,  memoryService.fetchMemoryList(searchParameter));
    }

    @ApiOperation(value = "추억")
    @GetMapping(value = "/{memoryNo}")
    public Response fetchMemory(@PathVariable Long memoryNo){
        return new Response(1, memoryService.fetchMemory(memoryNo));
    }


    @ApiOperation(value = "추억 저장")
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response save(@ModelAttribute MemoryDto memoryDto) throws ServiceException {
        return new Response(1, memoryService.save(memoryDto));
    }

    @ApiOperation(value = "추억 삭제")
    @DeleteMapping(value = "/{memoryNo}")
    public Response remove(@PathVariable Long memoryNo){
        return new Response(1, memoryService.remove(memoryNo));
    }

}
