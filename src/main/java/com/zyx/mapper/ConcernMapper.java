package com.zyx.mapper;

import com.zyx.model.Concern;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConcernMapper extends Mapper<Concern> {
    /**
     * 总动态数
     *
     * @return
     */
    int count();

    /**
     * 根据分页条件查询动态
     *
     * @param start
     * @param end
     * @return
     */
    List<Concern> findByPager(@Param(value = "start") int start, @Param(value = "end") int end);

    /**
     * 查询某一条数据
     *
     * @param id
     * @return
     */
    Concern findById(@Param(value = "id") Integer id);

    /**
     * 查询某一条数据
     *
     * @param id
     * @return
     */
    Integer setState(@Param(value = "state") Integer state, @Param(value = "id") Integer id);
}