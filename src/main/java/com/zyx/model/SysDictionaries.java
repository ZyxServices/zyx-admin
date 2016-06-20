package com.zyx.model;

import javax.persistence.*;

@Table(name = "SYS_DICTIONARIES")
public class SysDictionaries {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ZD_ID")
    private String zdId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIANMA")
    private String bianma;

    @Column(name = "ORDY_BY")
    private Integer ordyBy;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "JB")
    private Integer jb;

    @Column(name = "P_BM")
    private String pBm;

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
     * @return ZD_ID
     */
    public String getZdId() {
        return zdId;
    }

    /**
     * @param zdId
     */
    public void setZdId(String zdId) {
        this.zdId = zdId;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return BIANMA
     */
    public String getBianma() {
        return bianma;
    }

    /**
     * @param bianma
     */
    public void setBianma(String bianma) {
        this.bianma = bianma;
    }

    /**
     * @return ORDY_BY
     */
    public Integer getOrdyBy() {
        return ordyBy;
    }

    /**
     * @param ordyBy
     */
    public void setOrdyBy(Integer ordyBy) {
        this.ordyBy = ordyBy;
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
     * @return JB
     */
    public Integer getJb() {
        return jb;
    }

    /**
     * @param jb
     */
    public void setJb(Integer jb) {
        this.jb = jb;
    }

    /**
     * @return P_BM
     */
    public String getpBm() {
        return pBm;
    }

    /**
     * @param pBm
     */
    public void setpBm(String pBm) {
        this.pBm = pBm;
    }
}