package com.zyx.model;

import javax.persistence.*;

@Table(name = "circle_item")
public class CircleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "circle_id")
    private Integer circleId;

    @Column(name = "create_id")
    private Integer createId;

    private String title;

    private Integer hot;

    @Column(name = "create_time")
    private Long createTime;

    private Integer top;

    private String content;

    private Integer state;//帖子状态,0为正常可以访问，-1为删除，-2为屏蔽如有业务需求，可以再加入

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "base_content")
    private String baseContent;

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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBaseContent() {
        return baseContent;
    }

    public void setBaseContent(String baseContent) {
        this.baseContent = baseContent;
    }
}