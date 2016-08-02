package com.zyx.mapper;

import com.zyx.base.BaseMapper;
import com.zyx.model.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> findAll();

    Integer setState(@Param(value = "id") Integer id, @Param(value = "state") Integer state);
}