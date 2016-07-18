package com.zyx.model;

import javax.persistence.*;

@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double yue;

    private Double shares;

    /**
     * @return id
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
     * @return name
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
     * @return yue
     */
    public Double getYue() {
        return yue;
    }

    /**
     * @param yue
     */
    public void setYue(Double yue) {
        this.yue = yue;
    }

    /**
     * @return shares
     */
    public Double getShares() {
        return shares;
    }

    /**
     * @param shares
     */
    public void setShares(Double shares) {
        this.shares = shares;
    }
}