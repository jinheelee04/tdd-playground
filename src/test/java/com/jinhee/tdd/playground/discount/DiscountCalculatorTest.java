package com.jinhee.tdd.playground.discount;

import com.jinhee.tdd.playground.expiry.DiscountCalculator;
import com.jinhee.tdd.playground.expiry.Membership;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 할인 정책 계산기
 * 요구사항
 * - 회원 등급별 할인
 *   - GOLD -> 20%
 *   - SILVER -> 10%
 *   - BASIC -> 할인 없음
 * - 시간대별 추가 할인
 *   - 조조(06:00 ~ 09:00) -> 5%
 *   - 야간(21:00 ~ 23:00) -> 5%
 * - 회원 할인 + 시간대 할인을 모두 적용 가능
 * - 최대 할인 한도 30%
 *
 * 테스트 순서
 * 1. BASIC 회원 + 10시 30분 예약
 *
 */
public class DiscountCalculatorTest {

    @Test
    @DisplayName("BASIC 회원이 10시 30분에 예약하면 할인율 없음")
    void givenBasicMember_whenReserveAt10AM30MIN_thenNoDiscount() {
        LocalTime reservationTime = LocalTime.of(10, 30);
        int price = 10_000;

        DiscountCalculator cal = new DiscountCalculator();
        int discounted = cal.calculateDiscount(price, Membership.BASIC, reservationTime);
        assertEquals(price, discounted);
    }
}
