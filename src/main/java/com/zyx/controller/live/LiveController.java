package com.zyx.controller.live;

import com.zyx.common.enums.LiveLabEnum;
import com.zyx.constants.LiveConstants;
import com.zyx.model.LiveInfo;
import com.zyx.model.LiveLab;
import com.zyx.parm.live.LiveInfoParm;
import com.zyx.service.live.LiveInfoService;
import com.zyx.service.live.LiveLabService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MrDeng on 2016/7/19.
 */
@Controller
@RequestMapping(path = "/v1/live")
//@Api(description = "直播相关接口")
public class LiveController {

    @Autowired
    LiveLabService liveLabService;
    @Autowired
    LiveInfoService liveInfoService;
    @RequestMapping(path = "/lab/create",method = {RequestMethod.POST})
    @ApiOperation(value = "添加直播标签************", notes = "直播-添加直播标签")
    public ModelAndView addLiveLab(@RequestParam(name = "lab", required = true) String lab, @RequestParam(name = "desc", required = false) String desc) {
        Map<String, Object> result = new HashMap<>();
        if (null == lab || lab.isEmpty()) {
            result.put(LiveConstants.STATE, LiveConstants.ERROR_LIVE_NULL_LAB);
            result.put(LiveConstants.ERROR_MSG, LiveConstants.ERROR_MSG_LIVE_NULL_LAB);
        } else {
            LiveLab liveLab = liveLabService.getByLab(lab);
            if (liveLab == null) {
                liveLab = new LiveLab();
                liveLab.setLab(lab.trim());
                liveLab.setDescription(desc.trim());
                liveLab.setCreateTime(System.currentTimeMillis());
                liveLab.setState(LiveLabEnum.USING.getStatus());
                liveLabService.addLiveLab(liveLab);
                result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            } else {
                result.put(LiveConstants.STATE, LiveConstants.ERROR_LIVE_EXIST_LAB);
                result.put(LiveConstants.ERROR_MSG, LiveConstants.ERROR_MSG_LIVE_EXIST_LAB);
            }
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(path = "/lab/list",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "获取直播标签列表", notes = "直播-获取直播标签列表")
    public ModelAndView getLiveLabList() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<LiveLab> labs = liveLabService.getAllLabs();
            result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            result.put(LiveConstants.DATA, labs);
        }catch (Exception e){
            e.printStackTrace();
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(path = "/lab/delete",method = {RequestMethod.POST})
    @ApiOperation(value = "删除直播标签", notes = "直播-删除直播标签")
    public ModelAndView deleteLiveLab(@RequestParam(name = "lab", required = true) Integer id) {
        Map<String, Object> result = new HashMap<>();
        if (null == id) {
            result.put(LiveConstants.STATE, LiveConstants.PARAM_MISS);
            result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_MISS);
        } else {
            liveLabService.deleteLiveLab(id);
            result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(path = "/lab/update",method = {RequestMethod.POST})
    @ApiOperation(value = "更新直播标签", notes = "直播-更新直播标签")
    public ModelAndView updateLiveLab(@RequestParam(name = "id", required = true) Integer id,
                                      @RequestParam(name = "lab", required = false) String lab,
                                      @RequestParam(name = "desc", required = false) String desc,
                                      @RequestParam(name = "state", required = false) Integer state) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (id==null||((null == lab || lab.isEmpty()) && (null == desc || desc.isEmpty())&&null == state)) {
                result.put(LiveConstants.STATE, LiveConstants.PARAM_MISS);
                result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_MISS);
            } else {
                LiveLab liveLab = new LiveLab();
                liveLab.setId(id);
                liveLab.setLab(lab.trim());
                liveLab.setLab(desc.trim());
                liveLab.setState(state);
                liveLabService.updateLiveLab(liveLab);
                result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }
    @RequestMapping(path = "/list",method = {RequestMethod.GET})
    @ApiOperation(value = "获取直播", notes = "直播-获取直播")
    public ModelAndView getLiveInfos(@RequestParam(name = "pageSize", required = true)Integer pageSize, @RequestParam(name = "pageNumber", required = false) Integer pageNumber) {
        Map<String, Object> result = new HashMap<>();
        LiveInfoParm param =new LiveInfoParm();
        param.setPageNumber(pageNumber);
        param.setPageSize(pageSize);
        List<LiveInfo> liveInfos = liveInfoService.getLiveInfos(param);
        result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
        result.put(LiveConstants.DATA,liveInfos);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

}
