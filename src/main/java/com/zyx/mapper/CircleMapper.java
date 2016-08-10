package com.zyx.mapper;

import com.zyx.model.Circle;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CircleMapper extends Mapper<Circle> {
    List<Circle> findByPager(@Param(value = "start") int start, @Param(value = "end") int end);

    Circle findById(@Param(value = "id") Integer id);

    Integer tuiJian(@Param(value = "circle_id") Integer circleId, @Param(value = "top_size") Integer topSize);

    Integer setState(@Param(value = "state") Integer state, @Param(value = "id") Integer id);

    Circle existCircle(@Param(value = "title") String title);

    List<Circle> search(@Param(value = "title") String title, @Param(value = "start") Integer start, @Param(value = "end") Integer end);

    Integer searchCount();

//    Integer editCircle(@Param("title") String title,@Param("headImg")String headImg,@Param("circleType")Integer circleType,@Param("details")String details,@Param("masterId")Integer masterId,@Param("adminIds")String adminIds);

}