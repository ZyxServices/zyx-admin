package com.zyx.service.pg.impl;

import com.zyx.constants.PgConstants;
import com.zyx.mapper.CircleTypeMapper;
import com.zyx.model.CircleType;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.CircleTypeService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg.impl
 * Create by XiaoWei on 2016/8/2
 */
@Service
public class CircleTypeServiceImpl extends BaseServiceImpl<CircleType> implements CircleTypeService {

    @Resource
    private CircleTypeMapper circleTypeMapper;

    public CircleTypeServiceImpl() {
        super(CircleType.class);
    }

    @Override
    public Map<String, Object> createCircleType(String typeName) {
        if (Objects.equals(typeName, null) || Objects.equals(typeName, "")) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30024, PgConstants.PG_ERROR_CODE_30024_MSG);
        }
        CircleType circleTypeFind=circleTypeMapper.findByTypeName(typeName);
        if(circleTypeFind!=null){
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30028, PgConstants.PG_ERROR_CODE_30028_MSG);
        }

        CircleType circleType = new CircleType();
        circleType.setState(0);
        circleType.setTypeName(typeName);
        Integer result = circleTypeMapper.insert(circleType);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_33000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> circleTypeList() {
        List<CircleType> circleTypes = circleTypeMapper.findAll();
        return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, circleTypes);
    }

    @Override
    public Map<String, Object> setState(Integer id, Integer state) {
        if (Objects.equals(state, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30020, PgConstants.PG_ERROR_CODE_30020_MSG);
        }
        if (!Objects.equals(id, null) || !Objects.equals(id, "")) {
            Integer result = circleTypeMapper.setState(id, state);
            if (result > 0) {
                return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_39000_MSG, null);
            }
        } else {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30025, PgConstants.PG_ERROR_CODE_30025_MSG);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }
}
