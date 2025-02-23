package com.study.web.service;

import com.study.util.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.logging.Logger;

public class PageService {
    Logger logger = Logger.getLogger(PageService.class.getName());

    /**
     * 현재 페이지 반환
     * @param request
     * @return
     */
    public int getNowPage(HttpServletRequest request) {
        int nowPage = 0;

        if (!CommonUtil.isEmpty(request.getParameter("nowPage"))) {
            nowPage = Integer.parseInt(request.getParameter("nowPage"));
        } else {
            nowPage = 1;
        }

        return nowPage;
    }
}
