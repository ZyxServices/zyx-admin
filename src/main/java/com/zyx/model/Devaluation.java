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
     * 图片地址
     */
    @Column(name = "image")
    private String image;

    /**
     * 首推排序
     */
    @Column(name = "sequence")
    private Integer sequence;

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

    /**
     * 获取图片地址
     *
     * @return image - 图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片地址
     *
     * @param image 图片地址
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取首推排序
     *
     * @return sequence - 首推排序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置首推排序
     *
     * @param sequence 首推排序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}