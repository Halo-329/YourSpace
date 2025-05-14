# 🛒 Shop Spring Boot Project

## 📦 프로젝트 소개
Spring Boot 기반으로 제작한 쇼핑몰 웹 애플리케이션입니다.  
상품 목록, 장바구니, 주문 기능 등을 구현하며 Spring 생태계를 학습하고 활용하기 위한 프로젝트입니다.

## 🛠️ 사용 기술
| 분류        | 기술 스택                      |
|-------------|-------------------------------|
| Language    | Java 17                       |
| Framework   | Spring Boot 3.4.5             |
| Template    | Thymeleaf                     |
| Database    | MySQL                         |
| ORM         | Spring Data JPA               |
| Build Tool  | Gradle                        |
| 기타        | Lombok, Spring Boot DevTools, JUnit 5 |

## 📂 프로젝트 구조
```plaintext
src
 └── main
     ├── java
     │    └── com.apple.shop
     │         ├── controller    # 웹 요청 처리
     │         ├── service       # 비즈니스 로직
     │         ├── repository    # DB 접근 (JPA)
     │         └── domain        # 엔티티 클래스
     └── resources
          ├── static            # 정적 파일 (CSS, JS, 이미지)
          ├── templates         # Thymeleaf 템플릿 파일
          └── application.properties # 환경 설정 파일
