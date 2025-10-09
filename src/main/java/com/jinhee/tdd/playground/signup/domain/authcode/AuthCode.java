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
    private static final long COOLDOWN_SECONDS = 60L;  // 60초 재요청 제한
    private static final long EXPIRATION_MINUTES = 5L; // 5분 유효시간
    private static final int MAX_ATTEMPTS = 5;         // 최대 시도 횟수

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private int attemptCount;

    private int maxAttempts;

    private LocalDateTime lastAttemptsAt;

    private int resendCount;

    @Column(nullable = false)
    private LocalDateTime lastSentAt;

    private boolean verified;

    public static AuthCode issue(String email, String code) {
        AuthCode authCode = new AuthCode();
        authCode.email = email;
        authCode.code = code;
        authCode.expiresAt = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
        authCode.attemptCount = 0;
        authCode.maxAttempts = MAX_ATTEMPTS;
        authCode.lastAttemptsAt = null;
        authCode.resendCount = 0;
        authCode.lastSentAt = LocalDateTime.now();
        authCode.verified = false;
        return authCode;
    }

    // ========== 도메인 로직 ========== //
    /* 아직 쿨타임 중인지 확인 */
    public boolean isInCooldown(){
        if(lastSentAt == null) return false;
        return lastSentAt.plusSeconds(COOLDOWN_SECONDS)
                        .isAfter(LocalDateTime.now());
    }

}
