package com.zyx.controller.deva;

import com.zyx.constants.Constants;
import com.zyx.constants.DevaContants;
import com.zyx.constants.LiveConstants;
import com.zyx.model.Devaluation;
import com.zyx.service.deva.DevaService;
import com.zyx.vo.deva.DevaVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/deva")
@Api(description = "首推相关接口")
public class DevaController {

    @Resource
    private DevaService devaService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "首推接口-新增首推", notes = "首推接口-新增首推，同模块同展示区域 模块ID唯一")
    public ModelAndView queryActivity(
            @ApiParam(required = true, name = "model", value = "模块类型（1活动，2直播，3圈子，4帖子，5动态，6用户，7系统）") @RequestParam(name = "model", required = true) Integer model,
            @ApiParam(required = true, name = "modelId", value = "模块ID") @RequestParam(name = "modelId", required = true) Integer modelId,
            @ApiParam(required = true, name = "area", value = "展示区域（1首页，2看台，3精选圈子）") @RequestParam(name = "area", required = true) Integer area,
            @ApiParam(required = false, name = "imageUrl", value = "图片地址") @RequestParam(name = "imageUrl", required = false) String imageUrl,
            @ApiParam(required = true, name = "state", value = "状态（0-未激活，1-激活）") @RequestParam(name = "state", required = true) Integer state,
            @ApiParam(required = true, name = "sequence", value = "展示顺序") @RequestParam(name = "sequence", required = true) Integer sequence
    ) {
        Map<String, Object> result = new HashMap<>();
        Integer sSize = DevaContants.DEVA_AREA_MAX_ITEM.get(model + "_" + area);
        if (sSize == null) {
            result.put(Constants.STATE, DevaContants.DEVA_NOT_EXIST_MODEL_AREA);
            result.put(Constants.ERROR_MSG, DevaContants.MSG_DEVA_NOT_EXIST_MODEL_AREA);
        } else {
            //Redis判断目前条数是否满
            Devaluation entity = new Devaluation();
            entity.setModel(model);
            entity.setArea(area);
            entity.setModelId(modelId);
            List<Devaluation> dblist = devaService.select(entity);
            if (dblist != null && !dblist.isEmpty()) {
                result.put(Constants.STATE, DevaContants.DEVA_REPEAT);
                result.put(Constants.ERROR_MSG, DevaContants.MSG_DEVA_REPEAT);
            } else {
                entity.setCreateTime(System.currentTimeMillis());
                entity.setArea(area);
                entity.setImage(imageUrl);
                entity.setSequence(sequence);
                entity.setState(state);
                int n = devaService.save(entity);
                result.put(Constants.STATE, LiveConstants.SUCCESS);
            }
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "首推接口-更新首推", notes = "首推接口-更新首推")
    public ModelAndView updateDeva(
            @ApiParam(required = true, name = "id", value = "推荐ID") @RequestParam(name = "id", required = true) Integer id,
            @ApiParam(required = false, name = "area", value = "展示区域（1首页，2精选圈子）") @RequestParam(name = "area", required = false) Integer area,
            @ApiParam(required = false, name = "imageUrl", value = "图片地址") @RequestParam(name = "imageUrl", required = false) String imageUrl,
            @ApiParam(required = true, name = "state", value = "状态（0-未激活，1-激活）") @RequestParam(name = "state", required = true) Integer state,
            @ApiParam(required = true, name = "sequence", value = "展示顺序") @RequestParam(name = "sequence", required = true) Integer sequence) {

        Map<String, Object> result = new HashMap<>();
        //判断目前条数是否满
        Devaluation entity = new Devaluation();
        entity.setId(id);
        entity.setArea(area);
        entity.setImage(imageUrl);
        entity.setSequence(sequence);
        entity.setState(state);
        devaService.updateNotNull(entity);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "首推接口-删除首推", notes = "首推接口-删除首推")
    public ModelAndView deleteDeva(@ApiParam(required = true, name = "id", value = "推荐ID") @RequestParam(name = "id", required = true) Integer id
    ) {
        Map<String, Object> result = new HashMap<>();
        devaService.delete(id);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "首推接口-获取首推", notes = "首推接口-获取首推")
    public ModelAndView getDevasByModel(
            @ApiParam(required = true, name = "model", value = "模块类型（1活动，2直播，3圈子，4帖子，5动态，6用户，7系统）") @RequestParam(name = "model", required = true) Integer model,
            @ApiParam(required = true, name = "area", value = "展示区域（1首页，2精选圈子）") @RequestParam(name = "area", required = true) Integer area) {
        Map<String, Object> result = new HashMap<>();
        List<DevaVo> list = devaService.getDevaList(model, area);
        result.put(Constants.DATA, list);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/sequence", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "首推接口-获取未使用的顺序列表", notes = "首推接口-获取未使用的顺序列表")
    public ModelAndView getUnusedSquen(
            @ApiParam(required = true, name = "model", value = "模块类型（1活动，2直播，3圈子，4帖子，5动态，6用户，7系统）") @RequestParam(name = "model", required = true) Integer model,
            @ApiParam(required = true, name = "area", value = "展示区域（1首页，2精选圈子）") @RequestParam(name = "area", required = true) Integer area) {
        Map<String, Object> result = new HashMap<>();
        Integer sSize = DevaContants.DEVA_AREA_MAX_ITEM.get(model + "_" + area);
        if (sSize == null) {
            result.put(Constants.STATE, DevaContants.DEVA_NOT_EXIST_MODEL_AREA);
            result.put(Constants.ERROR_MSG, DevaContants.DEVA_NOT_EXIST_MODEL_AREA);
        } else {
            List<Integer> list = devaService.getUsedSequence(model, area);
            List<Integer> seqList = new ArrayList<>();
            int i = 1;
            if (list != null && !list.isEmpty()) {
                for (Integer temp : list) {
                    if (temp != null) {
                        for (; i < temp; i++) {
                            seqList.add(i);
                        }
                        i++;
                    }
                }
            }
            for (; i <= sSize; i++) {
                seqList.add(i);
            }
            result.put(Constants.DATA, seqList);
            result.put(Constants.STATE, LiveConstants.SUCCESS);
        }
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    private void refreshDevaRedis(Integer model, Integer area) {

    }

}
