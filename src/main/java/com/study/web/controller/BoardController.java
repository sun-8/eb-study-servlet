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
        commands.put("list", new BoardListCommand());
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
