package com.jinhee.tdd.playground.signup.domain.authcode;

import com.jinhee.tdd.playground.signup.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthCodeErrorCode implements ErrorCode {
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "AC0001", "60초 이내 재요청은 불가능합니다." ),
    EMAIL_SEND_FAILED(HttpStatus.SERVICE_UNAVAILABLE , "AC0002", "이메일 발송에 실패했습니다. 관리자에게 문의하세요." ),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
