package com.zyx.model;

import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.*;

@Table(name = "concern")
public class Concern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private Integer type;//动态类型1为个人动态，2为活动动态，3为明星动态，4为圈子动态

    @Column(name = "topic_content")
    private String topicContent;

    @Column(name = "topic_title")
    private String topicTitle;

    @Column(name = "topic_visible")
    private Integer topicVisible;//0所有可见，1好友可见

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "create_time")
    private Long createTime;

    @Column
    private Integer state;//动态状态，-2未屏蔽，-1为删除,0为正常

    @Column(name = "from_id")
    private Integer fromId;//跟fromType对应

    @Column(name = "from_type")
    private Integer fromType;//1:直播，2活动，3帖子，该字段只用于我的关注模块中自动生成的动态查询


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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }


}