package com.zyx.utils;


import com.zyx.constants.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skmbg on 2016/6/29.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title MapUtils
 */
public final class MapUtils {
    public static Map<String, Object> buildSuccessMap(int state, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.STATE, state);
        map.put(Constants.SUCCESS_MSG, msg);
        map.put(Constants.DATA, data);
        return map;
    }

    public static Map<String, Object> buildErrorMap(int state, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.STATE, state);
        map.put(Constants.ERROR_MSG, msg);
        return map;
    }

    public static Map<String, Object> buildSuccessMap(int state, String msg, Object data, Map<String, Object> customParams) {
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.STATE, state);
        map.put(Constants.SUCCESS_MSG, msg);
        map.put(Constants.DATA, data);
        map.putAll(customParams);
        return map;
    }
}
