# 🧪 TDD Playground

이 저장소는 다양한 **Test-Driven Development (TDD)** 실습 예제들을 모아두는 공간입니다.  
각 예제는 요구사항 → 테스트 작성(Red) → 구현(Green) → 리팩터링(Refactor)의 과정을 거쳐 작성되었습니다.

예제 목록:

- [할인 정책 계산기](#할인-정책-계산기)

---
### ⚙️ 기술 스택
- **Language**: Java 21
- **Framework**: Spring Boot 3.x
- **Build Tool**: Gradle (Kotlin DSL)
- **Test**: JUnit 5, AssertJ

---

## 📌 할인 정책 계산기

### 요구사항 (Specification)
- **회원 등급별 할인**
    - GOLD 회원 → 20% 할인
    - SILVER 회원 → 10% 할인
    - BASIC 회원 → 할인 없음

- **시간대별 추가 할인**
    - 조조(06:00 ~ 09:00) 예약 시 추가 5% 할인
    - 야간(21:00 ~ 23:00) 예약 시 추가 5% 할인

- **할인 중첩 규칙**
    - 회원 할인 + 시간대 할인을 모두 적용할 수 있음
    - 예: GOLD 회원 + 조조 시간대 → 25% 할인

- **최대 할인 한도**
    - 전체 할인율은 최대 30%를 넘지 않음
---

