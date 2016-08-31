package com.zyx.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Constants {

    String CURRENT_USER = "user";
    ////////////// 系统标识符 开始//////////////////
    /**
     * 状态 标志
     */
    String STATE = "state";
    /**
     * 状态 标志
     */
    String DATA = "data";
    /**
     * 错误代码 标志
     */
//    String ERROR_CODE = "errcode";
    /**
     * 错误消息 标志
     */
    String ERROR_MSG = "errmsg";

    /**
     * 成功消息 标志
     */
    String SUCCESS_MSG = "successmsg";

    /**
     * 成功返回data
     */
    String SUCCESS_DATA = "data";

    /**
     * 活动首推
     */
    String ACTIVITY_DEVA = "activityDeva";

    /**
     * 用户首推
     */
    String ACCOUNT_DEVA = "accountDeva";

    /**
     * 视频首推
     */
    String LIVE_DEVA = "liveDeva";

    /**
     *
     */
    String CONCER_DEVA = "concerDeva";

    /**
     *
     */
    String CIRLE_DEVA = "cirleDeva";

    ////////////// 系统标识符 结束//////////////////

    ////////////// 系统状态码 开始//////////////////
    ////////
    /**
     * 请求失败
     */
    int ERROR = 100;
    /**
     * 请求成功
     */
    int SUCCESS = 200;
    /// 参数相关代码 300~399
    /**
     * 参数错误代码
     */
    int PARAM_ERROR = 300;
    /**
     * 参数缺失
     */
    int PARAM_MISS = 301;

    /**
     * 非法参数 如大小 长度 类型不正确
     */
    int PARAM_ILIGAL = 302;


    /// 请求类型代码
    /**
     * 请求错误
     */
    int REQUEST_ERROR = 400;
    /**
     * 无权限请求
     */
    int REQUEST_UNAUTHORIZED = 401;

    /**
     * TOKEN失效
     */
    int TOKEN_FAILURE = 800;

    /**
     * 无数据
     */
    int NO_DATA = 804;

    /**
     * 数据已存在
     */
    int DATA_ALREADY_EXISTS = 805;

    /**
     * 数据更新失败
     */
    int DATA_UPDATE_FAILED = 806;

    /**
     * 数据插入失败
     */
    int DATA_INSERT_FAILED = 807;

    //文件类型 900+

    /**
     * 图片上传文件大于5MB
     */
    int AUTH_ERROR_901 = 901;
    String AUTH_ERROR_901_MSG = "图片上传文件不能大于5MB";
    /**
     * 服务器错误
     */
    int ERROR_500 = 500;
    /**
     * 文件上传失败
     */
    int AUTH_ERROR_902 = 902;
    String AUTH_ERROR_902_MSG = "文件上传失败";
    /**
     * 文件格式错误
     */
    int AUTH_ERROR_903 = 903;
    String AUTH_ERROR_903_MSG = "文件格式错误";
    /**
     * 删除失败
     */
    int ERROR_DEL_1001 = 1001;

    /**
     * 恢复删除失败
     */
    int ERROR_UN_DEL_1002 = 1002;

    /**
     * 屏蔽失败
     */
    int ERROR_MASK_1003 = 1003;

    /**
     * 恢复屏蔽失败
     */
    int ERROR_UN_MASK_1004 = 1004;

    ////////
    ////////////// 系统状态码 结束//////////////////

    ////////////// 系统消息 开始//////////////////
    /**
     * 错误
     */
    String MSG_ERROR = "error";
    /**
     * 成功
     */
    String MSG_SUCCESS = "success";

    String MSG_PARAM_ERROR = "input paragrams is error";

    String MSG_PARAM_MISS = "missing paragrams";

    String MSG_PARAM_ILIGAL = "iligal paragram";

    String MSG_REQUEST_ERROR = "request error";

    String MSG_REQUEST_UNAUTHORIZED = "unauthorized request";

    /**
     * 删除失败
     */
    String ERROR_DEL_1001_MSG = "删除失败";

    /**
     * 恢复删除失败
     */
    String ERROR_UN_DEL_1002_MSG = "恢复删除失败";

    /**
     * 屏蔽失败
     */
    String ERROR_MASK_1003_MSG = "屏蔽失败";

    /**
     * 恢复屏蔽失败
     */
    String ERROR_UN_MASK_1004_MSG = "恢复屏蔽失败";


    ////////////// 系统消息 结束//////////////////

    /**
     * 系统错误
     */
    Map<String, Object> MAP_500 = new ConcurrentHashMap() {{
        put(Constants.STATE, Constants.ERROR_500);
        put(Constants.ERROR_MSG, Constants.MSG_ERROR);
    }};

    /**
     * TOKEN失效，401无权限请求
     */
    Map<String, Object> MAP_TOKEN_FAILURE = new ConcurrentHashMap() {{
        put(Constants.STATE, Constants.REQUEST_UNAUTHORIZED);
        put(Constants.ERROR_MSG, Constants.MSG_REQUEST_UNAUTHORIZED);
    }};

    /**
     * 参数缺失
     */
    Map<String, Object> MAP_PARAM_MISS = new ConcurrentHashMap() {{
        put(Constants.STATE, Constants.PARAM_MISS);
        put(Constants.ERROR_MSG, Constants.MSG_PARAM_MISS);
    }};

    /**
     * 参数缺失
     */
    Map<String, Object> MAP_BASE_SUCCESS = new ConcurrentHashMap() {{
        put(Constants.STATE, SUCCESS);
        put(Constants.ERROR_MSG, MSG_SUCCESS);
    }};

    /**
     * 数据库错误，删除失败
     */
    Map<String, Object> MAP_DEL_ERROR = new ConcurrentHashMap() {{
        put(Constants.STATE, ERROR_DEL_1001);
        put(Constants.ERROR_MSG, ERROR_DEL_1001_MSG);
    }};

    /**
     * 数据库错误，删除恢复失败
     */
    Map<String, Object> MAP_UN_DEL_ERROR = new ConcurrentHashMap() {{
        put(Constants.STATE, ERROR_UN_DEL_1002);
        put(Constants.ERROR_MSG, ERROR_UN_DEL_1002_MSG);
    }};

    /**
     * 数据库错误，屏蔽
     */
    Map<String, Object> MAP_MASK_ERROR = new ConcurrentHashMap() {{
        put(Constants.STATE, ERROR_MASK_1003);
        put(Constants.ERROR_MSG, ERROR_MASK_1003_MSG);
    }};

    /**
     * 数据库错误，屏蔽恢复失败
     */
    Map<String, Object> MAP_UN_MASK_ERROR = new ConcurrentHashMap() {{
        put(Constants.STATE, ERROR_UN_MASK_1004);
        put(Constants.ERROR_MSG, ERROR_UN_MASK_1004_MSG);
    }};

    //////////////////Model 模块定义//////////////////
    //子模块 当前数字后加两位如1子模块为101 11子模块为1101
    /**
     * 活动模块
     */
    int MODEL_ACTIVITY = 1;
    /**
     * 直播模块
     */
    int MODEL_LIVE = 2;
    /**
     * 圈子模块
     */
    int MODEL_CIRCLE = 3;
    /**
     * 帖子模块
     */
    int MODEL_CIRCLE_ITEM = 4;
    /**
     * 动态模块
     */
    int MODEL_CONCERN = 5;
    /**
     * 用户模块
     */
    int MODEL_USER = 6;
    /**
     * 系统模块
     */
    int MODEL_SYSTEM = 7;

    Map<Integer, String> devaNames = new HashMap() {{
        put(1, "activityDevas");
        put(2, "liveDevas");
        put(3, "cirleDevas");
        put(4, "cirleItemDevas");
        put(5, "concerDevas");
        put(6, "userDevas");
    }};
}
