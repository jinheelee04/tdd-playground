package com.jinhee.tdd.playground.signup.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Object... message){
        super(String.format(errorCode.getMessage(), message));
        this.errorCode = errorCode;
    }

}
