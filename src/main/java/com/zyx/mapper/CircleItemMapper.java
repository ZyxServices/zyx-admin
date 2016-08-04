package com.zyx.mapper;

import com.zyx.model.Circle;
import com.zyx.model.CircleItem;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CircleItemMapper extends Mapper<CircleItem> {

    List<CircleItem> findByPager(@Param(value = "start") Integer start, @Param(value = "end") Integer end);

    CircleItem findOne(@Param(value = "id") Integer id);

    Integer setVisible(@Param(value = "state") Integer state, @Param(value = "id") Integer id);

    List<CircleItem> search(@Param(value = "searchText") String searchText, @Param(value = "start") Integer start, @Param(value = "end") Integer end);

}