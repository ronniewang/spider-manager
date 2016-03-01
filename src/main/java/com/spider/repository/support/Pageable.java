package com.spider.repository.support;

public class Pageable {

    private int pageSize;

    private int pageNum;

    private String sortField;

    private String direction;

    public int getPageSize() {

        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }

    public int getPageNum() {

        return pageNum;
    }

    public void setPageNum(int pageNum) {

        this.pageNum = pageNum;
    }

    public String getSortField() {

        return sortField;
    }

    public void setSortField(String sortField) {

        this.sortField = sortField;
    }

    public String getDirection() {

        return direction;
    }

    public void setDirection(String direction) {

        this.direction = direction;
    }
}
