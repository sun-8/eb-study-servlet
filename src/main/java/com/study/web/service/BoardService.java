package com.study.web.service;

import com.study.util.CommonUtil;
import com.study.web.dto.BoardListDTO;
import jakarta.servlet.http.HttpServletRequest;

public class BoardService {

    /**
     * 게시판 목록 검색조건 반환
     * @param req
     * @return
     */
    public BoardListDTO getBoardListSrchData(HttpServletRequest req) {
        BoardListDTO boardListDTO = new BoardListDTO();

        if (CommonUtil.isEmpty(req.getParameter("srchRegDateStart"))
                && CommonUtil.isEmpty(req.getParameter("srchRegDateEnd"))) {
            boardListDTO.setSrchRegDateEnd(CommonUtil.localNowDate());
            boardListDTO.setSrchRegDateStart(CommonUtil.localDatePlusY(boardListDTO.getSrchRegDateEnd(), -1));
        } else {
            boardListDTO.setSrchRegDateStart(req.getParameter("srchRegDateStart"));
            boardListDTO.setSrchRegDateEnd(req.getParameter("srchRegDateEnd"));
        }
        boardListDTO.setSrchCategory(req.getParameter("srchCategory"));
        boardListDTO.setSrchWord(req.getParameter("srchWord"));

        return boardListDTO;
    }
}
