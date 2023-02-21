package com.server.diary.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.server.diary.repository.photoRepository.Photo} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto implements Serializable {
    private Long photoNo;
    private String photoUrl;
}