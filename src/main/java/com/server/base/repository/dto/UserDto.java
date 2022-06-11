package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.base.common.enums.UserStatus;
import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.authorizations.annotations.IgnoreEncrypt;
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
public class UserDto implements Serializable {
    private Long userNo;
    private String id;
    @JsonIgnore
    @IgnoreEncrypt
    private String password;
    @JsonIgnore
    private AuthEntityDto authEntity=new AuthEntityDto();
    private UserStatus userStatus;
    private LocalDateTime regDate;
    private LocalDateTime lastLoginDate;
    private LocalDateTime withdrawalDate;
}
