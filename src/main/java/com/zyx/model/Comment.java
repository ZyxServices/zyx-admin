package com.zyx.model;

import javax.persistence.*;

@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 回复类型，0：圈子,1:帖子，2：活动，3：动态，4：直播
     */
    @Column(name = "comment_type")
    private Integer commentType;

    /**
     * 主体id，根据comment_type而决定是哪个主体id
     */
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "comment_account")
    private Integer commentAccount;

    @Column(name = "create_time")
    private Long createTime;

    /**
     * 显示方式，0：公开回复
     */
    @Column(name = "comment_state")
    private Integer commentState;

    @Column(name = "comment_content")
    private String commentContent;

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
     * 获取回复类型0：圈子,1:帖子，2：活动，3：动态
     *
     * @return comment_type - 回复类型0：圈子,1:帖子，2：活动，3：动态
     */
    public Integer getCommentType() {
        return commentType;
    }

    /**
     * 设置回复类型0：圈子,1:帖子，2：活动，3：动态
     *
     * @param commentType 回复类型0：圈子,1:帖子，2：活动，3：动态
     */
    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    /**
     * 获取主体id，根据comment_type而决定是哪个主体id
     *
     * @return comment_id - 主体id，根据comment_type而决定是哪个主体id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置主体id，根据comment_type而决定是哪个主体id
     *
     * @param commentId 主体id，根据comment_type而决定是哪个主体id
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * @return comment_account
     */
    public Integer getCommentAccount() {
        return commentAccount;
    }

    /**
     * @param commentAccount
     */
    public void setCommentAccount(Integer commentAccount) {
        this.commentAccount = commentAccount;
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
     * 获取显示方式，0：公开回复
     *
     * @return comment_state - 显示方式，0：公开回复
     */
    public Integer getCommentState() {
        return commentState;
    }

    /**
     * 设置显示方式，0：公开回复
     *
     * @param commentState 显示方式，0：公开回复
     */
    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }

    /**
     * @return comment_content
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}