package com.zyx.model;

import javax.persistence.*;

@Table(name = "circleItem")
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
}