package com.zyx.controller.activity;

import com.zyx.constants.Constants;
import com.zyx.model.Activity;
import com.zyx.service.activity.ActivityService;
import com.zyx.service.deva.DevaService;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

/**
 * Created by SubDong on 16-7-12.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title ActivityController
 * @package com.zyx.controller.activity
 * @update 16-7-12 下午2:27
 */
@Controller
@RequestMapping("/v1/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;
    @Autowired
    DevaService devaService;

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    @ApiOperation(value = "活动发布", notes = "活动发布")
    public ModelAndView release(@RequestParam(name = "userId", required = true) Integer userId,
                                @RequestParam(name = "title", required = true) String title,
                                @RequestParam(name = "desc", required = true) String desc,
                                @RequestParam(name = "image", required = true) String image,
                                @RequestParam(name = "startTime", required = true) String startTime,//转时间戳
                                @RequestParam(name = "endTime", required = true) String endTime,//转时间戳
                                @RequestParam(name = "lastTime", required = true) String lastTime,//转时间戳
                                @RequestParam(name = "maxPeople", required = false) Integer maxPeople,
                                @RequestParam(name = "visible", required = false) Integer visible,
                                @RequestParam(name = "phone", required = false) String phone,
                                @RequestParam(name = "price", required = false) Double price,
                                @RequestParam(name = "type", required = true) Integer type,
                                @RequestParam(name = "address", required = false) String address,
                                @RequestParam(name = "examine", required = false) Integer examine,
                                @RequestParam(name = "memberTemplate", required = false) String memberTemplate) {

        AbstractView jsonView = new MappingJackson2JsonView();

        if (image == null || image.equals("")) {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
            return new ModelAndView(jsonView);
        }

        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setTitle(title);
        activity.setDescContent(desc);

        activity.setImgUrls(image);
        activity.setStartTime(getDateTime(startTime));
        activity.setEndTime(getDateTime(endTime));
        activity.setLastTime(getDateTime(lastTime));
        activity.setMaxPeople(maxPeople != null ? maxPeople : 9999);
        activity.setVisible(visible != null ? visible : 0);
        activity.setPhone(phone);
        activity.setPrice(price != null ? price : 0);
        activity.setType(type);
        activity.setAddress(address);
        activity.setExamine(examine == null ? 0 : examine);
        activity.setMemberTemplate(memberTemplate);

        Map<String, Object> map = activityService.insertActivity(activity);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "活动修改", notes = "活动修改")
    public ModelAndView update(@RequestParam(name = "id", required = true) Integer id,
                               @RequestParam(name = "userId", required = true) Integer userId,
                               @RequestParam(name = "title", required = false) String title,
                               @RequestParam(name = "desc", required = false) String desc,
                               @RequestParam(name = "image", required = false) String image,
                               @RequestParam(name = "startTime", required = false) String startTime,//转时间戳
                               @RequestParam(name = "endTime", required = false) String endTime,//转时间戳
                               @RequestParam(name = "lastTime", required = false) String lastTime,//转时间戳
                               @RequestParam(name = "maxPeople", required = false) Integer maxPeople,
                               @RequestParam(name = "visible", required = false) Integer visible,
                               @RequestParam(name = "phone", required = false) String phone,
                               @RequestParam(name = "price", required = false) Double price,
                               @RequestParam(name = "type", required = false) Integer type,
                               @RequestParam(name = "address", required = false) String address,
                               @RequestParam(name = "examine", required = false) Integer examine,
                               @RequestParam(name = "memberTemplate", required = false) String memberTemplate) {

        AbstractView jsonView = new MappingJackson2JsonView();

        if (image == null || image.equals("")) {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
            return new ModelAndView(jsonView);
        }

        Activity activity = new Activity();
        activity.setId(id);
        activity.setUserId(userId);
        activity.setTitle(title);
        activity.setDescContent(desc);
        activity.setImgUrls(image);
        activity.setStartTime(getDateTime(startTime));
        activity.setEndTime(getDateTime(endTime));
        activity.setLastTime(getDateTime(lastTime));
        activity.setMaxPeople(maxPeople);
        activity.setVisible(visible);
        activity.setPhone(phone);
        activity.setPrice(price);
        activity.setType(type);
        activity.setAddress(address);
        activity.setExamine(examine);
        activity.setMemberTemplate(memberTemplate);

        Map<String, Object> map = activityService.updateActivity(activity);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryActivity", method = RequestMethod.GET)
    @ApiOperation(value = "活动查询", notes = "活动查询")
    public ModelAndView queryActivity(@RequestParam(name = "pageDataNum", required = true) Integer pageDataNum,
                                      @RequestParam(name = "pageNum", required = true) Integer pageNum,
                                      @RequestParam(name = "search", required = false) String search) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.queryActivity(pageDataNum, pageNum, search);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryActivityById", method = RequestMethod.POST)
    @ApiOperation(value = "通过id查询活动", notes = "通过id查询活动")
    public ModelAndView queryActivityById(@RequestParam(name = "activityId", required = true) Integer activityId) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.queryActivityById(activityId);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/maskActivity", method = RequestMethod.POST)
    @ApiOperation(value = "活动屏蔽", notes = "活动屏蔽")
    public ModelAndView maskActivity(@RequestParam(name = "id", required = true) Integer id,
                                     @RequestParam(name = "maskType", required = true) Integer maskType) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.maskActivity(id, maskType);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delActivity", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动", notes = "删除活动")
    public ModelAndView delActivity(@RequestParam(name = "id", required = true) String id,
                                    @RequestParam(name = "delType", required = true) Integer delType) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.delActivity(id, delType);
        if (map.get("state").equals("200")) {
            String[] ids = id.split(",");
            for (String s : ids) {
                devaService.cascadeDelete(Constants.MODEL_ACTIVITY, Integer.valueOf(s));
            }
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    private Long getDateTime(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.parse(time != null && !Objects.equals(time, "") ? time : "0").getTime();
        } catch (ParseException e) {
            try {
                SimpleDateFormat formatters = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return formatters.parse(time != null && !Objects.equals(time, "") ? time : "0").getTime();
            } catch (ParseException e1) {
                e1.printStackTrace();
                return 0L;

            }
        }
    }
}
