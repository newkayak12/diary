package com.server.diary.repository.dto;

import com.server.diary.common.dto.PagingDto;
import com.server.diary.common.enums.Category;
import com.server.diary.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchParameter extends PagingDto {
    private Long userNo;
    private Category category;
    private String address;

    public void setCategory(String category) throws ServiceException {
        this.category = Arrays.stream(Category.values()).filter(ca -> ca.equals(category)).findFirst().orElseThrow(() -> new ServiceException("파라미터가 잘못됐습니다."));
    }
}
