package com.zyx.service.pg;

import com.zyx.model.Circle;
import com.zyx.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg
 * Create by XiaoWei on 2016/7/25
 */
public interface CircleService extends BaseService<Circle> {
    Map<String, Object> findByPager(Integer start, Integer pageSize);

    Map<String, Object> insertCircle(String title, Integer createId, Integer state, Integer type, String details, String headImgUrl, Integer masterId, String adminIds);

    Map<String, Object> findById(Integer id);

    Map<String, Object> deleteOne(Integer id);

    Map<String, Object> setVisible(Integer id, Integer state);

    Map<String, Object> search(Integer start, Integer pageSize, String title);

    Map<String, Object> editCircle(Integer circleId, String title, String file, Integer circleType, String details, Integer masterId, String adminIds);

    Map<String,Object> delByIds(String ids);

}
