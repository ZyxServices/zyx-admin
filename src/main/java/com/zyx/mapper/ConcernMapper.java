package com.zyx.mapper;

import com.zyx.model.Concern;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConcernMapper extends Mapper<Concern> {
    /**
     * 总动态数
     * @return
     */
    int count();

    /**
     * 根据分页条件查询动态
     * @param start
     * @param end
     * @return
     */
    List<Concern> findByPager(@Param(value = "start") int start, @Param(value = "end") int end);
}