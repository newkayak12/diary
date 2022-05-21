package com.server.base.common.baseEntity;

import com.server.base.common.Enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AuthEntity {
    @Column(name = "refresh_token", length = 500, nullable = false, columnDefinition = "VARCHAR(500)")
    private String refreshToken;

}
