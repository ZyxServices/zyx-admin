package com.zyx.parm;

import java.io.Serializable;

/**
 * Created by MrDeng on 2016/7/19.
 * 查询通用
 */
public abstract class QueryParam implements Serializable {

    private Integer pageNumber;

    private Integer pageSize;

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
}
