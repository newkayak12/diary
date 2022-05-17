package com.server.base.repository.dto;

import com.server.base.common.authorizations.AuthorizeDto;
import com.server.base.common.authorizations.IgnoreEncrypt;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class TestDto implements Serializable {
    private String id1;
    private Integer id2;
    @IgnoreEncrypt
    private Long id3;
    private LocalDateTime id4;
}
