package com.zyx.mapper;

import com.zyx.base.BaseMapper;
import com.zyx.model.CombinedData;
import com.zyx.parm.activity.QueryCombinationParm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("combinedDataMapper")
public interface CombinedDataMapper extends BaseMapper<CombinedData> {

    /**
     * 多条条件查询组合
     *
     * @param parm
     * @return
     */
    List<CombinedData> queryCombinationActivity(QueryCombinationParm parm);


}