package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.PgConstants;
import com.zyx.mapper.CircleItemMapper;
import com.zyx.model.CircleItem;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.CircleItemService;
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
public class CircleItemServiceImpl extends BaseServiceImpl<CircleItem> implements CircleItemService {

    @Resource
    private CircleItemMapper circleItemMapper;

    @Override
    public Map<String, Object> findByPager(Integer start, Integer pageSize) {
        Optional.ofNullable(start).orElse(0);
        Optional.ofNullable(pageSize).orElse(0);
        List<CircleItem> circleItems = circleItemMapper.findByPager(start * pageSize, pageSize);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, circleItems);
        return map;
    }
}
