package com.zyx.dto;

import javax.persistence.*;

@Table(name = "activity")
public class ActivityDto {
    /**
     * 表id
     */
    private Integer id;

    /**
     * 创建者ID
     */
    private Integer userId;
    /**
     * 创建者名称
     */
    private String userName;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动图片
     */
    private String imgUrls;

    /**
     * 活动开始时间
     */
    private Long startTime;

    /**
     * 活动结束时间
     */
    private Long endTime;

    /**
     * 报名截止时间
     */
    private Long lastTime;

    /**
     * 活动人数上线
     */
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
    private String targetUrl;

    /**
     * 活动是否需要审核（0 不需要审核，1 需要审核）
     */
    private Integer examine;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 报名活动模版
     */
    private String memberTemplate;

    private Long createTime;

    private Integer del;

    private Integer mask;

    /**
     * 活动描述
     */
    private String descContent;

    private boolean isDeva;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getExamine() {
        return examine;
    }

    public void setExamine(Integer examine) {
        this.examine = examine;
    }

    public String getMemberTemplate() {
        return memberTemplate;
    }

    public void setMemberTemplate(String memberTemplate) {
        this.memberTemplate = memberTemplate;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public String getDescContent() {
        return descContent;
    }

    public void setDescContent(String descContent) {
        this.descContent = descContent;
    }

    public boolean getIsDeva() {
        return isDeva;
    }

    public void setIsDeva(boolean isDeva) {
        this.isDeva = isDeva;
    }
}