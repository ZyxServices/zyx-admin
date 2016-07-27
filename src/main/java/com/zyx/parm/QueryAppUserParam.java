package com.zyx.parm;

/**
 * Created by wms on 2016/7/19.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title QueryAppUserParam.java
 */
public class QueryAppUserParam extends QueryParam {

    private String searchText;

    private Integer authenticate;

    private Boolean mask;

    private Boolean del;

    private Integer official = 0;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Integer getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Integer authenticate) {
        this.authenticate = authenticate;
    }

    public Boolean getMask() {
        return mask;
    }

    public void setMask(Boolean mask) {
        this.mask = mask;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Integer getOfficial() {
        return official;
    }

    public void setOfficial(Integer official) {
        this.official = official;
    }
}
