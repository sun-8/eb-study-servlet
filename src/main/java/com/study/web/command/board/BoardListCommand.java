package com.study.web.command.board;

import com.study.web.command.Command;
import com.study.web.dto.BoardDTO;
import com.study.web.dto.CategoryDTO;
import com.study.web.dto.PageDTO;
import com.study.web.service.BoardService;
import com.study.web.service.CategoryService;
import com.study.web.service.PageService;
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
        PageService pageService = new PageService();
        BoardService boardService = new BoardService();
        CategoryService categoryService = new CategoryService();

        // 검색조건
        BoardDTO boardSrchData = boardService.getBoardListSrchData(req);
        // category 목록
        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList();
        // 페이징
        PageDTO pageDTO = new PageDTO() ;
        pageDTO.setNowPage(pageService.getNowPage(req));
        pageDTO.setStartIdx(pageDTO.getNowPage());
        pageDTO.setDataCnt(boardService.cntBoardList());
        pageDTO.setSrchDataCnt(boardService.cntBoardList(boardSrchData));
        pageDTO.setEndPage(pageDTO.getSrchDataCnt());
        // 게시판 목록
        List<BoardDTO> boardDTOList = boardService.getBoardList(boardSrchData, pageDTO);

        req.setAttribute("boardSrchData", boardSrchData);
        req.setAttribute("categoryDTOList", categoryDTOList);
        req.setAttribute("pageDTO", pageDTO);
        req.setAttribute("boardDTOList", boardDTOList);

        req.getRequestDispatcher("/boardList.jsp").forward(req, resp);
    }
}
