package com.jinhee.tdd.playground.signup.service;

import org.springframework.stereotype.Service;

@Service
public class SmtpEmailSender implements EmailSender{

    @Override
    public void sendVerificationEmail(String email) {
        // TODO: 실제 SMTP 발송 로직
    }
}
