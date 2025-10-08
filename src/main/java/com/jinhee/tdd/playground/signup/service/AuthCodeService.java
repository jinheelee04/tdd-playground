package com.jinhee.tdd.playground.signup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCodeService {
    private final EmailSender emailSender;

    public void sendAuthCode(String email){
        emailSender.sendVerificationEmail(email);
    }
}
