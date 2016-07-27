package com.zyx.dto;

import javax.persistence.*;

/**
 * Created by wms on 2016/7/26.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserAuthDto.java
 */
public class AppUserAuthDto {

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "authinfo")
    private String authInfo;

    @Column(name = "authfile")
    private String authFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    public String getAuthFile() {
        return authFile;
    }

    public void setAuthFile(String authFile) {
        this.authFile = authFile;
    }
}
