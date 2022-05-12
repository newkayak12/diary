package com.server.base.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PagingDto {
    private Integer page;
    private Integer limit;
}
