package com.zyx.parm.sys;

import java.io.Serializable;

/**
 * Created by wms on 2016/8/2.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title CreateSystemRoleParam.java
 */
public class CreateSystemRoleParam implements Serializable {

    private Integer id;

    private String roleId;

    private String roleName;

    private String roleDesc;

    private String menuPerm;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getMenuPerm() {
        return menuPerm;
    }

    public void setMenuPerm(String menuPerm) {
        this.menuPerm = menuPerm;
    }
}
