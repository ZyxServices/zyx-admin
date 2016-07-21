package com.zyx.model;

import javax.persistence.*;

@Table(name = "myconcern")
public class MyConcern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "concern_id")
    private Integer concernId;

    @Column(name = "concern_type")
    private Integer concernType;

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
     * @return concern_id
     */
    public Integer getConcernId() {
        return concernId;
    }

    /**
     * @param concernId
     */
    public void setConcernId(Integer concernId) {
        this.concernId = concernId;
    }

    /**
     * @return concern_type
     */
    public Integer getConcernType() {
        return concernType;
    }

    /**
     * @param concernType
     */
    public void setConcernType(Integer concernType) {
        this.concernType = concernType;
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