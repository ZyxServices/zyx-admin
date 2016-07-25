package com.zyx.service.pg;

import com.zyx.model.Concern;
import com.zyx.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg
 * Create by XiaoWei on 2016/7/25
 */
public interface ConcernService extends BaseService<Concern> {
    Map<String, Object> findByPager(int start, int pageSize);
}
