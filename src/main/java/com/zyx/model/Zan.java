package com.zyx.model;

import javax.persistence.*;

@Table(name = "zan")
public class Zan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "body_id")
    private Integer bodyId;

    @Column(name = "body_type")
    private Integer bodyType;//1：圈子，2：动态，3：活动，4直播，5帖子

    @Column(name = "account_id")
    private Integer accountId;

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
     * @return body_id
     */
    public Integer getBodyId() {
        return bodyId;
    }

    /**
     * @param bodyId
     */
    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    /**
     * @return body_type
     */
    public Integer getBodyType() {
        return bodyType;
    }

    /**
     * @param bodyType
     */
    public void setBodyType(Integer bodyType) {
        this.bodyType = bodyType;
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
}