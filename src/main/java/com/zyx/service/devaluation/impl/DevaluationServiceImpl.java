package com.zyx.service.devaluation.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.DevaluationMapper;
import com.zyx.model.Devaluation;
import com.zyx.service.devaluation.DevaluationService;
import com.zyx.utils.MapUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SubDong on 16-7-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title DevaluationServiceImpl
 * @update 16-7-12 上午10:30
 */
@Service
public class DevaluationServiceImpl implements DevaluationService {

    @Resource
    DevaluationMapper devaluationMapper;

    @Resource
    protected RedisTemplate<String, String> stringRedisTemplate;

    @Override
    public Map<String, Object> insertActivityDeva(Devaluation devaluation) {

        if (devaluation.getTypes() != null && devaluation.getDevaluationId() != null && devaluation.getSequence() != null) {
            devaluation.setCreateTime(new Date().getTime());

            Devaluation devaQuery = new Devaluation();
            devaQuery.setTypes(devaluation.getTypes());
            devaQuery.setDevaluationId(devaluation.getDevaluationId());

            List<Devaluation> devaluations = devaluationMapper.select(devaQuery);
            if (devaluations.size() > 0) {
                return MapUtils.buildErrorMap(Constants.DATA_ALREADY_EXISTS, "首推数据已存在");
            }

            Devaluation devaQuerySeq = new Devaluation();
            devaQuerySeq.setTypes(devaluation.getTypes());
            devaQuerySeq.setSequence(devaluation.getSequence());
            List<Devaluation> selectSeq = devaluationMapper.select(devaQuerySeq);
            if (selectSeq.size() > 0) {
                return MapUtils.buildErrorMap(Constants.DATA_ALREADY_EXISTS, "当前当前排序已存在数据");
            }

            int insert = devaluationMapper.insert(devaluation);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "首推成功", null);
            } else {
                return MapUtils.buildErrorMap(Constants.ERROR, "首推失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }

    @Override
    public Map<String, Object> insertAppUserDeva(Devaluation devaluation) {
        if (devaluation.getTypes() != null && devaluation.getDevaluationId() != null && devaluation.getSequence() != null) {
            devaluation.setCreateTime(new Date().getTime());

            Devaluation devaQuery = new Devaluation();
            devaQuery.setTypes(devaluation.getTypes());
            devaQuery.setDevaluationId(devaluation.getDevaluationId());

            List<Devaluation> devaluations = devaluationMapper.select(devaQuery);
            if (devaluations.size() > 0) {
                return MapUtils.buildErrorMap(Constants.DATA_ALREADY_EXISTS, "首推数据已存在");
            }

            Devaluation devaQuerySeq = new Devaluation();
            devaQuerySeq.setTypes(devaluation.getTypes());
            devaQuerySeq.setSequence(devaluation.getSequence());
            List<Devaluation> selectSeq = devaluationMapper.select(devaQuerySeq);
            if (selectSeq.size() > 0) {
                Devaluation _devaluation = selectSeq.get(0);
                _devaluation.setDevaluationId(devaluation.getDevaluationId());
                int result = devaluationMapper.updateByPrimaryKey(_devaluation);
                if (result > 0) {
                    return MapUtils.buildSuccessMap(Constants.SUCCESS, "首推成功", null);
                } else {
                    return MapUtils.buildErrorMap(Constants.ERROR, "首推失败");
                }
            }

            int insert = devaluationMapper.insert(devaluation);
            if (insert > 0) {
                return MapUtils.buildSuccessMap(Constants.SUCCESS, "首推成功", null);
            } else {
                return MapUtils.buildErrorMap(Constants.ERROR, "首推失败");
            }
        } else {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失");
        }
    }
}
