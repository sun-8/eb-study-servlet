package com.study.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListDTO {
    private int id;
    private String categoryId;
    private String userName;
    private String password;
    private String title;
    private String contents;
    private String file1;
    private String file2;
    private String file3;
    private String regDttm;
    private String modDttm;

    // 검색조건
    private String srchRegDateStart;
    private String srchRegDateEnd;
    private String srchCategory;
    private String srchWord;
}
