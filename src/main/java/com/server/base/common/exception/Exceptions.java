package com.server.base.common.exception;

import lombok.Getter;

@Getter
public enum Exceptions {
    NO_DATA(-1, "존재하지 않는 데이터입니다.");

    private Integer code;
    private String msg;
    Exceptions(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
