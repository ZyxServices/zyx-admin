package com.zyx.service.deva;

import com.zyx.model.Devaluation;
import com.zyx.service.BaseService;
import com.zyx.vo.deva.DevaVo;

import java.util.List;
import java.util.Map;

/**
 * Created by SubDong on 16-7-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title DevaluationService
 * @update 16-7-12 上午10:30
 */
public interface DevaService extends BaseService<Devaluation>{

    /**
     * 获取首推
     * @param model
     * @return
     */
    List<Devaluation> getDevas(Integer model);
    /**
     *
     * @param model
     * @param area
     * @return
     */
    List<Devaluation> getDevas(Integer model, Integer area);

    List<DevaVo> getDevaList(Integer model, Integer area);

    List<Integer> getUsedSequence(Integer model, Integer area);
    List<Integer> selectModelIds(Integer area,Integer model);

    void cascadeDelete(Integer area,Integer model,Integer modelId);
    void cascadeDelete(Integer model,Integer modelId);
}
