package com.zyx.parm;

import java.io.Serializable;

/**
 * Created by wms on 2016/7/19.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title QueryAppUserParam.java
 */
public class QueryAppUserParam implements Serializable {

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
