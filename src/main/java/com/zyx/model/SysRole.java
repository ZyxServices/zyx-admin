package com.zyx.model;

import javax.persistence.*;

@Table(name = "SYS_ROLE")
public class SysRole {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "RIGHTS")
    private String rights;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "ADD_QX")
    private String addQx;

    @Column(name = "DEL_QX")
    private String delQx;

    @Column(name = "EDIT_QX")
    private String editQx;

    @Column(name = "CHA_QX")
    private String chaQx;

    @Column(name = "QX_ID")
    private String qxId;

    @Column(name = "ROLE_DESC")
    private String roleDesc;

    @Column(name = "MENU_PERM")
    private String menuPerm;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return ROLE_ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return ROLE_NAME
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return RIGHTS
     */
    public String getRights() {
        return rights;
    }

    /**
     * @param rights
     */
    public void setRights(String rights) {
        this.rights = rights;
    }

    /**
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * @return ADD_QX
     */
    public String getAddQx() {
        return addQx;
    }

    /**
     * @param addQx
     */
    public void setAddQx(String addQx) {
        this.addQx = addQx;
    }

    /**
     * @return DEL_QX
     */
    public String getDelQx() {
        return delQx;
    }

    /**
     * @param delQx
     */
    public void setDelQx(String delQx) {
        this.delQx = delQx;
    }

    /**
     * @return EDIT_QX
     */
    public String getEditQx() {
        return editQx;
    }

    /**
     * @param editQx
     */
    public void setEditQx(String editQx) {
        this.editQx = editQx;
    }

    /**
     * @return CHA_QX
     */
    public String getChaQx() {
        return chaQx;
    }

    /**
     * @param chaQx
     */
    public void setChaQx(String chaQx) {
        this.chaQx = chaQx;
    }

    /**
     * @return QX_ID
     */
    public String getQxId() {
        return qxId;
    }

    /**
     * @param qxId
     */
    public void setQxId(String qxId) {
        this.qxId = qxId;
    }

    /**
     * @return ROLE_DESC
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * @param roleDesc
     */
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