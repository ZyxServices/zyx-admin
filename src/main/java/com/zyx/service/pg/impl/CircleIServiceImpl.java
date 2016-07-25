package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.CircleMapper;
import com.zyx.model.Circle;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.CircleService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CircleIServiceImpl extends BaseServiceImpl<Circle> implements CircleService {
    @Resource
    private CircleMapper circleMapper;

    @Override
    public Map<String, Object> findByPager(Integer start, Integer pageSize) {
        Optional.ofNullable(start).orElse(0);
        Optional.ofNullable(pageSize).orElse(0);
        List<Circle> circles = circleMapper.findByPager(start * pageSize, pageSize);
        return MapUtils.buildSuccessMap(Constants.SUCCESS, "查询成功", circles);
    }
}
