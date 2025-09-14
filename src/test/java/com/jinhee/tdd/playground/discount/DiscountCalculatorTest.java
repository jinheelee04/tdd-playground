package com.jinhee.tdd.playground.discount;

import com.jinhee.tdd.playground.expiry.DiscountCalculator;
import com.jinhee.tdd.playground.expiry.Membership;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
 * 1. BASIC 회원 + 10시 30분 예약 -> 할인율 없음
 * 2. SILVER 회원 + 10시 30분 예약 -> 10% 할인
 * 3. GOLD 회원 + 10시 30분 예약 -> 20% 할인
 * 4. BASIC 회원 + 6시 30분 예약 -> 5% 할인
 * 5. BASIC 회원 + 06:00 ~ 09:00 예약 -> 5% 할인
 * 6. BASIC 회원 + 오전 6시 예약 -> 5% 할인
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

    @Test
    @DisplayName("SILVER 회원이 10시 30분에 예약하면 할인율 10%")
    void givenSiverMember_whenReserveAt10AM30MIN_thenApply10PercentDiscount() {
        LocalTime reservationTime = LocalTime.of(10, 30);
        int price = 10_000;

        DiscountCalculator cal = new DiscountCalculator();
        int discounted = cal.calculateDiscount(price, Membership.SILVER, reservationTime);
        int expected = (int) Math.round(price * 0.9);
        assertEquals(expected, discounted);
    }

    @Test
    @DisplayName("Gold 회원이 10시 30분에 예약하면 할인율 20%")
    void givenGoldMember_whenReserveAt10AM30MIN_thenApply20PercentDiscount() {
        LocalTime reservationTime = LocalTime.of(10, 30);
        int price = 10_000;

        DiscountCalculator cal = new DiscountCalculator();
        int discounted = cal.calculateDiscount(price, Membership.GOLD, reservationTime);
        int expected = (int) Math.round(price * 0.8);
        assertEquals(expected, discounted);
    }

    @Test
    @DisplayName("BASIC 회원이 오전 6시 30분에 예약하면 할인율 5%")
    void givenBasicMember_whenReserveAt6AM30MIN_thenApply5PercentDiscount() {
        LocalTime reservationTime = LocalTime.of(6, 30);
        int price = 10_000;

        DiscountCalculator cal = new DiscountCalculator();
        int discounted = cal.calculateDiscount(price, Membership.BASIC, reservationTime);
        int expected = (int) Math.round(price * 0.95);
        assertEquals(expected, discounted);
    }

    @DisplayName("BASIC 회원이 06시~09시 사이에 예약하면 5% 할인 적용")
    @ParameterizedTest
    @ValueSource(ints = {7, 8})
    void givenBasicMember_whenReserveAtMorningHours_thenApply5PercentDiscount(int hour) {
        // given
        int price = 10000;
        Membership membership = Membership.BASIC;
        LocalTime time = LocalTime.of(hour, 0);

        // when
        DiscountCalculator cal = new DiscountCalculator();
        int discounted = cal.calculateDiscount(price, membership, time);
        int expected = (int) Math.round(price * 0.95);
        // then
        assertEquals(expected, discounted);
    }

    @Test
    @DisplayName("BASIC 회원이 오전 6시에 예약하면 할인율 5%")
    void  givenBasicMember_whenReserveAt6AM_thenApply5PercentDiscount(){
        LocalTime reservationTime = LocalTime.of(6, 00);
        int price = 10_000;

        DiscountCalculator cal = new DiscountCalculator();
        int discounted = cal.calculateDiscount(price, Membership.BASIC, reservationTime);
        int expected = (int) Math.round(price * 0.95);
        assertEquals(expected, discounted);
    }

}
