package com.study.web.service;

import com.study.util.CommonUtil;
import com.study.web.dao.BoardDAO;
import com.study.web.dto.BoardDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BoardService {
    Logger logger = Logger.getLogger(BoardService.class.getName());

    /**
     * 게시판 목록 검색조건 반환
     * @param req
     * @return
     */
    public BoardDTO getBoardListSrchData(HttpServletRequest req) {
        BoardDTO boardDTO = new BoardDTO();

        if (CommonUtil.isEmpty(req.getParameter("srchRegDateStart"))
                && CommonUtil.isEmpty(req.getParameter("srchRegDateEnd"))) {
            boardDTO.setSrchRegDateEnd(CommonUtil.localNowDate());
            boardDTO.setSrchRegDateStart(CommonUtil.localDatePlusY(boardDTO.getSrchRegDateEnd(), -1));
        } else {
            boardDTO.setSrchRegDateStart(req.getParameter("srchRegDateStart"));
            boardDTO.setSrchRegDateEnd(req.getParameter("srchRegDateEnd"));
        }
        boardDTO.setSrchCategory(CommonUtil.nvl(req.getParameter("srchCategory"), ""));
        boardDTO.setSrchWord(CommonUtil.nvl(req.getParameter("srchWord"), ""));

        return boardDTO;
    }

    /**
     * 게시물 총 개수
     * @return
     */
    public int cntBoardList() {
        int cnt = 0;
        BoardDAO boardDAO = new BoardDAO();

        try {
            cnt = boardDAO.selectCnt();
        } catch (SQLException e) {
            logger.info("SQLException : " + e.getMessage());
        }
        return cnt;
    }

    /**
     * 게시판 목록 조회
     * @param boardDTO
     * @return
     */
    public List<BoardDTO> getBoardList(BoardDTO boardDTO) {
        List<BoardDTO> boardDTOList = new ArrayList<>();
        BoardDAO boardDAO = new BoardDAO();

        try {
            boardDTOList = boardDAO.selectAll(boardDTO);
        } catch (SQLException e) {
            logger.info("SQLException: " + e.getMessage());
        }
        return boardDTOList;
    }
}
