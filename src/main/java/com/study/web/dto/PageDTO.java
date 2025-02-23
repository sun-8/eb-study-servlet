package com.study.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDTO {
    private int nowPage = 1;
    private int startIdx = 0;
    private int pageSize = 10;
    private int dataCnt = 0;
    private int srchDataCnt = 0;
    private int startPage = 1;
    private int endPage = 1;
    private int lookPageCnt = 3; // ex) 1 2 3 ... 10

    public void setStartIdx(int nowPage) {
        this.startIdx = (nowPage - 1) * this.pageSize;
    }

    public void setEndPage(int srchDataCnt) {
        this.endPage = (srchDataCnt % this.pageSize == 0) ? srchDataCnt / this.pageSize : srchDataCnt / this.pageSize + 1;
    }
}
