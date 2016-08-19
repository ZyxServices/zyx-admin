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

    Map<String, Object> findById(Integer id);

    Map<String, Object> deleteOne(Integer id);

    Map<String, Object> setVisible(Integer id, Integer state);

    Map<String, Object> edit(String topicContent, String imgUrl, Integer id);

    Map<String, Object> createConcern(String content, Integer createId, Integer type, Integer topVisible, String dbImgPath);

    Map<String, Object> search(Integer start, Integer pageSize, String userName);
    /**
     *
     * @param fromId
     * @param fromType 目前3个模块用到，1：直播，2活动，3帖子
     * @param formObj
     * @return
     */
    Integer fromConcern(Integer fromId, Integer fromType,Object formObj);
}
