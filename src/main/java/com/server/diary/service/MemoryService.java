package com.server.diary.service;

import com.server.diary.common.dto.PagingContainer;
import com.server.diary.common.responseContainer.Response;
import com.server.diary.repository.dto.MemoryDto;
import com.server.diary.repository.dto.SearchParameter;
import com.server.diary.repository.memoryRepository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryRepository memoryRepository;

    @Transactional(readOnly = true)
    public PagingContainer fetchMemoryList(SearchParameter searchParameter) {
        Pageable pageable = PageRequest.of(searchParameter.getPage(), searchParameter.getLimit());
        return new PagingContainer(pageable, memoryRepository.fetchMemoryList(pageable, searchParameter));
    }

    @Transactional(readOnly = true)
    public MemoryDto fetchMemory(Long memoryNo) {
         return memoryRepository.fetchMemory(memoryNo);
    }


    public String save(MemoryDto memoryDto) {
        return "저장했습니다.";
    }
}
