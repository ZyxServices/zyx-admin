package com.zyx.constants;

/**
 * Created by wms on 2016/7/26.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserConstants.java
 */
public interface AppUserConstants extends Constants {

    /**
     * 已经存在的用户账号
     */
    int ERROR_APP_USER_5001 = 5001;
    String ERROR_APP_USER_5001_MSG = "this phone is exist";

    /**
     * 创建用户失败
     */
    int ERROR_APP_USER_5002 = 5002;
    String ERROR_APP_USER_5002_MSG = "create app user failed";

    /**
     * 编辑用户失败
     */
    int ERROR_APP_USER_5003 = 5003;
    String ERROR_APP_USER_5003_MSG = "update app user failed";
}
