package com.server.diary.common.fileUpload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class FileResult {
    private String originalFileName;
    private String storedFileName;
    private String fileUrl;
    private String contentType;
    private Long fileSize;
}
