package com.study.web.service;

import com.study.web.dao.CategoryDAO;
import com.study.web.dto.CategoryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CategoryService {
    Logger logger = Logger.getLogger(CategoryService.class.getName());

    /**
     * 카테고리 모든 데이터 목록 조회
     * @return
     */
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        CategoryDAO categoryDAO = new CategoryDAO();

        try {
            categoryDTOList = categoryDAO.selectAll();
        } catch (SQLException e) {
            logger.info("SQLException : " + e.getMessage());
        }
        return categoryDTOList;
    }
}
