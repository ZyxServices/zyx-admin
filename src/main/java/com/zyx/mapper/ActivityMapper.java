package com.zyx.mapper;

import com.zyx.dto.ActivityDto;
import com.zyx.model.Activity;
import com.zyx.parm.activity.QueryActivityParm;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("activityMapper")
public interface ActivityMapper extends Mapper<Activity> {

    /**
     * 多条条件查询活动
     *
     * @param parm
     * @return
     */
    List<ActivityDto> queryActivity(QueryActivityParm parm);

    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    int updateActivity(Activity activity);


    /**
     * 多ID查询活动
     *
     * @param ids
     * @return
     */
    List<Activity> queryActivityIn(List<Integer> ids);

    /**
     *
     */
    int selectCountActivity();

}