package com.zyx.service.devaluation;

import com.zyx.model.Devaluation;

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
public interface DevaluationService {

    /**
     * 首推设置
     *
     * @param devaluation
     * @return
     */
    Map<String, Object> insertActivityDeva(Devaluation devaluation);

    /**
     * 用户首推设置
     *
     * @param devaluation
     * @return
     */
    Map<String, Object> insertAppUserDeva(Devaluation devaluation);
}
