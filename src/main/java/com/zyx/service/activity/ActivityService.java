package com.zyx.service.activity;


import com.zyx.parm.QueryActivityParm;

import java.util.Map;

/**
 * Created by Rainbow on 16-6-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title com.zyx.service.activity
 */
public interface ActivityService {


    /**
     * 多条条件查询活动
     *
     * @param pageDataNumber
     * @param pageNumber
     * @return
     */
    Map<String, Object> queryActivity(int pageDataNumber, int pageNumber,String searchText);

    /**
     * 通过ID查询活动
     *
     * @param activityId
     * @return
     */
    Map<String, Object> queryActivityById(Integer activityId);

}
