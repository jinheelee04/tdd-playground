package com.jinhee.tdd.playground.signup.domain.authcode;

import com.jinhee.tdd.playground.signup.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "auth_code")
public class AuthCode extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private int attemptCount;

    private int maxAttempts;

    private LocalDateTime maxAttemptsAt;

    private int resendCount;

    @Column(nullable = false)
    private LocalDateTime lastSentAt;

    private boolean verified;

}
