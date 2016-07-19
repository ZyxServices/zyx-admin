package com.zyx.controller.live;

import com.zyx.constants.LiveConstants;
import com.zyx.model.LiveLab;
import com.zyx.service.live.LiveLabService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class LiveController {

    @Autowired
    LiveLabService liveLabService;

    @RequestMapping(path = "/lab/create")
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
                liveLab.setDesc(desc.trim());
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

    @RequestMapping(path = "/lab/list")
    public ModelAndView getLiveLabList() {
        Map<String, Object> result = new HashMap<>();
        List<LiveLab> labs = liveLabService.getAllLabs();
        result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
        result.put(LiveConstants.DATA, labs);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(path = "/lab/delete")
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

    @RequestMapping(path = "/lab/update")
    public ModelAndView deleteLiveLab(@RequestParam(name = "id", required = false) Integer id, @RequestParam(name = "lab", required = false) String lab, @RequestParam(name = "desc", required = false) String desc) {
        Map<String, Object> result = new HashMap<>();
        if (id==null||((null == lab || lab.isEmpty()) && (null == desc || desc.isEmpty()))) {
            result.put(LiveConstants.STATE, LiveConstants.PARAM_MISS);
            result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_MISS);
        } else {
            LiveLab liveLab = new LiveLab();
            liveLab.setId(id);
            liveLab.setLab(lab.trim());
            liveLab.setLab(desc.trim());
            liveLabService.updateLiveLab(liveLab);
            result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }


}
