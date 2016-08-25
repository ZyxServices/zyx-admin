package com.zyx.model.vo;

import com.zyx.model.Circle;
import com.zyx.model.CircleType;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.model.vo
 * Create by XiaoWei on 2016/7/28
 */
public class CircleVo extends Circle {

    private UserVo userVo;
    private UserVo masterVo;
    private CircleType circleTypeVo;
    private Integer circleItemCount;
    private Integer concernCount;


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

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public UserVo getMasterVo() {
        return masterVo;
    }

    public void setMasterVo(UserVo masterVo) {
        this.masterVo = masterVo;
    }

    public CircleType getCircleTypeVo() {
        return circleTypeVo;
    }

    public void setCircleTypeVo(CircleType circleTypeVo) {
        this.circleTypeVo = circleTypeVo;
    }
}
