# 매장 테이블 예약 서비스 구현
식당이나 점포를 이용하기 전에 미리 예약을 하여 편하게 식당/점포를 이용할 수 있는 서비스 개발

---
## ✅ 구현 내용
(구현 중 수정 예정)

- 매장 파트너(관리자)
  - 파트너 회원 가입
  - 매장 등록(매장 이름, 매장 위치, 매장 설명 등)
- 사용자
  - 사용자 회원 가입
  - 매장 조회
  - 매장 예약
  - 키오스크를 통해 방문 확인
    - 예약 시간 10분전까지 입장(10분이 넘어가면 허용x)
  - 매장 이용 후 리뷰 작성
    - 리뷰 수정(작성자)
    - 리뷰 삭제(작성자, 관리자)

---
## 🛠️ 개발 환경
- JDK 17
- SpringBoot 3.2.4 (Gradle)
- H2database
- MariaDB
- Library
    - spring-boot-starter
      - web
      - security
      - data-jdbc
      - data-jpa
      - redis(예정)
  - Lombok
---
## 🔀 ERD
(구현 중 수정 예정)
![reservation_v01 drawio](https://github.com/dev-sam32/practice-reservation-project/assets/90596545/43f2d83b-0ad7-4818-b66f-f3ba93ee3b7a)


---
## ▶️ 기능 시현
(구현 후 추가 예정)

---
