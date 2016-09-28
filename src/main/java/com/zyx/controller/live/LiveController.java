package com.zyx.controller.live;

import com.zyx.common.enums.LiveLabEnum;
import com.zyx.constants.Constants;
import com.zyx.constants.LiveConstants;
import com.zyx.model.LiveInfo;
import com.zyx.model.LiveLab;
import com.zyx.parm.live.LiveInfoParm;
import com.zyx.service.deva.DevaService;
import com.zyx.service.live.LiveInfoService;
import com.zyx.service.live.LiveLabService;
import com.zyx.vo.live.LiveInfoVo;
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
    @Autowired
    DevaService devaService;

    @RequestMapping(path = "/lab/create", method = {RequestMethod.POST})
    @ApiOperation(value = "添加直播标签", notes = "直播-添加直播标签")
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
                liveLab.setDescription(desc==null?null:desc.trim());
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

    @RequestMapping(path = "/lab/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "获取直播标签列表", notes = "直播-获取直播标签列表")
    public ModelAndView getLiveLabList() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<LiveLab> labs = liveLabService.getAllLabs();
            result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            result.put(LiveConstants.DATA, labs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(path = "/lab/delete", method = {RequestMethod.POST})
    @ApiOperation(value = "删除直播标签", notes = "直播-删除直播标签")
    public ModelAndView deleteLiveLab(@RequestParam(name = "id", required = true) Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (null == id) {
                result.put(LiveConstants.STATE, LiveConstants.PARAM_MISS);
                result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_MISS);
            } else {
                liveLabService.deleteLiveLab(id);
                result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(path = "/lab/update", method = {RequestMethod.POST})
    @ApiOperation(value = "更新直播标签", notes = "直播-更新直播标签")
    public ModelAndView updateLiveLab(@RequestParam(name = "id", required = true) Integer id,
                                      @RequestParam(name = "lab", required = false) String lab,
                                      @RequestParam(name = "desc", required = false) String desc,
                                      @RequestParam(name = "state", required = false) Integer state) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (id == null || ((null == lab || lab.isEmpty()) && (null == desc || desc.isEmpty()) && null == state)) {
                result.put(LiveConstants.STATE, LiveConstants.PARAM_MISS);
                result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_MISS);
            } else {
                LiveLab liveLab = new LiveLab();
                liveLab.setId(id);
                liveLab.setLab(lab==null?null:lab.trim());
                liveLab.setDescription(desc==null?null:desc.trim());
                liveLab.setState(state);
                liveLabService.updateLiveLab(liveLab);
                result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(path = "/list", method = {RequestMethod.GET})
    @ApiOperation(value = "获取直播", notes = "直播-获取直播")
    public ModelAndView getLiveInfos(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                     @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(name = "searchText", required = false) String searchText) {
        Map<String, Object> result = new HashMap<>();
        LiveInfoParm param = new LiveInfoParm();
        param.setPageNumber(pageNumber);
        param.setPageSize(pageSize);
        int count = liveInfoService.selectCount(new LiveInfo());
        List<LiveInfo> liveInfos = liveInfoService.getLiveInfos(param);

        result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
        result.put(LiveConstants.DATA, liveInfos);
        result.put("total",count);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }
    @RequestMapping(path = "/search", method = {RequestMethod.GET})
    @ApiOperation(value = "搜索直播", notes = "直播-搜索直播")
    public ModelAndView searchLiveInfos(@RequestParam(name = "keyword", required = false) String keyword) {
        Map<String, Object> result = new HashMap<>();
        try{
            List<LiveInfoVo> liveInfos =liveInfoService.search(keyword);
            result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            result.put(LiveConstants.DATA, liveInfos);
        }catch (Exception e){
            e.printStackTrace();
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "直播发布", notes = "直播-直播发布")
    public ModelAndView createLive(
            @RequestParam(name = "auth") Integer auth, @RequestParam(name = "type") Integer type,
            @RequestParam(name = "start", required = false) Long start,
            @RequestParam(name = "end", required = false) Long end, @RequestParam(name = "title") String title,
            @RequestParam(name = "lab") Integer lab, @RequestParam(name = "bgmUrl", required = false) String bgmUrl) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (type == null || title == null || "".equals(title) || lab == null) {// 判断参数必要性
                result.put(LiveConstants.STATE, LiveConstants.PARAM_MISS);
                result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_MISS);
            } else if (!(type == 1 || type == 2) || !(lab == 1 || lab == 2 || lab == 3 || lab == 4)
                    || !(auth == 1 || auth == 2 || auth == 3 || auth == 4)) {// 判断参数合法性
                result.put(LiveConstants.STATE, LiveConstants.PARAM_ILIGAL);
                result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_ILIGAL);
            } else {
                LiveInfo liveInfo = new LiveInfo();
                // 系统补全参数
                liveInfo.setCreateTime(System.currentTimeMillis());
                liveInfo.setAuth(auth);
                // 传入参数构造
                liveInfo.setType(type);
                liveInfo.setStart(start);
                liveInfo.setEnd(end);
                liveInfo.setTitle(title);
                liveInfo.setLab(lab);
                liveInfo.setState(0);
                // 不必须字段
                liveInfo.setBgmUrl(bgmUrl);
                liveInfoService.addLiveInfo(liveInfo);
                result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "直播更新修改", notes = "直播-直播更新修改")
    public ModelAndView updateLive(@RequestParam(name = "id") Integer id,
                                   @RequestParam(name = "isPublic", required = false) Boolean isPublic,
                                   @RequestParam(name = "type", required = false) Integer type,
                                   @RequestParam(name = "start", required = false) Long start,
                                   @RequestParam(name = "end", required = false) Long end,
                                   @RequestParam(name = "title", required = false) String title,
                                   @RequestParam(name = "lab", required = false) Integer lab,
                                   @RequestParam(name = "bgmUrl", required = false) String bgmUrl,
                                   @RequestParam(name = "vedioUrl", required = false) String vedioUrl,
                                   @RequestParam(name = "state", required = false) Integer state) {
        Map<String, Object> result = new HashMap<>();
        if (id == null) {
            result.put(LiveConstants.STATE, LiveConstants.PARAM_ILIGAL);
            result.put(LiveConstants.ERROR_MSG, LiveConstants.MSG_PARAM_ILIGAL);
        } else {
            LiveInfo liveInfo = new LiveInfo();
            liveInfo.setId(id);
            // 传入参数构造
            // 传入参数构造
            liveInfo.setType(type);
            liveInfo.setTitle(title);
            liveInfo.setLab(lab);
            // 不必须字段
            liveInfo.setStart(start == null ? System.currentTimeMillis() : start);
            liveInfo.setEnd(end == null ? System.currentTimeMillis() : end);
            liveInfo.setBgmUrl(bgmUrl);
            liveInfo.setVedioUrl(vedioUrl);
            liveInfo.setState(state);
            // 系统补全参数
            liveInfoService.updateNotNull(liveInfo);
            result.put(LiveConstants.STATE, LiveConstants.SUCCESS);
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "直播更新修改", notes = "直播-直播更新修改")
    public ModelAndView deleteLive(@RequestParam(name = "id", required = true) Integer id) {
        Map<String, Object> attrMap = new HashMap<>();
        // 系统补全参数
        liveInfoService.delete(id);
        devaService.cascadeDelete(Constants.MODEL_LIVE,id);
        attrMap.put(LiveConstants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(attrMap);
        return new ModelAndView(jsonView);
    }
}
