package com.zyx.model;

import javax.persistence.*;

@Table(name = "SYS_USER_QX")
public class SysUserQx {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "U_ID")
    private String uId;

    @Column(name = "C1")
    private Integer c1;

    @Column(name = "C2")
    private Integer c2;

    @Column(name = "C3")
    private Integer c3;

    @Column(name = "C4")
    private Integer c4;

    @Column(name = "Q1")
    private Integer q1;

    @Column(name = "Q2")
    private Integer q2;

    @Column(name = "Q3")
    private Integer q3;

    @Column(name = "Q4")
    private Integer q4;

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
     * @return U_ID
     */
    public String getuId() {
        return uId;
    }

    /**
     * @param uId
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * @return C1
     */
    public Integer getC1() {
        return c1;
    }

    /**
     * @param c1
     */
    public void setC1(Integer c1) {
        this.c1 = c1;
    }

    /**
     * @return C2
     */
    public Integer getC2() {
        return c2;
    }

    /**
     * @param c2
     */
    public void setC2(Integer c2) {
        this.c2 = c2;
    }

    /**
     * @return C3
     */
    public Integer getC3() {
        return c3;
    }

    /**
     * @param c3
     */
    public void setC3(Integer c3) {
        this.c3 = c3;
    }

    /**
     * @return C4
     */
    public Integer getC4() {
        return c4;
    }

    /**
     * @param c4
     */
    public void setC4(Integer c4) {
        this.c4 = c4;
    }

    /**
     * @return Q1
     */
    public Integer getQ1() {
        return q1;
    }

    /**
     * @param q1
     */
    public void setQ1(Integer q1) {
        this.q1 = q1;
    }

    /**
     * @return Q2
     */
    public Integer getQ2() {
        return q2;
    }

    /**
     * @param q2
     */
    public void setQ2(Integer q2) {
        this.q2 = q2;
    }

    /**
     * @return Q3
     */
    public Integer getQ3() {
        return q3;
    }

    /**
     * @param q3
     */
    public void setQ3(Integer q3) {
        this.q3 = q3;
    }

    /**
     * @return Q4
     */
    public Integer getQ4() {
        return q4;
    }

    /**
     * @param q4
     */
    public void setQ4(Integer q4) {
        this.q4 = q4;
    }
}