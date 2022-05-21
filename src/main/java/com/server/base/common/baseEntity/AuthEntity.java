package com.server.base.common.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@AllArgsConstructor
public class AuthEntity {
    @Column(name = "refresh_token", length = 500, nullable = false, columnDefinition = "VARCHAR(500)")
    private String refreshToken;

    @Column(name= "last_sign_in_date", updatable = false)
    private LocalDateTime lastSignInDate;
    @Column(name = "reg_date", updatable = false)
    private LocalDateTime regDate;
    @Column(name = "withdrawal_date")
    private LocalDateTime withdrawalDate;
}
