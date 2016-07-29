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
}
