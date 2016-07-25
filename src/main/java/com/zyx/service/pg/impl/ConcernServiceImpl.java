package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.ConcernMapper;
import com.zyx.model.Concern;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.ConcernService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg.impl
 * Create by XiaoWei on 2016/7/25
 */
@Service
public class ConcernServiceImpl extends BaseServiceImpl<Concern> implements ConcernService {
    @Resource
    private ConcernMapper concernMapper;

    @Override
    public Map<String, Object> findByPager(int start, int pageSize) {

        Optional.ofNullable(start).orElse(0);
        Optional.ofNullable(pageSize).orElse(0);
        List<Concern> concerns = concernMapper.findByPager(start * pageSize, pageSize);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", concerns);
        return map;
    }
}
