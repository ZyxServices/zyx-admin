package com.zyx.service.pg;

import com.zyx.model.CircleItem;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg
 * Create by XiaoWei on 2016/7/25
 */
public interface CircleItemService extends BaseService<CircleItem> {
    Map<String, Object> findByPager(Integer start, Integer pageSize, Integer circleId);

    Map<String, Object> addCircleItem(Integer circle_id, Integer create_id, String title, String content);

    Map<String, Object> findOne(Integer id);

    Map<String, Object> deleteOne(Integer id);

    Map<String, Object> setVisible(Integer id, Integer state);

    Map<String, Object> search(Integer start, Integer pageSize, String searchText);

    Map<String, Object> editCircleItem(Integer circleItemId, String title, String content);

    Map<String, Object> deleteByIds(String ids);


}
