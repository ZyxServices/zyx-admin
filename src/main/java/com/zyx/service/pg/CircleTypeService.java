package com.zyx.service.pg;

import com.zyx.model.CircleType;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg
 * Create by XiaoWei on 2016/8/2
 */
public interface CircleTypeService extends BaseService<CircleType> {
    Map<String, Object> createCircleType(String typeName);
    Map<String,Object> circleTypeList();
    Map<String,Object> setState(Integer id,Integer state);

}
