package com.sjtu.se2321.backend.dto;

import lombok.Data;

@Data
public class BookReqParam {
    private String tag;
    private String keyword;
    private Integer pageIndex;
    private Integer pageSize;
}
