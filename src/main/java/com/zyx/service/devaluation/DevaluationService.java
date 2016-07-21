package com.zyx.service.devaluation;

import com.zyx.constants.Constants;
import com.zyx.model.Devaluation;
import com.zyx.utils.MapUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by SubDong on 16-7-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title DevaluationService
 * @package com.github.ichenkaihua.service.activity
 * @update 16-7-12 上午10:30
 */
public interface DevaluationService {

    /**
     * 首推设置
     * @param devaluation
     * @return
     */
    Map<String, Object> insterActivityDeva(Devaluation devaluation);

}
