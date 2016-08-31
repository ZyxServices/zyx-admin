package com.zyx.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MrDeng on 2016/8/24.
 */
public interface DevaContants extends Constants {
    /**
     * 首页
     */
    int DEVA_AREA_HOME_PAGE = 1;
    /**
     * 精选
     */
    int DEVA_AREA_SECOND = 2;
    /**
     * 看台
     */
    int DEVA_AREA_STAND = 3;

    int DEVA_NOT_EXIST_MODEL_AREA = 73001;
    String MSG_DEVA_NOT_EXIST_MODEL_AREA = "not exist modle or area ";

    int DEVA_REPEAT = 73002;
    String MSG_DEVA_REPEAT="repeat set devaluation";
    Map<String, Integer> DEVA_AREA_MAX_ITEM = new HashMap<String, Integer>() {
        {
            put("1_1", 5);
            put("2_1",12);
            put("4_1",3);
            put("5_1",9);
            put("6_1",10);
            put("4_3",5);
            put("3_3",3);
            put("2_2",4);
        }
    };
}
