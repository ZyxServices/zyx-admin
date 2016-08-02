package com.zyx.utils;

import com.zyx.constants.Constants;

import java.util.Map;

/**
 * Created by SubDong on 16-6-16.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title ImagesVerifyUtils
 * @package com.zyx.utils
 * @update 16-6-16 上午10:21
 */
public class ImagesVerifyUtils {

    public static Map<String, Object> verify(String uploadFile) {

        if (uploadFile == null || uploadFile.equals(Constants.AUTH_ERROR_902 + "")) {
            return MapUtils.buildErrorMap(Constants.AUTH_ERROR_902, "图片上传失败,请重试");
        }

        if (uploadFile.equals(Constants.AUTH_ERROR_903 + "")) {
            return MapUtils.buildErrorMap(Constants.AUTH_ERROR_903, "文件格式错误");
        }

        if (uploadFile.equals(Constants.AUTH_ERROR_901 + "")) {
            return MapUtils.buildErrorMap(Constants.AUTH_ERROR_901, "图片大小不能超过5MB");
        }
        return null;
    }
}
