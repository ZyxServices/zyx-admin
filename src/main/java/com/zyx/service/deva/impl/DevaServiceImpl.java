package com.zyx.service.deva.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.DevaMapper;
import com.zyx.model.Devaluation;
import com.zyx.parm.deva.DevaParam;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.deva.DevaService;
import com.zyx.utils.MapUtils;
import com.zyx.vo.deva.DevaVo;
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

    public DevaServiceImpl() {
        super(Devaluation.class);
    }

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
    public List<DevaVo> getDevaList(Integer model, Integer area) {
        DevaParam devaParam =new DevaParam();
        devaParam.setModel(model);
        devaParam.setArea(area);
        return devaMapper.selectDevaList(devaParam);
    }

    @Override
    public List<Integer> getUsedSequence(Integer model, Integer area) {
        return devaMapper.selectUsedSequence(model,area);
    }

    @Override
    public List<Integer> selectModelIds(Integer area, Integer model) {
        DevaParam param =new DevaParam();
        param.setArea(area);
        param.setModel(model);
        return devaMapper.queryModelIds(param);
    }

    @Override
    public void cascadeDelete(Integer area,Integer model,Integer modelId) {
        if(model==null||modelId==null)
            return;

        devaMapper.cascadeDelete(new DevaParam(area,model,modelId));
    }

    @Override
    public void cascadeDelete(Integer model, Integer modelId) {
        cascadeDelete(null,model,modelId);
    }
}
