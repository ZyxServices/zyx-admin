package com.zyx.mapper;

import com.zyx.model.Circle;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CircleMapper extends Mapper<Circle> {
    List<Circle> findByPager(@Param(value = "start") int start, @Param(value = "end") int end);

    Circle findById(@Param(value = "id") Integer id);

    Integer tuiJian(@Param(value = "circle_id")Integer circleId,@Param(value = "top_size") Integer topSize);
}