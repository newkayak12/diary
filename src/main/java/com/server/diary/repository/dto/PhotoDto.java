package com.server.diary.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.server.diary.repository.photoRepository.Photo} entity
 */
@Data
@NoArgsConstructor
@Builder
public class PhotoDto implements Serializable {
    @QueryProjection
    public PhotoDto(Long photoNo, String photoUrl) {
        this.photoNo = photoNo;
        this.photoUrl = photoUrl;
    }

    private Long photoNo;
    private String photoUrl;
}