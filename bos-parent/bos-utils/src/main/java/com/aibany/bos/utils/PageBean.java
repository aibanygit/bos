package com.aibany.bos.utils;

import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(serialize = false)
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @JSONField(serialize = false)
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

    @JSONField(serialize = false)
    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    @JSONField(serialize = false)
    public int getOffset() {

        int offset = (this.getCurrentPage() - 1) * this.getPageSize();
        if (offset < 0) {
            offset = 0;
        }
        return offset;
    }
}
