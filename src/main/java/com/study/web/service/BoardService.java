package com.study.web.service;

import com.study.web.dao.BoardDAO;
import com.study.web.dto.BoardDTO;
import com.study.web.dto.PageDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BoardService {
    Logger logger = Logger.getLogger(BoardService.class.getName());

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
     * 게시물 총 개수 (+검색조건)
     * @param boardDTO
     * @return
     */
    public int cntBoardList(BoardDTO boardDTO) {
        int cnt = 0;
        BoardDAO boardDAO = new BoardDAO();

        try {
            cnt = boardDAO.selectCnt(boardDTO);
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
    public List<BoardDTO> getBoardList(BoardDTO boardDTO, PageDTO pageDTO) {
        List<BoardDTO> boardDTOList = new ArrayList<>();
        BoardDAO boardDAO = new BoardDAO();

        try {
            boardDTOList = boardDAO.selectAll(boardDTO, pageDTO);
        } catch (SQLException e) {
            logger.info("SQLException: " + e.getMessage());
        }
        return boardDTOList;
    }
}
