package com.server.diary.service;

import com.server.diary.common.constants.Constants;
import com.server.diary.common.dto.PagingContainer;
import com.server.diary.common.exception.ServiceException;
import com.server.diary.common.responseContainer.Response;
import com.server.diary.repository.dto.MemoryDto;
import com.server.diary.repository.dto.PhotoDto;
import com.server.diary.repository.dto.SearchParameter;
import com.server.diary.repository.memoryRepository.Memory;
import com.server.diary.repository.memoryRepository.MemoryRepository;
import com.server.diary.repository.photoRepository.Photo;
import com.server.diary.repository.photoRepository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.newkayak.FileUpload.FileUpload;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final PhotoRepository photoRepository;
    private final FileUpload fileUpload;
    private final ModelMapper mapper;

    @Transactional(readOnly = true)
    public PagingContainer fetchMemoryList(SearchParameter searchParameter) {
        Pageable pageable = PageRequest.of(searchParameter.getPage() - 1, searchParameter.getLimit());
        return new PagingContainer(pageable, memoryRepository.fetchMemoryList(pageable, searchParameter));
    }

    @Transactional(readOnly = true)
    public MemoryDto fetchMemory(Long memoryNo) {
//         return mapper.map(memoryRepository.fetchMemory(memoryNo), MemoryDto.class);
        return memoryRepository.fetchMemory(memoryNo);
    }


    @Transactional(rollbackFor = Exception.class)
    public String save(MemoryDto memoryDto) throws ServiceException {
        try {



            if(Objects.nonNull(memoryDto.getFirstMultipartFile())){
                Photo first = fileUpload.upload(true, memoryDto.getFirstMultipartFile()).stream()
                        .findFirst().map(file -> mapper.map(PhotoDto.builder().photoUrl(file.getStoredFileName()).build(), Photo.class))
                        .orElseThrow(() -> new ServiceException("파일 저장에 실패했습니다."));
                memoryDto.setFirstPhoto(mapper.map(photoRepository.save(first), PhotoDto.class));
            }
            if(Objects.nonNull(memoryDto.getSecondMultipartFile())){
                Photo second = fileUpload.upload(true, memoryDto.getSecondMultipartFile()).stream()
                        .findFirst().map(file -> mapper.map(PhotoDto.builder().photoUrl(file.getStoredFileName()).build(), Photo.class))
                        .orElseThrow(() -> new ServiceException("파일 저장에 실패했습니다."));
                memoryDto.setSecondPhoto(mapper.map(photoRepository.save(second), PhotoDto.class));
            }
            if(Objects.nonNull(memoryDto.getThirdMultipartFile())){
                Photo third = fileUpload.upload(true, memoryDto.getThirdMultipartFile()).stream()
                        .findFirst().map(file -> mapper.map(PhotoDto.builder().photoUrl(file.getStoredFileName()).build(), Photo.class))
                        .orElseThrow(() -> new ServiceException("파일 저장에 실패했습니다."));
                memoryDto.setThirdPhoto(mapper.map(photoRepository.save(third), PhotoDto.class));
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }


        Memory memory = mapper.map(memoryDto, Memory.class);

        if(Objects.isNull(memoryRepository.save(memory))){
            throw new ServiceException("저장에 실패했습니다.");
        }
        log.warn("3");
        return "저장했습니다.";
    }

    @Transactional(rollbackFor = Exception.class)
    public String remove(Long memoryNo) {
        memoryRepository.deleteMemoryByMemoryNo(memoryNo);
        return "삭제했습니다.";
    }
}
