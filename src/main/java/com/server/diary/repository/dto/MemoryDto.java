package com.server.diary.repository.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.querydsl.core.annotations.QueryProjection;
import com.server.diary.common.enums.Category;
import com.server.diary.common.exception.ServiceException;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * A DTO for the {@link com.server.diary.repository.memoryRepository.Memory} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemoryDto implements Serializable {

    private Long memoryNo;
    private UserDto user;
    private PhotoDto firstPhoto;
    private MultipartFile firstMultipartFile;
    private PhotoDto secondPhoto;
    private MultipartFile secondMultipartFile;
    private PhotoDto thirdPhoto;
    private MultipartFile thirdMultipartFile;
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;
    private String address;
    private Category category;

    public void setCategory(String category) throws ServiceException {
        this.category = Arrays.stream(Category.values()).filter(ca -> ca.name().equals(category)).findFirst().orElseThrow(() -> new ServiceException("파라미터가 잘못됐습니다."));
    }

//    public void setRegDate(String regDate) {
//        this.regDate = LocalDate.parse(regDate).atStartOfDay();
//    }


    @QueryProjection
    public MemoryDto(Long memoryNo, UserDto user, PhotoDto firstPhoto, PhotoDto secondPhoto, PhotoDto thirdPhoto, String contents, LocalDate regDate, String address, Category category) {
        this.memoryNo = memoryNo;
        this.user = user;
        this.firstPhoto = firstPhoto;
        this.secondPhoto = secondPhoto;
        this.thirdPhoto = thirdPhoto;
        this.contents = contents;
        this.regDate = regDate;
        this.address = address;
        this.category = category;
    }

    @QueryProjection
    public MemoryDto(Long memoryNo,
                     PhotoDto firstPhoto,
                     PhotoDto secondPhoto,
                     PhotoDto thirdPhoto,
                     String contents,
                     LocalDate regDate,
                     String address,
                     Category category) {
        this.memoryNo = memoryNo;
        this.firstPhoto = firstPhoto;
        this.secondPhoto = secondPhoto;
        this.thirdPhoto = thirdPhoto;
        this.contents = contents;
        this.regDate = regDate;
        this.address = address;
        this.category = category;
    }
}