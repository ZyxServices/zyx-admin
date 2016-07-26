package com.zyx.model;

import javax.persistence.*;

@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reply_type")
    private Integer replyType;//回复主体类型，-1为回复id，0为圈子，1为帖子，2为活动，3为动态

    @Column(name = "reply_id")
    private Integer replyId;

    @Column(name = "reply_account_id")
    private Integer replyAccountId;

    @Column(name = "reply_state")
    private Integer replyState;//回复类型,0为公开回复，其他类型预留

    @Column(name = "create_time")
    private Long createTime;

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
     * @return reply_type
     */
    public Integer getReplyType() {
        return replyType;
    }

    /**
     * @param replyType
     */
    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    /**
     * @return reply_id
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * @param replyId
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * @return reply_account_id
     */
    public Integer getReplyAccountId() {
        return replyAccountId;
    }

    /**
     * @param replyAccountId
     */
    public void setReplyAccountId(Integer replyAccountId) {
        this.replyAccountId = replyAccountId;
    }

    /**
     * @return reply_state
     */
    public Integer getReplyState() {
        return replyState;
    }

    /**
     * @param replyState
     */
    public void setReplyState(Integer replyState) {
        this.replyState = replyState;
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