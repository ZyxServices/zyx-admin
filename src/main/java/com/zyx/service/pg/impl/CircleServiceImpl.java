package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.PgConstants;
import com.zyx.mapper.AppUserMapper;
import com.zyx.mapper.CircleMapper;
import com.zyx.mapper.DevaMapper;
import com.zyx.model.Circle;
import com.zyx.model.vo.CircleVo;
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
public class CircleServiceImpl extends BaseServiceImpl<Circle> implements CircleService {
    @Resource
    private CircleMapper circleMapper;
    @Resource
    private AppUserMapper appUserMapper;

    @Resource
    private DevaMapper devaMapper;

    public CircleServiceImpl() {
        super(Circle.class);
    }

    @Override
    public Map<String, Object> findByPager(Integer start, Integer pageSize) {
        Optional.ofNullable(start).orElse(0);
        Optional.ofNullable(pageSize).orElse(0);
        List<CircleVo> circles = circleMapper.findByPager(start * pageSize, pageSize);
        circles.forEach(e ->{
            if(e.getAdminIds() != null){
                String[] strings = e.getAdminIds().split(",");
                List<Integer> integers = new ArrayList<>();
                for (String string : strings) {
                    integers.add(Integer.valueOf(string));
                }
                List<String> strings1 = appUserMapper.queryAppUserByName(integers);
                String replace = strings1.toString().replace("[","").replace("]","");
                e.setAdminIds(replace);
            }
        });
        Integer count = circleMapper.searchCount();
        Map<String, Object> countHas = new HashMap<>();
        countHas.put("total", count);
        return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, circles, countHas);
    }

    @Override
    public Map<String, Object> insertCircle(String title, Integer createId, Integer state, Integer circleType, String details, String headImgUrl, Integer masterId, String adminIds) {
        try {
            Circle insertCircle = new Circle();
            if (title == null || Objects.equals(title, "")) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30006, PgConstants.PG_ERROR_CODE_30006_MSG);
            }
            Circle circleFind = circleMapper.existCircle(title);
            if (circleFind != null) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30028, PgConstants.PG_ERROR_CODE_30028_MSG);
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
            if (circleType == null) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30012, PgConstants.PG_ERROR_CODE_30012_MSG);

            }
            Optional.ofNullable(circleType).ifPresent(insertCircle::setCircleType);
            if (details == null || Objects.equals(details, "")) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30025, PgConstants.PG_ERROR_CODE_30025_MSG);
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
            insertCircle.setType(0);
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
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, circle);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_34000_MSG);
    }

//    @Override
//    public Map<String, Object> tuiJian(Integer circel_id, Integer topSize) {
//        if (circel_id == null) {
//            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30001, PgConstants.PG_ERROR_CODE_30001_MSG);
//        }
//        if (topSize == null) {
//            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30018, PgConstants.PG_ERROR_CODE_30018_MSG);
//        }
//        Integer result = circleMapper.tuiJian(circel_id, topSize);
//        if (result > 0) {
//            Devaluation devaluation = new Devaluation();
//            devaluation.setCreateTime(new Date().getTime());
//            devaluation.setActivation(0);
//            devaluation.setDevaluationId(circel_id);
//            devaluation.setSequence(topSize);
//            devaluation.setModel(3);
//            devaluation.setActivation(1);
//            devaluation.setArea(1);
//            devaMapper.insert(devaluation);
//            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_36000_MSG, null);
//        }
//        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
//    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Integer result = circleMapper.setState(-1, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_37000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> setVisible(Integer id, Integer state) {
        Integer result = circleMapper.setState(state, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_39000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> search(Integer start, Integer pageSize, String title) {
        if (Objects.equals(title, null) || Objects.equals(title, "")) {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, Constants.MSG_PARAM_MISS);
        }
        List<Circle> circles = circleMapper.search(title, start * pageSize, pageSize);
        return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, circles);
    }

    @Override
    public Map<String, Object> editCircle(Integer circleId, String title, String headImg, Integer circleType, String details, Integer masterId, String adminIds) {
        Circle circleFind = circleMapper.findById(circleId);
        if (circleFind != null) {
            circleFind.setTitle(title);
            if (!Objects.equals(headImg, "")) {
                circleFind.setHeadImgUrl(headImg);
            } else {
                circleFind.setHeadImgUrl("");
            }
            circleFind.setCircleType(circleType);
            circleFind.setDetails(details);
            circleFind.setCircleMasterId(masterId);
            circleFind.setAdminIds(adminIds);
            Integer result = circleMapper.editCircle(circleFind);
            if (result > 0) {
                return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_36000_MSG, null);
            }
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> delByIds(String ids) {
        if (Objects.equals(ids, null) || Objects.equals(ids, "")) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30025, PgConstants.PG_ERROR_CODE_30025_MSG);
        }
        try {
            if (ids.contains(",")) {
                String[] idList = ids.split(",");
                Integer result = circleMapper.deleteByIds(idList);
                if (result > 0) {
                    return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_39000_MSG, null);
                }
            } else {
                Integer result = circleMapper.setState(-1, Integer.valueOf(ids));
                if (result > 0) {
                    return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_39000_MSG, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return PgConstants.MAP_500;
        }
        return PgConstants.MAP_500;
    }
}
