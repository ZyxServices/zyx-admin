package com.zyx.parm.appUser;

import java.io.Serializable;

/**
 * Created by wms on 2016/7/26.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 */
public class AppUserCreateParam implements Serializable {

    private Integer appUserId;
    private String phone;
    private String password;
    private String avatar;
    private Integer sex;
    private String address;
    private String authName;
    private String authIDCard;
    private String authMob;
    private String authFile;
    private String authInfo;
    private String authFileWork;
    private String nickname;
    private Integer official;

    private Long modifyTime;

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getOfficial() {
        return official;
    }

    public void setOfficial(Integer official) {
        this.official = official;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthIDCard() {
        return authIDCard;
    }

    public void setAuthIDCard(String authIDCard) {
        this.authIDCard = authIDCard;
    }

    public String getAuthMob() {
        return authMob;
    }

    public void setAuthMob(String authMob) {
        this.authMob = authMob;
    }

    public String getAuthFileWork() {
        return authFileWork;
    }

    public void setAuthFileWork(String authFileWork) {
        this.authFileWork = authFileWork;
    }
}
