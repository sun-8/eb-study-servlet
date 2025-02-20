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
