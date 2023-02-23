package com.server.diary.repository.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.server.diary.common.enums.Category;
import com.server.diary.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * A DTO for the {@link com.server.diary.repository.memoryRepository.Memory} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemoryDto implements Serializable {
    private Long memoryNo;
    private PhotoDto firstPhoto;
    private MultipartFile firstMultipartFile;
    private PhotoDto secondPhoto;
    private MultipartFile secondMultipartFile;
    private PhotoDto thirdPhoto;
    private MultipartFile thirdMultipartFile;
    private String contents;
    private LocalDateTime regDate;
    private String address;
    private Category category;

    public void setCategory(String category) throws ServiceException {
        this.category = Arrays.stream(Category.values()).filter(ca -> ca.equals(category)).findFirst().orElseThrow(() -> new ServiceException("파라미터가 잘못됐습니다."));
    }
}