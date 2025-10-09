package com.jinhee.tdd.playground.signup.service;

import com.jinhee.tdd.playground.signup.domain.authcode.AuthCode;
import com.jinhee.tdd.playground.signup.domain.authcode.AuthCodeErrorCode;
import com.jinhee.tdd.playground.signup.exception.BusinessException;
import com.jinhee.tdd.playground.signup.domain.authcode.AuthCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCodeService {

    private final AuthCodeRepository authCodeRepository;
    private final EmailSender emailSender;

    public void sendAuthCode(String email) {
        AuthCode authCode = authCodeRepository.findByEmail(email).orElse(null);

        // 재요청이 60초 이내인 경우 예외 발생
        if(authCode != null && authCode.isInCooldown()){
            throw new BusinessException(AuthCodeErrorCode.TOO_MANY_REQUESTS);
        }

        authCodeRepository.save(AuthCode.issue(email, "12345"));

        try{
            emailSender.sendVerificationEmail(email);
        } catch (RuntimeException e){
            throw new BusinessException(AuthCodeErrorCode.EMAIL_SEND_FAILED);
        }
    }
}
