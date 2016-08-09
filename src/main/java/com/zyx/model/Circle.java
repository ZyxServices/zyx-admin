package com.zyx.model;

import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;

@Table(name = "circle")
public class Circle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "create_id")
    private Integer createId;

    @Column(name = "create_time")
    private Long createTime;

    private Integer state;//状态,可以随业务逻辑增加，但必须在此注明类型说明。0为所有可见,-1为删除,-2为屏蔽

    @Column(name = "admin_ids")
    private String adminIds;//管理员ids

    private Integer type;//圈子类型,0未普通圈子，1位明星圈子

    @Column(name = "circle_master_id")
    private Integer circleMasterId;

    @Column(name = "head_img_url")
    private String headImgUrl;

    private Integer top;//1位置顶，其他都为不置顶,可以作为排序使用

    private Integer hot;//热度

    private String details;//简介

    private Integer tag;//标签

    private Integer circleType;//圈子类别

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return create_id
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * @param createId
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
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
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }


    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return circle_master_id
     */
    public Integer getCircleMasterId() {
        return circleMasterId;
    }

    /**
     * @param circleMasterId
     */
    public void setCircleMasterId(Integer circleMasterId) {
        this.circleMasterId = circleMasterId;
    }

    /**
     * @return head_img_url
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * @param headImgUrl
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /**
     * @return top
     */
    public Integer getTop() {
        return top;
    }

    /**
     * @param top
     */
    public void setTop(Integer top) {
        this.top = top;
    }

    /**
     * @return hot
     */
    public Integer getHot() {
        return hot;
    }

    /**
     * @param hot
     */
    public void setHot(Integer hot) {
        this.hot = hot;
    }

    /**
     * @return details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    public String getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(String adminIds) {
        this.adminIds = adminIds;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Integer getCircleType() {
        return circleType;
    }

    public void setCircleType(Integer circleType) {
        this.circleType = circleType;
    }
}