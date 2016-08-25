package com.zyx.model;

import javax.persistence.*;

@Table(name = "activity")
public class Activity {
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建者ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动图片
     */
    @Column(name = "img_urls")
    private String imgUrls;

    /**
     * 活动开始时间
     */
    @Column(name = "start_time")
    private Long startTime;

    /**
     * 活动结束时间
     */
    @Column(name = "end_time")
    private Long endTime;

    /**
     * 报名截止时间
     */
    @Column(name = "last_time")
    private Long lastTime;

    /**
     * 活动人数上线
     */
    @Column(name = "max_people")
    private Integer maxPeople;

    /**
     * 活动可见范围（0 所有可见，1我的可见，2我的关注）
     */
    private Integer visible;

    /**
     * 咨询电话
     */
    private String phone;

    /**
     * 活动价格
     */
    private Double price;

    /**
     * 活动分类（0  线上活动，  1线下活动）
     */
    private Integer type;

    /**
     * 线上活动跳转地址
     */
    @Column(name = "target_url")
    private String targetUrl;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 活动是否需要审核（0 不需要审核，1 需要审核）
     */
    private Integer examine;

    /**
     * 报名活动模版
     */
    @Column(name = "member_template")
    private String memberTemplate;

    @Column(name = "create_time")
    private Long createTime;

    private Integer del;

    private Integer mask;

    /**
     * 活动描述
     */
    @Column(name = "desc_content")
    private String descContent;

    /**
     * 获取表id
     *
     * @return id - 表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表id
     *
     * @param id 表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建者ID
     *
     * @return user_id - 创建者ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置创建者ID
     *
     * @param userId 创建者ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取活动标题
     *
     * @return title - 活动标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置活动标题
     *
     * @param title 活动标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取活动图片
     *
     * @return img_urls - 活动图片
     */
    public String getImgUrls() {
        return imgUrls;
    }

    /**
     * 设置活动图片
     *
     * @param imgUrls 活动图片
     */
    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    /**
     * 获取活动开始时间
     *
     * @return start_time - 活动开始时间
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置活动开始时间
     *
     * @param startTime 活动开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取活动结束时间
     *
     * @return end_time - 活动结束时间
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置活动结束时间
     *
     * @param endTime 活动结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取报名截止时间
     *
     * @return last_time - 报名截止时间
     */
    public Long getLastTime() {
        return lastTime;
    }

    /**
     * 设置报名截止时间
     *
     * @param lastTime 报名截止时间
     */
    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取活动人数上线
     *
     * @return max_people - 活动人数上线
     */
    public Integer getMaxPeople() {
        return maxPeople;
    }

    /**
     * 设置活动人数上线
     *
     * @param maxPeople 活动人数上线
     */
    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    /**
     * 获取活动可见范围（0 所有可见，1朋友可见）
     *
     * @return visible - 活动可见范围（0 所有可见，1朋友可见）
     */
    public Integer getVisible() {
        return visible;
    }

    /**
     * 设置活动可见范围（0 所有可见，1朋友可见）
     *
     * @param visible 活动可见范围（0 所有可见，1朋友可见）
     */
    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    /**
     * 获取咨询电话
     *
     * @return phone - 咨询电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置咨询电话
     *
     * @param phone 咨询电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取活动价格
     *
     * @return price - 活动价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置活动价格
     *
     * @param price 活动价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取活动分类（0  线上活动，  1线下活动）
     *
     * @return type - 活动分类（0  线上活动，  1线下活动）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置活动分类（0  线上活动，  1线下活动）
     *
     * @param type 活动分类（0  线上活动，  1线下活动）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取线上活动跳转地址
     *
     * @return target_url - 线上活动跳转地址
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * 设置线上活动跳转地址
     *
     * @param targetUrl 线上活动跳转地址
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    /**
     * 获取活动地址
     *
     * @return address - 活动地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置活动地址
     *
     * @param address 活动地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取活动是否需要审核（0 不需要审核，1 需要审核）
     *
     * @return examine - 活动是否需要审核（0 不需要审核，1 需要审核）
     */
    public Integer getExamine() {
        return examine;
    }

    /**
     * 设置活动是否需要审核（0 不需要审核，1 需要审核）
     *
     * @param examine 活动是否需要审核（0 不需要审核，1 需要审核）
     */
    public void setExamine(Integer examine) {
        this.examine = examine;
    }

    /**
     * 获取报名活动模版
     *
     * @return member_template - 报名活动模版
     */
    public String getMemberTemplate() {
        return memberTemplate;
    }

    /**
     * 设置报名活动模版
     *
     * @param memberTemplate 报名活动模版
     */
    public void setMemberTemplate(String memberTemplate) {
        this.memberTemplate = memberTemplate;
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

    /**
     * @return mask
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * @param mask
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }

    /**
     * 获取活动描述
     *
     * @return desc_content - 活动描述
     */
    public String getDescContent() {
        return descContent;
    }

    /**
     * 设置活动描述
     *
     * @param descContent 活动描述
     */
    public void setDescContent(String descContent) {
        this.descContent = descContent;
    }
}