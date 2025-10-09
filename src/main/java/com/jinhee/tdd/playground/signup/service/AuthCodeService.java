package com.jinhee.tdd.playground.signup.service;

import com.jinhee.tdd.playground.signup.domain.authcode.AuthCode;
import com.jinhee.tdd.playground.signup.domain.authcode.AuthCodeErrorCode;
import com.jinhee.tdd.playground.signup.exception.BusinessException;
import com.jinhee.tdd.playground.signup.domain.authcode.AuthCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthCodeService {

    private final AuthCodeRepository authCodeRepository;
    private final EmailSender emailSender;

    public void sendAuthCode(String email) {
        AuthCode authCode = authCodeRepository.findByEmail(email).orElse(null);

        // 마지막 요청 시간이 60초 이내인 경우 예외 발생
        if(authCode != null && authCode.getLastSentAt() != null
            && authCode.getLastSentAt().plusSeconds(60).isAfter(LocalDateTime.now())){
            throw new BusinessException(AuthCodeErrorCode.TOO_MANY_REQUESTS);
        }

        emailSender.sendVerificationEmail(email);
    }
}
