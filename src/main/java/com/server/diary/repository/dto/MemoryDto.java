package com.server.diary.repository.dto;

import com.server.diary.common.enums.Category;
import com.server.diary.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * A DTO for the {@link com.server.diary.repository.memoryRepository.Memory} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryDto implements Serializable {
    private Long memoryNo;
    private PhotoDto firstPhoto;
    private PhotoDto secondPhoto;
    private PhotoDto thirdPhoto;
    private String contents;
    private LocalDateTime regDate;
    private String address;
    private Category category;

    public void setCategory(String category) throws ServiceException {
        this.category = Arrays.stream(Category.values()).filter(ca -> ca.equals(category)).findFirst().orElseThrow(() -> new ServiceException("파라미터가 잘못됐습니다."));
    }
}