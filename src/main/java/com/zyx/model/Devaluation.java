package com.zyx.model;

import javax.persistence.*;

@Table(name = "devaluation")
public class Devaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 模块类型（1、活动.........）
     */
    private Integer types;

    /**
     * 对应模块首推数据ID
     */
    @Column(name = "devaluation_id")
    private Integer devaluationId;

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
     * 获取模块类型（1、活动.........）
     *
     * @return types - 模块类型（1、活动.........）
     */
    public Integer getTypes() {
        return types;
    }

    /**
     * 设置模块类型（1、活动.........）
     *
     * @param types 模块类型（1、活动.........）
     */
    public void setTypes(Integer types) {
        this.types = types;
    }

    /**
     * 获取对应模块首推数据ID
     *
     * @return devaluation_id - 对应模块首推数据ID
     */
    public Integer getDevaluationId() {
        return devaluationId;
    }

    /**
     * 设置对应模块首推数据ID
     *
     * @param devaluationId 对应模块首推数据ID
     */
    public void setDevaluationId(Integer devaluationId) {
        this.devaluationId = devaluationId;
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