package com.zyx.model;

import javax.persistence.*;

@Table(name = "SYS_GL_QX")
public class SysGlQx {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "GL_ID")
    private String glId;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "FX_QX")
    private Integer fxQx;

    @Column(name = "FW_QX")
    private Integer fwQx;

    @Column(name = "QX1")
    private Integer qx1;

    @Column(name = "QX2")
    private Integer qx2;

    @Column(name = "QX3")
    private Integer qx3;

    @Column(name = "QX4")
    private Integer qx4;

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
     * @return GL_ID
     */
    public String getGlId() {
        return glId;
    }

    /**
     * @param glId
     */
    public void setGlId(String glId) {
        this.glId = glId;
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
     * @return FX_QX
     */
    public Integer getFxQx() {
        return fxQx;
    }

    /**
     * @param fxQx
     */
    public void setFxQx(Integer fxQx) {
        this.fxQx = fxQx;
    }

    /**
     * @return FW_QX
     */
    public Integer getFwQx() {
        return fwQx;
    }

    /**
     * @param fwQx
     */
    public void setFwQx(Integer fwQx) {
        this.fwQx = fwQx;
    }

    /**
     * @return QX1
     */
    public Integer getQx1() {
        return qx1;
    }

    /**
     * @param qx1
     */
    public void setQx1(Integer qx1) {
        this.qx1 = qx1;
    }

    /**
     * @return QX2
     */
    public Integer getQx2() {
        return qx2;
    }

    /**
     * @param qx2
     */
    public void setQx2(Integer qx2) {
        this.qx2 = qx2;
    }

    /**
     * @return QX3
     */
    public Integer getQx3() {
        return qx3;
    }

    /**
     * @param qx3
     */
    public void setQx3(Integer qx3) {
        this.qx3 = qx3;
    }

    /**
     * @return QX4
     */
    public Integer getQx4() {
        return qx4;
    }

    /**
     * @param qx4
     */
    public void setQx4(Integer qx4) {
        this.qx4 = qx4;
    }
}