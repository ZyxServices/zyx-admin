package com.zyx.model;

import javax.persistence.*;

@Table(name = "concern")
public class Concern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private Integer type;

    @Column(name = "topic_content")
    private String topicContent;

    @Column(name = "topic_title")
    private String topicTitle;

    @Column(name = "topic_visible")
    private Integer topicVisible;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "video_url")
    private String videoUrl;

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
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * @return topic_content
     */
    public String getTopicContent() {
        return topicContent;
    }

    /**
     * @param topicContent
     */
    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    /**
     * @return topic_title
     */
    public String getTopicTitle() {
        return topicTitle;
    }

    /**
     * @param topicTitle
     */
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    /**
     * @return topic_visible
     */
    public Integer getTopicVisible() {
        return topicVisible;
    }

    /**
     * @param topicVisible
     */
    public void setTopicVisible(Integer topicVisible) {
        this.topicVisible = topicVisible;
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return video_url
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * @param videoUrl
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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