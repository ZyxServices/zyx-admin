package com.zyx.parm;

import java.io.Serializable;

/**
 * Created by MrDeng on 2016/7/19.
 * 查询通用
 */
public abstract class QueryParam implements Serializable {

    private Integer pageNumber;

    private Integer pageSize;

    private String sortName;

    private String sortOrder;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
