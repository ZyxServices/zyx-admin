package com.zyx.service.activity.impl;

import com.zyx.constants.Constants;
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
            int insertCombination = combinationMapper.insertCombination(combination);
            if (insertCombination > 0) {
                List<CombinedData> combinedDatas = new ArrayList<>();
                for (Integer activityId : activityIds) {
                    CombinedData combinedData = new CombinedData();
                    if (activityId != null && activityId > 0) {
                        combinedData.setDataid(activityId);
                    } else {
                        continue;
                    }
                    combinedData.setCombinedid(insertCombination);
                    combinedData.setCreateTime(new Date().getTime());
                    combinedData.setDel(0);
                    combinedData.setMask(0);
                    combinedDatas.add(combinedData);
                }
                int insertList = combinedDataMapper.insertList(combinedDatas);
                if (insertList > 0) {
                    return MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", "");
                } else {
                    combinationMapper.deleteByPrimaryKey(insertCombination);
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
    public Map<String, Object> queryCombination(int pageDataNumber, int pageNumber, String name) {
        QueryCombinationParm parm = new QueryCombinationParm();

        if (pageNumber == 0) pageNumber = 1;
        if (pageNumber == 1) {
            parm.setPageNumber(0);
            parm.setPage(pageDataNumber - 1);
        } else {
            parm.setPageNumber(pageDataNumber);
            if (pageDataNumber == 0) {
                parm.setPage(0);
            } else {
                parm.setPage((pageDataNumber * pageNumber) - 1);
            }
        }
        parm.setName(name);
        List<Activity> activities = combinationMapper.queryCombination(parm);
        if(activities != null && activities.size() > 0){
            int i = combinationMapper.selectCount(null);
            Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", activities);
            map.put("dataCount", i);
            return map;
        }else{
            return MapUtils.buildErrorMap(Constants.NO_DATA, "差无数据");
        }
    }
}
