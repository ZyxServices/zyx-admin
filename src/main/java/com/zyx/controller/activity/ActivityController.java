package com.zyx.controller.activity;

import com.zyx.model.Activity;
import com.zyx.service.activity.ActivityService;
import com.zyx.service.devaluation.DevaluationService;
import com.zyx.utils.FileUploadUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.HashMap;
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
    private DevaluationService devaluationService;

    @Resource
    private ActivityService activityService;

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    @ApiOperation(value = "活动接口", notes = "活动发布")
    public ModelAndView release(@RequestParam(name = "token", required = true) String token,
                                @RequestParam(name = "createId", required = true) Integer createId,
                                @RequestParam(name = "title", required = true) String title,
                                @RequestParam(name = "desc", required = true) String desc,
                                @RequestPart(name = "image", required = true) MultipartFile image,
                                @RequestParam(name = "startTime", required = true) Long startTime,
                                @RequestParam(name = "endTime", required = true) Long endTime,
                                @RequestParam(name = "lastTime", required = true) Long lastTime,
                                @RequestParam(name = "maxPeople", required = true) Integer maxPeople,
                                @RequestParam(name = "visible", required = true) Integer visible,
                                @RequestParam(name = "phone", required = true) String phone,
                                @RequestParam(name = "price", required = false) Double price,
                                @RequestParam(name = "type", required = true) Integer type,
                                @RequestParam(name = "address", required = false) String address,
                                @RequestParam(name = "examine", required = false) Integer examine,
                                @RequestParam(name = "memberTemplate", required = false) String memberTemplate) {

        AbstractView jsonView = new MappingJackson2JsonView();


        Map<String, Object> map = new HashMap<>();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "活动接口", notes = "活动修改")
    public ModelAndView update(@RequestParam(name = "id", required = true) Integer id,
                               @RequestParam(name = "userId", required = true) Integer userId,
                               @RequestParam(name = "title", required = false) String title,
                               @RequestParam(name = "desc", required = false) String desc,
                               @RequestPart(name = "image", required = false) MultipartFile image,
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

        long newStartTime = 0;
        long newEndTime = 0;
        long newLastTime = 0;
        String newImage = null;
        try {
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
            newStartTime = dateFormat.parse(startTime != null && !Objects.equals(startTime, "") ? startTime : "0").getTime();
            newEndTime = dateFormat.parse(endTime != null && !Objects.equals(endTime, "") ? endTime : "0").getTime();
            newLastTime = dateFormat.parse(lastTime != null && !Objects.equals(lastTime, "") ? lastTime : "0").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (image != null && !image.isEmpty()) {
            newImage = FileUploadUtils.uploadFile(image);
        }

        Activity activity = new Activity();
        activity.setId(id);
        activity.setUserId(userId);
        activity.setTitle(title);
        activity.setDescContent(desc);
        activity.setImgUrls(newImage);
        activity.setStartTime(newStartTime);
        activity.setEndTime(newEndTime);
        activity.setLastTime(newLastTime);
        activity.setMaxPeople(maxPeople);
        activity.setVisible(visible);
        activity.setPhone(phone);
        activity.setPrice(price);
        activity.setType(type);
        activity.setAddress(address);
        activity.setExamine(examine);
        activity.setMemberTemplate(memberTemplate);

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.updateActivity(activity);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryActivity", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "活动接口", notes = "活动发布")
    public ModelAndView queryActivity(@RequestParam(name = "pageDataNum", required = true) Integer pageDataNum,
                                      @RequestParam(name = "pageNum", required = true) Integer pageNum,
                                      @RequestParam(name = "search", required = false) String search) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.queryActivity(pageDataNum, pageNum, search);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryActivityById", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "活动接口", notes = "活动发布")
    public ModelAndView queryActivityById(@RequestParam(name = "activityId", required = true) Integer activityId) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.queryActivityById(activityId);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/maskActivity", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "活动接口", notes = "活动屏蔽")
    public ModelAndView maskActivity(@RequestParam(name = "id", required = true) Integer id,
                                     @RequestParam(name = "maskType", required = true) Integer maskType) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.maskActivity(id,maskType);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delActivity", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "活动接口", notes = "活动屏蔽")
    public ModelAndView delActivity(@RequestParam(name = "id", required = true) Integer id,
                                    @RequestParam(name = "delType", required = true) Integer delType) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Map<String, Object> map = activityService.delActivity(id,delType);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
