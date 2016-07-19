package com.zyx.constants;

/**
 * Created by MrDeng on 2016/7/19.
 */
public interface LiveConstants extends Constants {

    /**
     * 参数Lab为空
     */
    int ERROR_LIVE_NULL_LAB = 23001;
    String ERROR_MSG_LIVE_NULL_LAB = "null or empty parameter of 'lab'";

    /**
     * 已经存在的Lab标签
     */
    int ERROR_LIVE_EXIST_LAB = 23002;
    String ERROR_MSG_LIVE_EXIST_LAB = "this lab is exist";
}
