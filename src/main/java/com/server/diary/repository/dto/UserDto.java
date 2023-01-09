package com.server.diary.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.diary.common.enums.UserStatus;
import com.server.diary.common.authorizations.annotations.AuthorizeDto;
import com.server.diary.common.authorizations.annotations.IgnoreEncrypt;
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
@JsonIgnoreProperties(allowSetters = true, allowGetters = false, value = {"password"})
public class UserDto implements Serializable {
    private Long userNo;
    private String userId;
    @IgnoreEncrypt
    private String password;
    @JsonIgnore
    private AuthEntityDto authEntity=new AuthEntityDto();
    private UserStatus userStatus;
    private LocalDateTime regDate;
    private LocalDateTime lastLoginDate;
    private LocalDateTime withdrawalDate;
}
