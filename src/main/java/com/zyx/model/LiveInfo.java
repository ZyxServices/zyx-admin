package com.zyx.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_live")
public class LiveInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 1-公开，默认 2-我的粉丝可见 3-我关注人可见 4-包括2、3情况
     */
    private Integer auth;

    /**
     * 直播类型1-图文直播 2-视频直播
     */
    private Integer type;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Long start;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Long end;

    /**
     * 直播创建用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 直播标题
     */
    private String title;

    /**
     * 直播标签
     */
    private Integer lab;

    /**
     * 直播背景图片
     */
    @Column(name = "bgm_url")
    private String bgmUrl;

    /**
     * 视频直播地址
     */
    @Column(name = "vedio_url")
    private String vedioUrl;

    /**
     * 0-未开始，1-正在直播，2-直播结束
     */
    private Integer state;

    private Integer del;

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
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取1-公开，默认 2-我的粉丝可见 3-我关注人可见 4-包括2、3情况
     *
     * @return auth - 1-公开，默认 2-我的粉丝可见 3-我关注人可见 4-包括2、3情况
     */
    public Integer getAuth() {
        return auth;
    }

    /**
     * 设置1-公开，默认 2-我的粉丝可见 3-我关注人可见 4-包括2、3情况
     *
     * @param auth 1-公开，默认 2-我的粉丝可见 3-我关注人可见 4-包括2、3情况
     */
    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    /**
     * 获取直播类型1-图文直播 2-视频直播
     *
     * @return type - 直播类型1-图文直播 2-视频直播
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置直播类型1-图文直播 2-视频直播
     *
     * @param type 直播类型1-图文直播 2-视频直播
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取开始时间
     *
     * @return start - 开始时间
     */
    public Long getStart() {
        return start;
    }

    /**
     * 设置开始时间
     *
     * @param start 开始时间
     */
    public void setStart(Long start) {
        this.start = start;
    }

    /**
     * 获取结束时间
     *
     * @return end - 结束时间
     */
    public Long getEnd() {
        return end;
    }

    /**
     * 设置结束时间
     *
     * @param end 结束时间
     */
    public void setEnd(Long end) {
        this.end = end;
    }

    /**
     * 获取直播创建用户ID
     *
     * @return user_id - 直播创建用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置直播创建用户ID
     *
     * @param userId 直播创建用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取直播标题
     *
     * @return title - 直播标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置直播标题
     *
     * @param title 直播标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取直播标签
     *
     * @return lab - 直播标签
     */
    public Integer getLab() {
        return lab;
    }

    /**
     * 设置直播标签
     *
     * @param lab 直播标签
     */
    public void setLab(Integer lab) {
        this.lab = lab;
    }

    /**
     * 获取直播背景图片
     *
     * @return bgm_url - 直播背景图片
     */
    public String getBgmUrl() {
        return bgmUrl;
    }

    /**
     * 设置直播背景图片
     *
     * @param bgmUrl 直播背景图片
     */
    public void setBgmUrl(String bgmUrl) {
        this.bgmUrl = bgmUrl;
    }

    /**
     * 获取视频直播地址
     *
     * @return vedio_url - 视频直播地址
     */
    public String getVedioUrl() {
        return vedioUrl;
    }

    /**
     * 设置视频直播地址
     *
     * @param vedioUrl 视频直播地址
     */
    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    /**
     * 获取0-未开始，1-正在直播，2-直播结束
     *
     * @return state - 0-未开始，1-正在直播，2-直播结束
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0-未开始，1-正在直播，2-直播结束
     *
     * @param state 0-未开始，1-正在直播，2-直播结束
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return del
     */
    public Integer getDel() {
        return del;
    }

    /**
     * @param del
     */
    public void setDel(Integer del) {
        this.del = del;
    }
}