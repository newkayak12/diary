package com.server.diary.repository.memoryRepository;

import com.server.diary.repository.dto.MemoryDto;
import com.server.diary.repository.dto.SearchParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemoryRepositoryCustom {
    Page<MemoryDto> fetchMemoryList(Pageable pageable, SearchParameter searchParameter);

    MemoryDto fetchMemory(Long memoryNo);
}
