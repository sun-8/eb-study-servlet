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
@WebServlet("/board/free/*")
public class BoardController extends HttpServlet {
    Logger logger = Logger.getLogger(BoardController.class.getName());

    Map<String, Command> commands = new HashMap<String, Command>();

    @Override
    public void init() {
        commands.put("GET-list", new BoardListCommand());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = getCommand(req);
        if (command != null) {
            command.execute(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * 요청 처리하여 command 객체 반환
     * @param req
     * @return
     */
    protected Command getCommand(HttpServletRequest req) {
        String path = req.getRequestURI();
        String method = req.getMethod();
        String commandKey = method + "-" + path.substring(path.lastIndexOf("/") + 1);
        return commands.get(commandKey);
    }
}
