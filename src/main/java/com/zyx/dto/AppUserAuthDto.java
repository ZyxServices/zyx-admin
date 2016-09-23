package com.zyx.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wms on 2016/7/26.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 */
public class AppUserAuthDto implements Serializable {

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "authname")
    private String authName;

    @Column(name = "authmob")
    private String authMob;

    @Column(name = "authidcard")
    private String authIDCard;

    @Column(name = "authfile")
    private String authFile;

    @Column(name = "authinfo")
    private String authInfo;

    @Column(name = "authfilework")
    private String authFileWork;

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

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthMob() {
        return authMob;
    }

    public void setAuthMob(String authMob) {
        this.authMob = authMob;
    }

    public String getAuthIDCard() {
        return authIDCard;
    }

    public void setAuthIDCard(String authIDCard) {
        this.authIDCard = authIDCard;
    }

    public String getAuthFile() {
        return authFile;
    }

    public void setAuthFile(String authFile) {
        this.authFile = authFile;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }

    public String getAuthFileWork() {
        return authFileWork;
    }

    public void setAuthFileWork(String authFileWork) {
        this.authFileWork = authFileWork;
    }
}
