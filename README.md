## HikariCP와 JDBC를 활용한 DB관리 프로그램 제작 프로젝트
# <포켓몬스터 카드 경매 프로그램: 포켓 옥션>💸
&nbsp; 
![5356574d8814be54](https://github.com/junsoo186/card_auction_project/assets/169410809/d8e958ce-e7fe-4056-a242-c25d4b8ef48b)
&nbsp;
### 목차
1. 프로젝트 개요
2. 구성원 및 맡은 역할
3. 서비스 환경
4. 사용 라이브러리 및 프로토콜
5. 시퀀스 다이어그램
6. 주요 기능
7. ERD 다이어그램
&nbsp; 
## 1️⃣ 프로젝트 개요
* HikariCP, JDBC를 활용한 DB 관리 프로그램 제작
* 최근 아이들에게 인기 많은 '포켓몬스터 카드'라는 아이템을 활용, MySQL과 Java로 회원관리 기능 및 경매 기능 구현
&nbsp; 
## 2️⃣ 구성원 및 맡은 역할
|이름|역할|맡은 역할|
|------|---|---|
|엄송현🗺|팀장| Java Swing GUI 구현, 보고서-시퀀스 다이어그램 작성 및 리더 |
|박준수☠|팀원| DAO, 타이머 기능 구현 및 프로그램 전체 UI 디자인 |
|석지웅👨‍💻|팀원| MySQL 테이블, DAO&DTO, 검색 기능 및 페이징 기능 구현 |
|송원석😉|팀원| Socket을 사용한 회원정보 관리, 프로토콜, 경매가격 시스템 구현|
## 3️⃣ 서비스 환경
|유형|구분|서비스 배포 환경|
|------|---|---|
|SW|OS| Windows10 |
||Browser| Chrome 121.0.6167.161 |
||Tool| Spring Tool Suite |
||BackEnd| Java 17 7 MySQL 8.0.26 |
||Version/Issue 관리| GitHub & GitBash |
||Communication| Discord & Notion|
## 4️⃣ 사용 라이브러리 및 프로토콜
### (1) 사용 라이브러리
|라이브러리명|버전명|용도|
|------|---|---|
|HikariCP|HikariCP 5.1.0| Connection Pool을 통한 효율적인 DB 연결 관리 |
|Lombok|Lombok| 간편한 생성자 및 메서드 사용 |
|MySQL Connector Java|MySQL Connector Java 8.0.21| MySQL 테이블, DAO&DTO, 검색 기능 및 페이징 기능 구현 |
|SLF4J|SLF4J API 2.0.0| 다양한 로깅 프레임워크에 대한 추상화 및 연결 |
|SLF4J|SLF4J simple 2.0.0| SLF4J 인터페이스를 로깅 구현체와 연결 |
### (2) 사용 프로토콜
|기능명|프로토콜명|용도|
|------|---|---|
|회원가입|/sendDB/| User 데이터 생성 및 카드 랜덤 5개 증정 |
||/login/| User 데이터 조회 후 success/wrong 출력 |
|경매|/bid/| 상품 현재 가격 변경 (최고가) |
||/cash/| 충전 후 User 보유 포인트 동기화|
||BackEnd| 경매에 상품 판매 |
## 5️⃣ 시퀀스 다이어그램
### (1) 로그인 시퀀스
### (2) 회원가입 시퀀스
### (3) 경매 시퀀스
