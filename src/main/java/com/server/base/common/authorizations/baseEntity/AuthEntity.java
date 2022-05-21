package com.server.base.common.authorizations.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@AllArgsConstructor
public class AuthEntity {
    @Column(name = "refresh_token", length = 500, nullable = false, columnDefinition = "VARCHAR(500)")
    private String refreshToken;
}
