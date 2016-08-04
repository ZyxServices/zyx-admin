package com.zyx.parm.sys;

import com.zyx.parm.QueryParam;

/**
 * Created by wms on 2016/7/27.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title QuerySystemRoleParam.java
 */
public class QuerySystemRoleParam extends QueryParam {

    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
