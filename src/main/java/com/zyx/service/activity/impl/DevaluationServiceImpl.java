package com.zyx.service.activity.impl;

import com.zyx.mapper.DevaluationMapper;
import com.zyx.model.Devaluation;
import com.zyx.service.BaseService;
import com.zyx.service.activity.DevaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class DevaluationServiceImpl extends BaseService<DevaluationMapper,Devaluation> implements DevaluationService {

    @Resource
    DevaluationMapper devaluationMapper;


}
