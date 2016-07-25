package com.zyx.service.pg;

import com.zyx.model.Circle;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg
 * Create by XiaoWei on 2016/7/25
 */
public interface CircleService extends BaseService<Circle> {
    Map<String, Object> findByPager(Integer start, Integer pageSize);
}
