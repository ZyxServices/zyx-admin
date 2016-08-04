package com.zyx.constants;

/**
 * Created by wms on 2016/8/2.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title SysConstants.java
 */
public interface SysConstants extends Constants {
    /**
     * 已经存在的角色
     */
    int ERROR_APP_USER_9001 = 9001;
    String ERROR_APP_USER_9001_MSG = "this sys role name is exist";

    /**
     * 创建角色失败
     */
    int ERROR_APP_USER_9002 = 9002;
    String ERROR_APP_USER_9002_MSG = "create sys role failed";

    /**
     * 创建角色失败
     */
    int ERROR_APP_USER_9003 = 9003;
    String ERROR_APP_USER_9003_MSG = "edit sys role failed";

    /**
     * 已经存在的管理员
     */
    int ERROR_9004 = 9005;
    String ERROR_9004_MSG = "edit sys user failed";

    /**
     * 创建用户失败
     */
    int ERROR_9005 = 9005;
    String ERROR_9005_MSG = "create sys user failed";

    /**
     * 编辑用户失败
     */
    int ERROR_9006 = 9006;
    String ERROR_9006_MSG = "edit sys user failed";
}
