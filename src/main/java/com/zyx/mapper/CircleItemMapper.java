package com.zyx.mapper;

import com.zyx.model.Circle;
import com.zyx.model.CircleItem;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CircleItemMapper extends Mapper<CircleItem> {

    List<Circle> findByPage(@Param(value = "start") int start, @Param(value = "end") int end);
}