package com.zyx.service.activity;

import com.zyx.model.Combination;

import java.util.Map;

/**
 * Created by SubDong on 16-7-29.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title CombinationService
 * @package com.zyx.service.activity
 * @update 16-7-29 下午2:41
 */
public interface CombinationService {

    /**
     * 活动组合
     *
     * @param name
     * @param image
     * @param activityIds
     * @return
     */
    Map<String, Object> createCombination(String name, String image, Integer[] activityIds);

    /**
     * 修改组合
     *
     * @param combination
     * @param activityIds
     * @return
     */
    Map<String, Object> updateCombination(Combination combination, Integer[] activityIds);

    /**
     * 查询活动组合
     *
     * @param pageDataNumber
     * @param pageNumber
     * @param searchText
     * @return
     */
    Map<String, Object> queryCombination(int pageDataNumber, int pageNumber, String searchText);

    /**
     * 通过ID 查询组合
     *
     * @param combinationId
     * @return
     */
    Map<String, Object> queryCombinationById(Integer combinationId);

    /**
     * 通过ID 查询组合
     *
     * @param combinationId
     * @return
     */
    Map<String, Object> queryCombinationIdByActivity(Integer combinationId);

    /**
     * 查询对应活动组合
     *
     * @param pageDataNumber
     * @param pageNumber
     * @param combinationId
     * @return
     */
    Map<String, Object> queryCombinationActivity(int pageDataNumber, int pageNumber, Integer combinationId);
}
