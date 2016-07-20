package com.zyx.common.enums;

/**
 * Created by MrDeng on 2016/7/20.
 */
public enum  LiveLabEnum {

    /**
     * 删除
     */
    DELETED(-1),
    USING(1);


    private int status;

    LiveLabEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
