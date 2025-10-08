package com.jinhee.tdd.playground.signup.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // Spring 컨텍스트 없이 Mock 주입 가능
public class AuthCodeServiceTest {
    @Mock     // 의존 객체를 가짜로 만듦
    EmailSender emailSender;


    @InjectMocks // Mock 객체를 주입해 실제 테스트 대상(Service 생성)
    AuthCodeService authCodeService;

    @DisplayName("정상 이메일 요청 시 발송 성공")
    @Test
    void should_sendVerificationEmail_when_requestIsValid(){
        // given
        String email = "test@test.co.kr";
        // when
        authCodeService.sendAuthCode(email);
        // then : emailSender Mock의 sendVerificationEmail()이 딱 한 번 호출 됐는지 확인
        verify(emailSender).sendVerificationEmail(email);
    }
}
