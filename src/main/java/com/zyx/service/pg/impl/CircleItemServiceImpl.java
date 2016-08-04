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
import java.util.*;

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

    @Override
    public Map<String, Object> addCircleItem(Integer circle_id, Integer create_id, String title, String content) {
        try {
            CircleItem circleItem = new CircleItem();
            Optional.ofNullable(circle_id).ifPresent(circleItem::setCircleId);
            if (circle_id == null) {
//                map.put(Constants.STATE, PgConstants.PG_ERROR_CODE_30001);
//                map.put(Constants.ERROR_MSG, PgConstants.PG_ERROR_CODE_30001_MSG);
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30001, PgConstants.PG_ERROR_CODE_30001_MSG);
            }
            Optional.ofNullable(create_id).ifPresent(circleItem::setCreateId);
            if (create_id == null) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30005, PgConstants.PG_ERROR_CODE_30005_MSG);
//                map.put(Constants.STATE, PgConstants.PG_ERROR_CODE_30005);
//                map.put(Constants.ERROR_MSG, PgConstants.PG_ERROR_CODE_30005_MSG);
////                Constants.MSG_PARAM_ERROR
//                return map;
            }
            Optional.ofNullable(title).ifPresent(circleItem::setTitle);
            if (title == null) {
//                map.put(Constants.STATE, PgConstants.PG_ERROR_CODE_30006);
//                map.put(Constants.ERROR_MSG, PgConstants.PG_ERROR_CODE_30006_MSG);
//                return map;
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30006, PgConstants.PG_ERROR_CODE_30006_MSG);
            }
            Optional.ofNullable(content).ifPresent(circleItem::setContent);
            if (content == null) {
//                map.put(Constants.STATE, PgConstants.PG_ERROR_CODE_30007);
//                map.put(Constants.ERROR_MSG, PgConstants.PG_ERROR_CODE_30007_MSG);
//                return map;
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30007, PgConstants.PG_ERROR_CODE_30007_MSG);

            }
            circleItem.setState(0);
            circleItem.setCreateTime(new Date().getTime());
            save(circleItem);
            return MapUtils.buildSuccessMap(Constants.SUCCESS, PgConstants.PG_ERROR_CODE_33000_MSG, null);
        } catch (Exception e) {
            e.printStackTrace();
            return PgConstants.MAP_500;
        }
    }

    @Override
    public Map<String, Object> findOne(Integer id) {
        CircleItem circleItem = circleItemMapper.findOne(id);
        if (circleItem != null) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_34000, PgConstants.PG_ERROR_CODE_34000_MSG, circleItem);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);

    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Integer result = circleItemMapper.setVisible(-1, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_37000, PgConstants.PG_ERROR_CODE_37000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);

    }

    @Override
    public Map<String, Object> setVisible(Integer id, Integer state) {
        Integer result = circleItemMapper.setVisible(state, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_38000, PgConstants.PG_ERROR_CODE_38000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);

    }

    @Override
    public Map<String, Object> search(Integer start, Integer pageSize, String searchText) {
        if (Objects.equals(searchText, null) || Objects.equals(searchText, "")) {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, Constants.MSG_PARAM_MISS);
        }
        List<CircleItem> circleItems = circleItemMapper.search(searchText, start * pageSize, pageSize);
        return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_34000, PgConstants.PG_ERROR_CODE_34000_MSG, circleItems);
    }
}
