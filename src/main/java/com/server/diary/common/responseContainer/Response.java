package com.server.diary.common.responseContainer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Response {
    private int code;
    private String msg;
    private Object data;

    public Response(int code) {
        this.code = code;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Response(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
