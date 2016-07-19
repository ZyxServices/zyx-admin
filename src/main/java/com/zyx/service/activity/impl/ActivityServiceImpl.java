package com.zyx.service.activity.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.ActivityMapper;
import com.zyx.model.Activity;
import com.zyx.parm.QueryActivityParm;
import com.zyx.service.activity.ActivityService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SubDong on 16-7-18.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title ActivityServiceImpl
 * @package com.zyx.service.activity.impl
 * @update 16-7-18 下午4:31
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Map<String, Object> queryActivity(int pageDataNumber, int pageNumber) {

        QueryActivityParm parm = new QueryActivityParm();

        if (pageNumber == 0) pageNumber = 1;
        if (pageNumber == 1) {
            parm.setPageNumber(0);
            parm.setPage(pageDataNumber - 1);
        } else {
            parm.setPageNumber(pageDataNumber);
            if (pageDataNumber == 0) {
                parm.setPage(0);
            } else {
                parm.setPage((pageDataNumber * pageNumber) - 1);
            }
        }

        List<Activity> activities = activityMapper.queryActivity(parm);
        if (activities != null && activities.size() > 0) {
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", activities);
        } else {
            return MapUtils.buildErrorMap(Constants.NO_DATA,"差无数据");
        }
    }
}
