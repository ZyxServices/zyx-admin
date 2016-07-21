package com.zyx.common.enums;

/**
 * Created by MrDeng on 2016/7/21.
 */
public enum LiveStateEnum {
    WAIT_LIVING(0),
    LIVING(1),
    END_LIVING(2);
    private int state;
    LiveStateEnum(int state) {
        this.state = state;
    }
    public int getState() {
        return this.state;
    }
}
