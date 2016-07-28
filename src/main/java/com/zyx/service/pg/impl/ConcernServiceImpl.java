package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.PgConstants;
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

    @Override
    public Map<String, Object> findById(Integer id) {
        Optional.ofNullable(id).orElse(0);
//        concernM
        Concern concern = concernMapper.findById(id);
        if (concern != null) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_34000, PgConstants.PG_ERROR_CODE_34000_MSG, concern);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Integer result = concernMapper.setState(-1, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_37000, PgConstants.PG_ERROR_CODE_37000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> setVisible(Integer id) {
        Integer result = concernMapper.setState(-2, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_38000, PgConstants.PG_ERROR_CODE_38000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }
}
