# Wirebarley 코딩 테스트
## SpringBoot를 이용한 환율 계산 웹 기능 구현

### 실행
- src/main/java/com/codingtest/wirebarly/WirebarleyApplication main method 실행

### 사용기술
- SpringBoot 2.4.3
- Thymeleaf
- JUnit 5
- Lombok, httpcomponents 등

#### 기타
- 수취국가를 선택하는 Select Box가 변경될 때마다 서버에서 새로운 환율 정보를 얻도록 구현
- Submit 버튼을 누를 때에는 기존에 가지고 있던 환율을 활용하여 수취금액을 계산하여 보여주도록 구현
