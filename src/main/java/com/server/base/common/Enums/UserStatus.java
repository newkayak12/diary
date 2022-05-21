package com.server.base.common.Enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    WITHDRAW(-1),
    ACTIVATED(0),
    DEACTIVATED(1);

    UserStatus(Integer status) {
        this.status = status;
    }

    Integer status;
}
