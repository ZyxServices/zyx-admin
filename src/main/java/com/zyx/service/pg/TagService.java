package com.zyx.service.pg;

import com.zyx.model.Tag;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg
 * Create by XiaoWei on 2016/8/2
 */
public interface TagService extends BaseService<Tag> {
    Map<String, Object> findAll();

    Map<String, Object> createTag(String tagName);

    Map<String, Object> setState(Integer id, Integer state);
}
