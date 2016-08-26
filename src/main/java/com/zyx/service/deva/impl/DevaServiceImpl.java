package com.zyx.service.deva.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.DevaMapper;
import com.zyx.model.Devaluation;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.deva.DevaService;
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
public class DevaServiceImpl extends BaseServiceImpl<Devaluation> implements DevaService {

    @Resource
    DevaMapper devaMapper;
    @Resource
    protected RedisTemplate<String, String> stringRedisTemplate;

    @Override
    public List<Devaluation> getDevas(Integer model) {
        return getDevas(model,null);
    }
    @Override
    public List<Devaluation> getDevas(Integer model, Integer area) {
        Devaluation record =  new Devaluation();
        record.setModel(model);
        record.setArea(area);
        return devaMapper.select(record);
    }

    @Override
    public List<Integer> getUsedSequence(Integer model, Integer area) {
        return devaMapper.selectUsedSequence(model,area);
    }
}
