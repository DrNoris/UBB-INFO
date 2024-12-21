package com.example.scheletseminar9.util.paging;

public class Pageable {
    private final int pageSize;
    private final int pageNumber;

    public Pageable(int pageNumber, int pageSize) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
