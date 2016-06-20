package com.zyx.model;

import javax.persistence.*;

@Table(name = "WEIXIN_TEXTMSG")
public class WeixinTextmsg {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TEXTMSG_ID")
    private String textmsgId;

    /**
     * 关键词
     */
    @Column(name = "KEYWORD")
    private String keyword;

    /**
     * 内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private String createtime;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 备注
     */
    @Column(name = "BZ")
    private String bz;

    /**
     * @return ID
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
     * @return TEXTMSG_ID
     */
    public String getTextmsgId() {
        return textmsgId;
    }

    /**
     * @param textmsgId
     */
    public void setTextmsgId(String textmsgId) {
        this.textmsgId = textmsgId;
    }

    /**
     * 获取关键词
     *
     * @return KEYWORD - 关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键词
     *
     * @param keyword 关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取内容
     *
     * @return CONTENT - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取创建时间
     *
     * @return CREATETIME - 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return BZ - 备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 设置备注
     *
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }
}