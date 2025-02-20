package com.study.web.dao;

import com.study.util.JdbcUtil;
import com.study.web.dto.CategoryDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    /**
     * 카테고리 목록 조회
     * @return
     * @throws SQLException
     */
    public List<CategoryDTO> selectAll() throws SQLException {
        List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
        JdbcUtil jdbcUtil = new JdbcUtil();

        try(Connection conn = jdbcUtil.getConnection();
            Statement stmt = conn.createStatement()) {
            String sql = "SELECT ID, NAME FROM CATEGORY ORDER BY ID ASC";

            try(ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setId(rs.getString("ID"));
                    categoryDTO.setName(rs.getString("NAME"));
                    categoryDTOList.add(categoryDTO);
                }
            }
        }
        return categoryDTOList;
    }
}
