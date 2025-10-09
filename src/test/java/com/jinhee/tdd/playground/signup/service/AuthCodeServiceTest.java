package com.jinhee.tdd.playground.signup.service;

import com.jinhee.tdd.playground.signup.domain.authcode.AuthCode;
import com.jinhee.tdd.playground.signup.exception.BusinessException;
import com.jinhee.tdd.playground.signup.domain.authcode.AuthCodeErrorCode;
import com.jinhee.tdd.playground.signup.domain.authcode.AuthCodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Spring 컨텍스트 없이 Mock 주입 가능
public class AuthCodeServiceTest {
    @Mock     // 의존 객체를 가짜로 만듦
    EmailSender emailSender;

    @Mock
    AuthCodeRepository authCodeRepository;

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

    @DisplayName("이전 발송 후 60초 이내 재요청 시 TooManyRequestsException 발생")
    @Test
    void should_throwException_when_resendRequestedWithinCooldown(){
        LocalDateTime now = LocalDateTime.now();
        String email = "test@test.co.kr";
        AuthCode existing = AuthCode.builder()
                .id(1L)
                .email(email)
                .code("12345")
                .lastSentAt(now)
                .build();
        // 이미 발송 이력이 있는 것으로 Mock 설정
        given(authCodeRepository.findByEmail(email))
                .willReturn(Optional.of(existing));

        assertThatThrownBy(()-> authCodeService.sendAuthCode(email))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(AuthCodeErrorCode.TOO_MANY_REQUESTS.getMessage());
    }

    @DisplayName("이메일 발송 실패 시 EmailSendFailedException 발생")
    @Test
    void should_throwException_when_emailSendFails(){
        String email = "test@test.co.kr";
        willThrow(new RuntimeException("SMTP Error"))
                .given(emailSender).sendVerificationEmail(email);

        assertThatThrownBy(()-> authCodeService.sendAuthCode(email))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(AuthCodeErrorCode.EMAIL_SEND_FAILED.getMessage());
    }

    @DisplayName("처음 요청 시 새 AuthCode 생성 및 저장 성공")
    @Test
    void should_saveAuthCode_when_firstRequest(){
        String email = "test@test.co.kr";
        given(authCodeRepository.findByEmail(email)).willReturn(Optional.empty());

        authCodeService.sendAuthCode(email);

        ArgumentCaptor<AuthCode> captor = ArgumentCaptor.forClass(AuthCode.class);
        then(authCodeRepository).should().save(captor.capture());
        AuthCode authCode = captor.getValue();

        assertThat(authCode).isNotNull();
        assertThat(authCode.getEmail()).isEqualTo(email);
        assertThat(authCode.getAttemptCount()).isZero();
        assertThat(authCode.getCode()).isNotNull();
        assertThat(authCode.getLastSentAt()).isNotNull();

        verify(emailSender).sendVerificationEmail(email);
    }
}
