
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
- [jstl 적용 안됨 (02.20 해결)](https://velog.io/@sun-8/jspservlet-jstl추가-안됨)

---
## Command Pattern
행동(behavioral) 패턴 중 하나.<br>
요청을 객체의 형태로 캡슐화하여 재이용하거나 취소할 수 있도록 요청에 필요한 정보를 저장하거나 로그에 남기는 패턴<br>
요청에 사용되는 각종 명령어들을 추상 클래스와 구체 클래스로 분리하여 단순화<br>
1. Command : 인터페이스. 요청을 수행하는 메서드를 정의
2. ConcreteCommand : 인터페이스를 구현한 클래스. 실제로 요청을 처리하는 작업
3. Receiver : 실제 작업을 수행하는 객체. 핵심 로직 구현
4. Invoker : 사용자의 요청을 Command 객체로 변환. 이 객체에 저장하고 실행하는 역할

- Command.java => Command
- BoardListCommand.java => ConcreteCommand + Receiver
- BoardController => Invoker

```java
// 1. Command : 
// 요청을 캡슐화.
package com.study.web.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Command 패턴을 위한 interface 정의
 */
public interface Command {

  /**
   * 요청을 캡슐화하여 요청 보내는이(Invoker)와 요청 받는이(Receiver)를 분리
   * @param req
   * @param resp
   */
  public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}

```
```java
// 2. ConcreteCommand : 
// 인터페이스를 구현. 실제로 요청을 처리. 여러 종류의 클래스.
// 3. Receiver : 
// execute() 안에서 실제 작업을 수행하는 객체. 핵심 로직 구현.
package com.study.web.command.board;

import com.study.web.command.Command;
import com.study.web.dto.BoardDTO;
import com.study.web.dto.CategoryDTO;
import com.study.web.service.BoardService;
import com.study.web.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class BoardListCommand implements Command {

  /**
   * 게시판 목록
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   */
  @Override
  public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BoardService boardService = new BoardService();
    CategoryService categoryService = new CategoryService();

    // 검색조건
    BoardDTO boardDTO = boardService.getBoardListSrchData(req);

    // category 목록
    List<CategoryDTO> categoryDTOList = categoryService.getAll();

    req.setAttribute("boardDTO", boardDTO);
    req.setAttribute("categoryDTOList", categoryDTOList);

    req.getRequestDispatcher("/boardList.jsp").forward(req, resp);
  }
}


```

```java
// Invoker :
// /board/* 주소 모두 해당 Servlet을 탐. 하나의 Servlet에 한 매핑주소를 써야하는 한계 극복.
// 사용자의 요청을 Command 객체로 변환. 이 객체에 저장하고 실행하는 역할
package com.study.web.controller;

import com.study.web.command.Command;
import com.study.web.command.board.BoardListCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * /board/에 속하는 주소 모두 이곳에서 시작
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
    Logger logger = Logger.getLogger(BoardController.class.getName());

    Map<String, Command> commands = new HashMap<String, Command>();

    @Override
    public void init() throws ServletException {
        commands.put("boardList", new BoardListCommand());
        commands.put("boardDetail", new BoardListCommand());
        commands.put("boardReg", new BoardListCommand());
        commands.put("boardMod", new BoardListCommand());
        commands.put("boardDel", new BoardListCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet");
        String path = req.getRequestURI();
        String commandKey = path.substring(path.lastIndexOf("/") + 1);
        Command command = commands.get(commandKey);
        if (command != null) {
            command.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doPost");
        String path = req.getRequestURI();
        String commandKey = path.substring(path.lastIndexOf("/") + 1);
        Command command = commands.get(commandKey);
        if (command != null) {
            command.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

```

---
## service - serviceImpl VS service
이 프로젝트에서 service와 serviceImpl을 사용할지, service만 사용할지를 고민해보았다.<br>
인터페이스와 이를 구현한 클래스의 구조는 캡슐화의 이점도 있지만 다향성에 큰 이점이 있다고 생각한다.<br>
List 인터페이스에는 ArrayList, LinkedList, ...가 있듯 하나의 인터페이스를 여러 클래스에 사용했을 때 더 빛을 바란다고 생각한다.<br>
그런데 이 service - serviceImpl은 1:1 구조이기 때문에 코드만 더 복잡하게 만드는 것이라고 생각하여 service만 사용하기로 했다.
