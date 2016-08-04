package com.zyx.mapper;

import com.zyx.model.CircleType;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.mapper
 * Create by XiaoWei on 2016/8/2
 */
public interface CircleTypeMapper extends Mapper<CircleType> {
    List<CircleType> findAll();

    Integer setState(@Param(value = "id") Integer id, @Param(value = "state") Integer state);

    CircleType findByTypeName(@Param(value = "typeName") String typeName);
}
