package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.PgConstants;
import com.zyx.mapper.ConcernMapper;
import com.zyx.model.Activity;
import com.zyx.model.CircleItem;
import com.zyx.model.Concern;
import com.zyx.model.LiveInfo;
import com.zyx.model.vo.ConcernVo;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.ConcernService;
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
public class ConcernServiceImpl extends BaseServiceImpl<Concern> implements ConcernService {
    @Resource
    private ConcernMapper concernMapper;

    public ConcernServiceImpl() {
        super(Concern.class);
    }

    @Override
    public Map<String, Object> findByPager(int start, int pageSize) {

        Optional.ofNullable(start).orElse(0);
        Optional.ofNullable(pageSize).orElse(0);
        List<ConcernVo> concerns = concernMapper.findByPager(start * pageSize, pageSize);
        Integer count = concernMapper.count();
//        Map<String, Object> totalMap = new HashMap<>();
//        totalMap.put("total", count);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "成功", concerns, new HashMap() {{
            put("total", count);
        }});
        return map;
    }

    @Override
    public Map<String, Object> findById(Integer id) {
        Optional.ofNullable(id).orElse(0);
//        concernM
        Concern concern = concernMapper.findById(id);
        if (concern != null) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, concern);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> deleteOne(Integer id) {
        Integer result = concernMapper.setState(-1, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_37000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> setVisible(Integer id, Integer state) {
        Integer result = concernMapper.setState(state, id);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_39000_MSG, null);
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> edit(String topicContent, String imgUrl, Integer id) {
        if (id == null) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30021, PgConstants.PG_ERROR_CODE_30021_MSG);
        }
        Concern concernFind = concernMapper.findById(id);
        if (id != null) {
            if (topicContent == null) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30022, PgConstants.PG_ERROR_CODE_30022_MSG);
            }
            Optional.ofNullable(topicContent).ifPresent(concernFind::setTopicContent);
//            if (imgUrl == null || Objects.equals(topicContent, "")) {
//                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30013, PgConstants.PG_ERROR_CODE_30013_MSG);
//            }
//            Optional.ofNullable(imgUrl).ifPresent(concernFind::setImgUrl);
            concernFind.setImgUrl(imgUrl);
            Integer result = concernMapper.edit(topicContent, imgUrl, id);
            if (result > 0) {
                return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_36000_MSG, null);
            }
        }
        return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG);
    }

    @Override
    public Map<String, Object> createConcern(String content, Integer createId, Integer type, Integer topVisible, String dbImgPath) {
        if (Objects.equals(content, null) || Objects.equals(content, "")) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30010, PgConstants.PG_ERROR_CODE_30010_MSG);
        }
        if (Objects.equals(createId, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30005, PgConstants.PG_ERROR_CODE_30005_MSG);
        }
        if (Objects.equals(type, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30017, PgConstants.PG_ERROR_CODE_30017_MSG);
        }
        if (Objects.equals(topVisible, null)) {
            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30023, PgConstants.PG_ERROR_CODE_30023_MSG);
        }
//        if (Objects.equals(dbImgPath, null) || Objects.equals(dbImgPath, "")) {
//            return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30013, PgConstants.PG_ERROR_CODE_30013_MSG);
//        }
        Concern concern = new Concern();
        concern.setCreateTime(new Date().getTime());
        concern.setTopicContent(content);
        concern.setTopicVisible(topVisible);
        concern.setType(type);
        concern.setUserId(createId);
        concern.setState(0);//默认正常
        concern.setImgUrl(dbImgPath);
        Integer result = concernMapper.insert(concern);
        if (result > 0) {
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_33000_MSG, null);
        }
        return MapUtils.buildSuccessMap(PgConstants.PG_ERROR_CODE_35000, PgConstants.PG_ERROR_CODE_35000_MSG, null);
    }

    @Override
    public Map<String, Object> search(Integer start, Integer pageSize, String userName) {
        if (Objects.equals(userName, null) || Objects.equals(userName, "")) {
            return MapUtils.buildErrorMap(Constants.PARAM_MISS, Constants.MSG_PARAM_MISS);
        }
        List<Concern> concerns = concernMapper.search(userName, start * pageSize, pageSize);
        return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, concerns);
    }

    @Override
    public Integer fromConcern(Integer fromId, Integer fromType, Object fromObj) {
        if (Objects.equals(fromId, null)) {
            return 0;
        }
        if (Objects.equals(fromType, null)) {
            return 0;
        }
        if (Objects.equals(fromObj, null)) {
            return 0;
        }
        switch (fromType) {
            case 1:
                //直播
                LiveInfo liveInfo = (LiveInfo) fromObj;
                Concern concern = new Concern();
                concern.setFromId(fromId);
                concern.setFromType(1);
                concern.setType(5);
                concern.setCreateTime(new Date().getTime());
                if (!Objects.equals(liveInfo.getBgmUrl(), null)) {
                    concern.setImgUrl(liveInfo.getBgmUrl());//设置动态图片为直播的背景图片
                }
                concern.setTopicTitle(liveInfo.getTitle());
                concern.setTopicVisible(1);
                concern.setUserId(liveInfo.getUserId());
                concern.setState(0);
                return concernMapper.insert(concern);
            case 2:
                //活动
                Activity activity = (Activity) fromObj;
                Concern concernActivity = new Concern();
                concernActivity.setFromId(fromId);
                concernActivity.setFromType(fromType);
                concernActivity.setType(2);
                concernActivity.setCreateTime(new Date().getTime());
                if (!Objects.equals(activity.getImgUrls(), null)) {
                    concernActivity.setImgUrl(activity.getImgUrls());
                }
                concernActivity.setTopicTitle(activity.getTitle());
                concernActivity.setTopicVisible(1);
                concernActivity.setUserId(activity.getUserId());
                concernActivity.setState(0);
                return concernMapper.insert(concernActivity);
            case 3:
                //帖子
                CircleItem circleItem = (CircleItem) fromObj;
                Concern concernItem = new Concern();
                concernItem.setFromId(fromId);
                concernItem.setFromType(fromType);
                concernItem.setType(6);
                concernItem.setCreateTime(new Date().getTime());
                concernItem.setTopicTitle(circleItem.getTitle());
                concernItem.setTopicVisible(1);
                concernItem.setUserId(circleItem.getCreateId());
                concernItem.setState(0);
                return concernMapper.insert(concernItem);
        }
        return 0;
    }
}
