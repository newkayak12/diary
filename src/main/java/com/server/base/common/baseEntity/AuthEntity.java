package com.server.base.common.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Setter
public class AuthEntity {
    @Column(name = "refresh_token", length = 500, nullable = false, columnDefinition = "VARCHAR(500)")
    private String refreshToken;
}
