package com.jinhee.tdd.playground.signup.api.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinhee.tdd.playground.signup.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;

@Getter
@Builder
public class ApiResponse<T> {

    private final boolean success;
    private final int status;
    private final String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;

    private final T data;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .code("OK")
                .message(null)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .code("OK")
                .message(null)
                .data((T)Collections.emptyMap())
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode, T details) {
        return ApiResponse.<T>builder()
                .success(false)
                .status(errorCode.getStatus().value())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(details)
                .build();
    }
}