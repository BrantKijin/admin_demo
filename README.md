# 배치 API 테스트 를 위한 소스 

## 1. 개발환경
* JAVA 21 , spring boot 3.2.x
* 터미널로 프로젝트 상위 폴더 infra 폴더로 진입 docker-compose up 명령어로 로컬 DB 설치
* DB는 postgresql 로 올린다.

| 데이터베이스 |  포트   | ID | 비밀번호 |
|---|------|---|---|
| talk | 5432 | postgres | aegisep1234@ |

* src > resource > sql > schema.sql 초기 데이터 생성 스키마 
* 타임리프로 기본 포트로 접속하면 아디 비번 , 창이 뜬다
## 2. API TEST
 

````
http://localhost:9999/login    로그인창  aegis ,a123456789
타임리프 테스트로 화면 미작업 상태 
````