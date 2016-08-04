package com.zyx.dto;

import java.io.Serializable;

/**
 * Created by wms on 2016/7/27.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title SystemUserListDto.java
 */
public class SystemUserListDto implements Serializable {
    private Integer id;

    private String userId;

    private String username;

    private String password;

    private String name;

    private String roleId;

    private String bz;

    private SystemRoleListDto systemRoleListDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SystemRoleListDto getSystemRoleListDto() {
        return systemRoleListDto;
    }

    public void setSystemRoleListDto(SystemRoleListDto systemRoleListDto) {
        this.systemRoleListDto = systemRoleListDto;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
