package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.PgConstants;
import com.zyx.mapper.CircleMapper;
import com.zyx.mapper.DevaluationMapper;
import com.zyx.model.Circle;
import com.zyx.model.Devaluation;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.CircleService;
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
public class CircleIServiceImpl extends BaseServiceImpl<Circle> implements CircleService {
    @Resource
    private CircleMapper circleMapper;

    @Resource
    private DevaluationMapper devaluationMapper;

    @Override
    public Map<String, Object> findByPager(Integer start, Integer pageSize) {
        Optional.ofNullable(start).orElse(0);
        Optional.ofNullable(pageSize).orElse(0);
        List<Circle> circles = circleMapper.findByPager(start * pageSize, pageSize);
        return MapUtils.buildSuccessMap(Constants.SUCCESS, "查询成功", circles);
    }

    @Override
    public Map<String, Object> insertCircle(String title, Integer createId, Integer state, Integer type, String details, String headImgUrl, Integer masterId, String adminIds) {
        try {
            Circle insertCircle = new Circle();
            if (title == null || Objects.equals(title, "")) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30006, PgConstants.PG_ERROR_CODE_30006_MSG);
            }
            Optional.ofNullable(title).ifPresent(insertCircle::setTitle);
            if (createId == null || Objects.equals(createId, "")) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30005, PgConstants.PG_ERROR_CODE_30005_MSG);
            }
            Optional.ofNullable(createId).ifPresent(insertCircle::setCreateId);
//            if (state == null) {
//                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30020, PgConstants.PG_ERROR_CODE_30020_MSG);
//            }
           /* if (state == null) {
                map.put(Constants.STATE, PgConstants.PG_ERROR_CODE_30011);
                map.put(Constants.ERROR_MSG, PgConstants.PG_ERROR_CODE_30011_MSG);
                return map;
            }*/
            Optional.ofNullable(state).ifPresent(insertCircle::setState);
            if (type == null) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30012, PgConstants.PG_ERROR_CODE_30012_MSG);

            }
            Optional.ofNullable(type).ifPresent(insertCircle::setType);
            if (details == null || Objects.equals(details, "")) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30010, PgConstants.PG_ERROR_CODE_30010_MSG);
            }
            Optional.ofNullable(details).ifPresent(insertCircle::setDetails);
//            if (headImgUrl == null || Objects.equals(headImgUrl, "")) {
//                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30013, PgConstants.PG_ERROR_CODE_30013_MSG);
//            }
            Optional.ofNullable(headImgUrl).ifPresent(insertCircle::setHeadImgUrl);
            if (masterId == null || Objects.equals(masterId, "")) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30019, PgConstants.PG_ERROR_CODE_30019_MSG);
            }
            Optional.ofNullable(masterId).ifPresent(insertCircle::setCircleMasterId);
            Optional.ofNullable(adminIds).ifPresent(insertCircle::setAdminIds);
            insertCircle.setCreateTime(new Date().getTime());
            insertCircle.setState(0);
            mapper.insert(insertCircle);
//            map.put(Constants.STATE, PgConstants.SUCCESS);
//            map.put(Constants.SUCCESS_MSG, PgConstants.MSG_SUCCESS);
//            return map;
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_33000_MSG, null);

        } catch (Exception e) {
            e.printStackTrace();
            return PgConstants.MAP_500;
        }
    }

    @Override
    public Map<String, Object> findById(Integer id) {
        Optional.ofNullable(id).orElse(0);
        Circle circle = circleMapper.findById(id);
        if (circle != null) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_34000_MSG);
    }

    @Override
    public Map<String, Object> tuiJian(Integer circel_id, Integer topSize) {
        if (circel_id == null) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30001, PgConstants.PG_ERROR_CODE_30001_MSG);
        }
        if (topSize == null) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30018, PgConstants.PG_ERROR_CODE_30018_MSG);
        }
        Integer result = circleMapper.tuiJian(circel_id, topSize);
        if (result > 0) {
            Devaluation devaluation = new Devaluation();
            devaluation.setCreateTime(new Date().getTime());
            devaluation.setActivation(0);
            devaluation.setDevaluationId(circel_id);
            devaluation.setSequence(topSize);
            devaluation.setTypes(3);
            devaluationMapper.insert(devaluation);
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_36000, PgConstants.PG_ERROR_CODE_36000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Integer result = circleMapper.setState(-1, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_37000, PgConstants.PG_ERROR_CODE_37000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> setVisible(Integer id) {
        Integer result = circleMapper.setState(-2, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_38000, PgConstants.PG_ERROR_CODE_38000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }
}
