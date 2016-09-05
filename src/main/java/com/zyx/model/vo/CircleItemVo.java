package com.zyx.model.vo;

import com.zyx.model.CircleItem;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.model.vo
 * Create by XiaoWei on 2016/7/28
 */
public class CircleItemVo extends CircleItem {
    private String createUser;
    private String circleTitle;
    private String circleTypeName;
    private Integer zanCounts;
    private Integer commentCounts;
    private boolean isDeva;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCircleTitle() {
        return circleTitle;
    }

    public void setCircleTitle(String circleTitle) {
        this.circleTitle = circleTitle;
    }

    public String getCircleTypeName() {
        return circleTypeName;
    }

    public void setCircleTypeName(String circleTypeName) {
        this.circleTypeName = circleTypeName;
    }

    public Integer getZanCounts() {
        return zanCounts;
    }

    public void setZanCounts(Integer zanCounts) {
        this.zanCounts = zanCounts;
    }

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }

    public boolean isDeva() {
        return isDeva;
    }

    public void setDeva(boolean deva) {
        isDeva = deva;
    }
}
