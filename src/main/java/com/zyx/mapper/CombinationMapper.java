package com.zyx.mapper;

import com.zyx.base.BaseMapper;
import com.zyx.model.Activity;
import com.zyx.model.Combination;
import com.zyx.parm.activity.QueryCombinationParm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("combinationMapper")
public interface CombinationMapper extends BaseMapper<Combination> {

    /**
     * 插入组合数据（返回插入数据ID）
     * @param combination
     * @return
     */
    Integer insertCombination(Combination combination);

    /**
     * 多条条件查询组合
     *
     * @param parm
     * @return
     */
    List<Activity> queryCombination(QueryCombinationParm parm);

    /**
     * 修改组合
     *
     * @param combination
     * @return
     */
    int updateCombination(Combination combination);

}