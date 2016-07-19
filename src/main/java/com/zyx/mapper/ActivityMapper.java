package com.zyx.mapper;

import com.zyx.model.Activity;
import com.zyx.parm.QueryActivityParm;
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
    List<Activity> queryActivity(QueryActivityParm parm);


}