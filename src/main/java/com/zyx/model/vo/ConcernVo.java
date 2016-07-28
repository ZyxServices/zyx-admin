package com.zyx.model.vo;

import com.zyx.model.Concern;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.model.vo
 * Create by XiaoWei on 2016/7/28
 */
public class ConcernVo extends Concern {
    private String userName;
    private Integer authenticate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(Integer authenticate) {
        this.authenticate = authenticate;
    }
}
