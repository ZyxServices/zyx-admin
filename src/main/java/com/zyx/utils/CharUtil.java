package com.zyx.utils;

import java.util.List;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.utils
 * Create by XiaoWei on 2016/8/15
 */
public class CharUtil {

    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }
}
