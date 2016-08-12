package com.zyx.model.vo;

import com.zyx.model.Circle;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.model.vo
 * Create by XiaoWei on 2016/7/28
 */
public class CircleVo extends Circle {

    private String userName;
    private String masterName;
    private Integer circleItemCount;
    private Integer concernCount;
    private String circleTypeName;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Integer getCircleItemCount() {
        return circleItemCount;
    }

    public void setCircleItemCount(Integer circleItemCount) {
        this.circleItemCount = circleItemCount;
    }

    public Integer getConcernCount() {
        return concernCount;
    }

    public void setConcernCount(Integer concernCount) {
        this.concernCount = concernCount;
    }

    public String getCircleTypeName() {
        return circleTypeName;
    }

    public void setCircleTypeName(String circleTypeName) {
        this.circleTypeName = circleTypeName;
    }
}
