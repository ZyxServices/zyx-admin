package com.zyx.service.activity.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.ActivityMapper;
import com.zyx.mapper.CombinationMapper;
import com.zyx.mapper.CombinedDataMapper;
import com.zyx.model.Activity;
import com.zyx.model.Combination;
import com.zyx.model.CombinedData;
import com.zyx.parm.activity.QueryCombinationParm;
import com.zyx.service.activity.CombinationService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by SubDong on 16-7-29.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title CombinationServiceImpl
 * @package com.zyx.service.activity.impl
 * @update 16-7-29 下午2:42
 */
@Service
public class CombinationServiceImpl implements CombinationService {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private CombinationMapper combinationMapper;

    @Resource
    private CombinedDataMapper combinedDataMapper;

    @Override
    public Map<String, Object> createCombination(String name, String image, Integer[] activityIds) {
        if (name != null && image != null && activityIds != null && activityIds.length > 0) {
            Combination combination = new Combination();
            combination.setName(name);
            combination.setImage(image);
            combination.setCreateTime(new Date().getTime());
            combination.setDel(0);
            combination.setMask(0);

            Combination comb = new Combination();
            comb.setName(name);
            Combination selectCom = combinationMapper.selectByPrimaryKey(comb);
            if (selectCom != null && selectCom.getName().equals(name)) {
                return MapUtils.buildErrorMap(Constants.DATA_ALREADY_EXISTS, "组合已存在");
            }
            combinationMapper.insertCombination(combination);
            if (combination.getId() != null && combination.getId() > 0) {
                List<CombinedData> combinedDatas = new ArrayList<>();
                for (Integer activityId : activityIds) {
                    CombinedData combinedData = new CombinedData();
                    if (activityId != null && activityId > 0) {
                        combinedData.setDataid(activityId);
                    } else {
                        continue;
                    }
                    combinedData.setCombinedid(combination.getId());
                    combinedData.setCreateTime(new Date().getTime());
                    combinedData.setDel(0);
                    combinedData.setMask(0);
                    combinedDatas.add(combinedData);
                }
                int insertList = combinedDataMapper.insertList(combinedDatas);
                if (insertList > 0) {
                    return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
                } else {
                    combinationMapper.deleteByPrimaryKey(combination.getId());
                    return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED, "数据添加失败");
                }
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_INSERT_FAILED, "数据添加失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> updateCombination(Combination combination, Integer[] activityIds) {

        if (combination.getId() != null && combination.getId() > 0) {
            int updateCombination = combinationMapper.updateCombination(combination);
            if (updateCombination > 0) {
                if (activityIds != null && activityIds.length > 0) {
                    int combinationActivity = combinedDataMapper.delCombinationActivity(combination.getId());
                    if (combinationActivity > 0) {
                        List<CombinedData> datas = new ArrayList<>();
                        for (Integer activityId : activityIds) {
                            CombinedData combinedData = new CombinedData();
                            combinedData.setDataid(activityId);
                            combinedData.setCombinedid(combination.getId());
                            combinedData.setCreateTime(new Date().getTime());
                            combinedData.setDel(0);
                            combinedData.setMask(0);
                            datas.add(combinedData);
                        }
                        int insertList = combinedDataMapper.insertList(datas);
                        if (insertList > 0) {
                            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
                        } else {
                            return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "组合下的活动更新失败");
                        }
                    } else {
                        return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "组合下的活动更新失败");
                    }
                } else {
                    return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
                }
            } else {
                return MapUtils.buildErrorMap(Constants.DATA_UPDATE_FAILED, "数据更新失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> queryCombination(int pageDataNumber, int pageNumber, String name) {
        QueryCombinationParm parm = new QueryCombinationParm();

        parm.setPageNumber(pageNumber);
        parm.setPage((pageNumber - 1) * pageDataNumber);
        parm.setName(name);
        List<Activity> activities = combinationMapper.queryCombination(parm);
        if (activities != null && activities.size() > 0) {
            int i = combinationMapper.selectCount(null);
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", activities);
            map.put("dataCount", i);
            return map;
        } else {
            List<Activity> list = new ArrayList<>();
            return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", list);
        }
    }

    @Override
    public Map<String, Object> queryCombinationById(Integer combinationId) {
        if (combinationId != null && combinationId > 0) {
            Combination combination = combinationMapper.selectByPrimaryKey(combinationId);
            if (combination != null) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", combination);
            } else {
                return MapUtils.buildErrorMap(Constants.NO_DATA, "差无数据");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> queryCombinationIdByActivity(Integer combinationId) {
        if (combinationId != null && combinationId > 0) {
            CombinedData combinedData = new CombinedData();
            combinedData.setCombinedid(combinationId);
            List<CombinedData> combinedDatas = combinedDataMapper.select(combinedData);
            if (combinedDatas != null && combinedDatas.size() > 0) {
                List<Integer> integerList = combinedDatas.stream().map(CombinedData::getDataid).collect(Collectors.toList());
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", integerList);
            } else {
                return MapUtils.buildErrorMap(Constants.NO_DATA, "差无数据");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> queryCombinationActivity(int pageDataNumber, int pageNumber, Integer combinationId) {
        QueryCombinationParm parm = new QueryCombinationParm();

        parm.setPageNumber(pageDataNumber);
        parm.setPage((pageNumber - 1) * pageDataNumber);
        parm.setId(combinationId);

        List<CombinedData> combinedDatas = combinedDataMapper.queryCombinationActivity(parm);

        List<Integer> collect = combinedDatas
                .stream()
                .filter(e -> e.getDel() != 1 && e.getMask() != 1)
                .map(CombinedData::getDataid)
                .collect(Collectors.toList());

        List<Activity> activities = activityMapper.queryActivityIn(collect);
        if (activities != null && activities.size() > 0) {
            int dataCount = activities.size();
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", activities);
            map.put("dataCount", dataCount);
            return map;
        } else {
            return MapUtils.buildErrorMap(Constants.NO_DATA, "差无数据");
        }
    }
}
