package com.zyx.service.activity.impl;

import com.zyx.constants.ActivityConstants;
import com.zyx.constants.Constants;
import com.zyx.dto.ActivityDto;
import com.zyx.mapper.ActivityMapper;
import com.zyx.model.Activity;
import com.zyx.parm.activity.QueryActivityParm;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.activity.ActivityService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    public ActivityServiceImpl() {
        super(Activity.class);
    }

    @Override
    public Map<String, Object> insertActivity(Activity activity) {
        if (activity.getUserId() != null && activity.getTitle() != null
                && activity.getDescContent() != null && activity.getImgUrls() != null
                && activity.getStartTime() > 0 && activity.getEndTime() > 0
                && activity.getAddress() != null && activity.getPrice() != null
                && activity.getType() != null) {
            activity.setDel(0);
            activity.setMask(0);
            activity.setCreateTime(new Date().getTime());
            activity.setTargetUrl(null);
            int insert = activityMapper.insert(activity);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "发布成功", null);
            } else {
                return MapUtils.buildErrorMap(ActivityConstants.AUTH_ERROR_10000, "活动发布失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> queryActivity(int pageDataNumber, int pageNumber, String searchText) {

        QueryActivityParm parm = new QueryActivityParm();

        parm.setPageNumber(pageDataNumber);
        parm.setPage((pageNumber - 1) * pageDataNumber);
        parm.setGroupName(searchText);
        List<ActivityDto> activities = activityMapper.queryActivity(parm);

        int i = activityMapper.selectCountActivity();
        if (activities != null && activities.size() > 0) {
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", activities);
            map.put("dataCount", i);
            return map;
        } else {
            return MapUtils.buildErrorMap(Constants.NO_DATA, "差无数据");
        }
    }

    @Override
    public Map<String, Object> queryActivityById(Integer activityId) {
        if (activityId != null) {
            Activity activity = activityMapper.selectByPrimaryKey(activityId);
            if (activity != null) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", activity);
            } else {
                return MapUtils.buildErrorMap(Constants.NO_DATA, "差无数据");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> updateActivity(Activity activity) {
        if (activity != null) {
            int i = activityMapper.updateActivity(activity);
            if (i > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据更新失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> maskActivity(int id, int maskType) {
        if (maskType > -1 && maskType < 2 && id > 0) {
            Activity activity = new Activity();
            activity.setId(id);
            activity.setMask(maskType);
            int i = activityMapper.updateActivity(activity);
            if (i > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据屏蔽失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数有误");
        }
    }

    @Override
    public Map<String, Object> delActivity(String id, int delType) {
        if (delType > -1 && delType < 2 && id != null && !id.equals("")) {
            int a = 0;
            String[] ids = id.split(",");
            for (String integer : ids) {
                if (integer != null && Integer.valueOf(integer) > 0) {
                    Activity activity = new Activity();
                    activity.setId(Integer.valueOf(integer));
                    activity.setDel(delType);
                    int i = activityMapper.updateActivity(activity);
                    if (i > 0) {
                        a++;
                    }
                }
            }
            if (a > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据删除失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数有误");
        }
    }

}
