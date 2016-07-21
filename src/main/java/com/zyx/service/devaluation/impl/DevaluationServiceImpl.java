package com.zyx.service.devaluation.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.DevaluationMapper;
import com.zyx.model.Devaluation;
import com.zyx.service.devaluation.DevaluationService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by SubDong on 16-7-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title DevaluationServiceImpl
 * @package com.github.ichenkaihua.service.activity.impl
 * @update 16-7-12 上午10:30
 */
@Service
public class DevaluationServiceImpl implements DevaluationService {

    @Resource
    DevaluationMapper devaluationMapper;


    @Override
    public Map<String, Object> insterActivityDeva(Devaluation devaluation) {
        if (devaluation.getTypes() != null && devaluation.getDevaluationId() != null) {
            devaluation.setCreateTime(new Date().getTime());
            int insert = devaluationMapper.insert(devaluation);
            if (insert > 0) {
                devaluationMapper.insert(devaluation);
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "首推成功", null);
            } else {
                return MapUtils.buildErrorMap(Constants.ERROR, "首推失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }
}
