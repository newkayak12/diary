package com.server.diary.common.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto {
    public void setStartDate(String startDate) {
        if(Objects.isNull(startDate)||startDate.equals("")){
            this.startDate=null;
            return ;
        }
        this.startDate = LocalDate.parse(startDate);
    }
    public void setEndDate(String endDate) {
        if(Objects.isNull(endDate)||endDate.equals("")){
            this.endDate=null;
            return ;
        }
        this.endDate = LocalDate.parse(endDate);
    }

    private Integer page;
    private Integer limit;
    private String searchText;
    private LocalDate startDate;
    private LocalDate endDate;
}
