package com.aibany.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by mac on 2017/6/25.
 * 封装分页
 */
public class PageBean<T> {

    private int currentPage;
    private int pageSize;
    private DetachedCriteria detachedCriteria; //查询条件

    private long total;
    private List<T> rows;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
