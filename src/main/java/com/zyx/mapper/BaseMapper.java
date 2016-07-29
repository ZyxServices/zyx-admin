package com.zyx.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * Created by MrDeng on 2016/7/29.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
