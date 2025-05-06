package com.sjtu.se2321.backend.dto;

public class BookReqParam {
    private String tag;
    private String keyword;
    private Integer pageIndex;
    private Integer pageSize;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BookRequest [tag=" + tag + ", keyword=" + keyword + ", pageIndex=" + pageIndex + ", pageSize="
                + pageSize + "]";
    }

}
