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

    private LocalDateTime maxAttemptsAt;

    private int resendCount;

    @Column(nullable = false)
    private LocalDateTime lastSentAt;

    private boolean verified;

    // ========== 도메인 로직 ========== //
    /* 아직 쿨타임 중인지 확인 */
    public boolean isInCooldown(){
        if(lastSentAt == null) return false;
        return lastSentAt.plusSeconds(COOLDOWN_SECONDS)
                        .isAfter(LocalDateTime.now());
    }

}
