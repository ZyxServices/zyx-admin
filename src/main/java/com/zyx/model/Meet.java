package com.zyx.model;

import javax.persistence.*;

@Table(name = "meet")
public class Meet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "circle_id")
    private Integer circleId;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "create_time")
    private Long createTime;

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
     * @return circle_id
     */
    public Integer getCircleId() {
        return circleId;
    }

    /**
     * @param circleId
     */
    public void setCircleId(Integer circleId) {
        this.circleId = circleId;
    }

    /**
     * @return account_id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}