package com.zyx.dto;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by wms on 2016/7/27.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserListDto.java
 */
public class AppUserListDto implements Serializable {

    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 地址
     */
    private String address;

    private Long birthday;

    /**
     * 1男 0女
     */
    private Integer sex;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 是否认证字段
     */
    private Integer authenticate;

    /**
     * 是否屏蔽
     */
    private Boolean mask;

    /**
     * 是否删除
     */
    private Boolean del;

    /**
     * 签名档
     */
    private String signature;

    private boolean deva;

    private AppUserAuthDto appUserAuthDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isDeva() {
        return deva;
    }

    public void setDeva(boolean deva) {
        this.deva = deva;
    }

    public AppUserAuthDto getAppUserAuthDto() {
        return appUserAuthDto;
    }

    public void setAppUserAuthDto(AppUserAuthDto appUserAuthDto) {
        this.appUserAuthDto = appUserAuthDto;
    }
}
