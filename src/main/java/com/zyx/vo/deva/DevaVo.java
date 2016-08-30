package com.zyx.vo.deva;

import java.io.Serializable;

/**
 * Created by MrDeng on 2016/8/26.
 */
public class DevaVo implements Serializable{
    private Integer id;

    /**
     * 模块类型（1、活动.........）
     * //  对面模块类型 1、活动，2,直播，3,圈子，4动态，5用户，6帖子
     */
    private Integer model;
    /**
     * 对应模块首推数据ID
     */
    private Integer modelId;



    private Long createTime;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 首推排序
     */
    private Integer sequence;

    private Integer state;//激活1，未激活0

    private Integer area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}
