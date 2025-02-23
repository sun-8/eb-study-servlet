package com.study.web.dao;

import com.study.util.CommonUtil;
import com.study.util.JdbcUtil;
import com.study.web.dto.BoardDTO;
import com.study.web.dto.PageDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BoardDAO {
    Logger logger = Logger.getLogger(BoardDAO.class.getName());

    /**
     * 게시물 총 개수
     * @return
     * @throws SQLException
     */
    public int selectCnt() throws SQLException {
        int cnt = 0;
        JdbcUtil jdbcUtil = new JdbcUtil();

        try(Connection conn = jdbcUtil.getConnection();
            Statement stmt = conn.createStatement();) {
            String sql = "SELECT COUNT(*) FROM BOARD";

            try(ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    cnt = rs.getInt(1);
                }
            }
        }
        return cnt;
    }

    /**
     * 게시물 총 개수 (+검색조건)
     * @return
     * @throws SQLException
     */
    public int selectCnt(BoardDTO boardDTO) throws SQLException {
        int cnt = 0;
        JdbcUtil jdbcUtil = new JdbcUtil();

        try(Connection conn = jdbcUtil.getConnection();
            Statement stmt = conn.createStatement();) {

            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM BOARD WHERE 1=1 ");
            if (!CommonUtil.isEmpty(boardDTO.getSrchRegDateStart())) {
                sql.append("AND DATE_FORMAT(REG_DTTM, '%Y-%m-%d') >= '")
                        .append(boardDTO.getSrchRegDateStart()).append("' ");
            }
            if (!CommonUtil.isEmpty(boardDTO.getSrchRegDateEnd())) {
                sql.append("AND DATE_FORMAT(REG_DTTM, '%Y-%m-%d') <= '")
                        .append(boardDTO.getSrchRegDateEnd()).append("' ");
            }
            if (!CommonUtil.isEmpty(boardDTO.getSrchCategory())) {
                sql.append("AND CATEGORY_ID = '")
                        .append(boardDTO.getSrchCategory()).append("' ");
            }
            if (!CommonUtil.isEmpty(boardDTO.getSrchWord())) {
                sql.append("AND CONCAT(IFNULL(USER_NAME, ''), IFNULL(TITLE, ''), IFNULL(CONTENTS, '')) LIKE CONCAT('%', TRIM('")
                        .append(boardDTO.getSrchWord()).append("'), '%') ");
            }
            try(ResultSet rs = stmt.executeQuery(sql.toString())) {
                if (rs.next()) {
                    cnt = rs.getInt(1);
                }
            }
        }
        return cnt;
    }

    /**
     * 게시판 목록 조회
     * @param boardDTO
     * @return
     * @throws SQLException
     */
    public List<BoardDTO> selectAll(BoardDTO boardDTO, PageDTO pageDTO) throws SQLException {
        JdbcUtil jdbcUtil = new JdbcUtil();
        List<BoardDTO> boardDTOList = new ArrayList<>();

        try(Connection conn = jdbcUtil.getConnection();
            Statement stmt = conn.createStatement();) {
            StringBuilder sql = new StringBuilder("SELECT " +
                    "ID, " +
                    "CATEGORY_ID, " +
                    "(SELECT NAME FROM CATEGORY WHERE ID = CATEGORY_ID) AS CATEGORY_NAME, " +
                    "USER_NAME, " +
                    "PASSWORD, " +
                    "TITLE, " +
                    "CONTENTS, " +
                    "VIEW, " +
                    "FILE_1, " +
                    "FILE_2, " +
                    "FILE_3, " +
                    "REG_DTTM, " +
                    "MOD_DTTM " +
                    "FROM BOARD " +
                    "WHERE 1=1 ");
            if (!CommonUtil.isEmpty(boardDTO.getSrchRegDateStart())) {
                sql.append("AND DATE_FORMAT(REG_DTTM, '%Y-%m-%d') >= '")
                        .append(boardDTO.getSrchRegDateStart()).append("' ");
            }
            if (!CommonUtil.isEmpty(boardDTO.getSrchRegDateEnd())) {
                sql.append("AND DATE_FORMAT(REG_DTTM, '%Y-%m-%d') <= '")
                        .append(boardDTO.getSrchRegDateEnd()).append("' ");
            }
            if (!CommonUtil.isEmpty(boardDTO.getSrchCategory())) {
                sql.append("AND CATEGORY_ID = '")
                        .append(boardDTO.getSrchCategory()).append("' ");
            }
            if (!CommonUtil.isEmpty(boardDTO.getSrchWord())) {
                sql.append("AND CONCAT(IFNULL(USER_NAME, ''), IFNULL(TITLE, ''), IFNULL(CONTENTS, '')) LIKE CONCAT('%', TRIM('")
                        .append(boardDTO.getSrchWord()).append("'), '%') ");
            }
            sql.append("ORDER BY ID DESC ").
                    append("LIMIT ").append(pageDTO.getStartIdx()).append(", ").append(pageDTO.getPageSize());

            try (ResultSet rs = stmt.executeQuery(sql.toString())) {
                while(rs.next()) {
                    BoardDTO board = new BoardDTO();
                    board.setId(rs.getInt("ID"));
                    board.setCategoryId(rs.getString("CATEGORY_ID"));
                    board.setCategoryName(rs.getString("CATEGORY_NAME"));
                    board.setUserName(rs.getString("USER_NAME"));
                    board.setPassword(rs.getString("PASSWORD"));
                    board.setTitle(rs.getString("TITLE"));
                    board.setContents(rs.getString("CONTENTS"));
                    board.setView(rs.getInt("VIEW"));
                    board.setFile1(rs.getString("FILE_1"));
                    board.setFile2(rs.getString("FILE_2"));
                    board.setFile3(rs.getString("FILE_3"));
                    board.setRegDttm(rs.getString("REG_DTTM"));
                    board.setModDttm(rs.getString("MOD_DTTM"));
                    boardDTOList.add(board);
                }
            }
        }
        return boardDTOList;
    }
}
