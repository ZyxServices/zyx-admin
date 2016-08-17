package com.zyx.model;

import javax.persistence.*;

@Table(name = "devaluation")
public class Devaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 模块类型（1、活动.........）
     * //  对面模块类型 1、活动，2,直播，3,圈子，4动态，5用户，6帖子
     */
    private Integer model;

    /**
     * 对应模块首推数据ID
     */
    @Column(name = "model_id")
    private Integer devaluationId;

    @Column(name = "create_time")
    private Long createTime;

    /**
     * 图片地址
     */
    @Column(name = "image_url")
    private String image;

    /**
     * 首推排序
     */
    private Integer sequence;

    @Column(name = "state")
    private Integer activation;//激活0，未激活1

    private Integer area;
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
    public Integer getModel() {
        return model;
    }

    /**
     * 设置模块类型（1、活动.........）
     *
     * @param model 模块类型（1、活动.........）
     */
    public void setModel(Integer model) {
        this.model = model;
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

    /**
     * @return activation
     */
    public Integer getActivation() {
        return activation;
    }

    /**
     * @param activation
     */
    public void setActivation(Integer activation) {
        this.activation = activation;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}