package com.jinhee.tdd.playground.signup.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthCodeErrorCode implements ErrorCode{
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "AC0001", "60초 이내 재요청은 불가능합니다." ),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
