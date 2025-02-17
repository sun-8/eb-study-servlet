
# 사전 준비

## JDK 11 설치

## Docker Desktop 설치
https://www.docker.com/products/docker-desktop/

## Apache Tomcat 설치
https://tomcat.apache.org/download-10.cgi

## Docker Compose 실행 - MySql
``` 
cd help
docker-compose up -d
```
## Docker root password 찾기
```
docker exec [컨테이너명] env
```

# 프로젝트 소개
- JSP / Servlet 게시판을 Model-2 MVC 패턴을 적용하여 개발
- 게시판 목록 조회, 상세 조회, 등록, 수정, 삭제를 구현
- Java / JSP / Servlet / Mysql / JDBC / git

## JSP / Servlet 프로젝트를 하는 이유
#### 1. 웹 개발의 기초 이해
JSP와 Servlet은 Java 기반 웹 개발의 기본적인 기술.<br>
Spring이 있지만 결국 내부적으로 Servlet을 기반으로 동작.<br>
따라서 JSP와 Servlet에 대해 알면 Spring이 내부적으로 어떻게 동작하는지 더 쉽게 이해.

#### 2. HTTP 요청/응답 처리 개념 학습
Spring을 사용하면 어노테이션으로 쉽게 요청 처리 가능.<br>
하지만 그 전에 JSP / Servlet을 사용하면 HTTP 요청-응답 흐름을 깊이 이해.

#### 3. 기본적인 웹 애플리케이션 구조 익히기
MVC 패턴을 적용하는 방법<br>
DAO(Data Access Object) 패턴을 활용하여 DB와 연동<br>
위와 같은 기본적인 웹 애플리케이션 구조 습득

## 상세 기능
#### 게시판 목록 조회
- 검색조건 : 등록일 A to B (기본값 1년) / 카테고리 선택 하여 검색어 입력 (제목+작성자+내용)
- 게시글 건 수
- 목록 : 카테고리 / 첨부파일 유무표시 / 제목 / 조회수 / 등록,수정 일시
- 페이지네이션

#### 게시글 상세 조회
- 상세 : 제목 / 작성자 / 등록일시 / 수정일시 / 내용 / 첨부파일 다운로드
- 댓글 : 등록일 / 댓글 내용
- 게시글 삭제 : 비밀번호 입력

#### 게시글 등록
- 등록 : 카테고리 / 작성자 / 비밀번호 / 비밀번호 확인 / 제목 / 내용 / 첨부파일 1,2,3

#### 게시글 수정
- 수정 : 카테고리(고정) / 등록일시(고정) / 수정일시(고정) / 조회수(고정) / 작성자(고정) / 비밀번호 / 제목 / 내용 / 첨부파일 1,2,3
- 첨부파일 : ❎ 버튼으로 삭제 가능하지만 결론적으로 "취소"버튼을 클릭 시 삭제되지 않음. "저장"일 때만 삭제처리.

## DB 구조
![database_structure.png](database_structure.png)

## 이슈
- Tomcat version 수정 (02.17 해결)
  - tomcat 9는 Servlet 4.0을 지원하고 javax.* 패키지를 사용
  - tomcat 10은 Servlet 5.0을 지원하고 jakarta.* 패키지를 사용
  - build.gradle에 jakarta를 사용하고 있기 때문에 tomcat 10으로 변경
- [lombok 추가 안됨 (02.19 해결)](https://velog.io/@sun-8/jspservlet-lombok-추가-안됨)