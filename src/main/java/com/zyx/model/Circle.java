package com.zyx.model;

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

    private Integer state;

    @Column(name = "circle_master")
    private String circleMaster;

    private Integer type;

    @Column(name = "circle_master_id")
    private Integer circleMasterId;

    @Column(name = "head_img_url")
    private String headImgUrl;

    private Integer top;

    private Integer hot;

    private String details;

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
     * @return circle_master
     */
    public String getCircleMaster() {
        return circleMaster;
    }

    /**
     * @param circleMaster
     */
    public void setCircleMaster(String circleMaster) {
        this.circleMaster = circleMaster;
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
}