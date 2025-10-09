package com.jinhee.tdd.playground.signup.domain.authcode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthCodeRepository extends JpaRepository<AuthCode, Long> {

    Optional<AuthCode> findByEmail(String email);
}
