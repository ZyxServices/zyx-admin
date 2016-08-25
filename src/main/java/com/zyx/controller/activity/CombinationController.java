package com.zyx.controller.activity;

import com.zyx.constants.Constants;
import com.zyx.model.Combination;
import com.zyx.service.activity.CombinationService;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SubDong on 16-7-29.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title CombinationController
 * @package com.zyx.controller.activity
 * @update 16-7-29 下午2:23
 */
@Controller
@RequestMapping("/v1/combination")
public class CombinationController {

    @Resource
    private CombinationService combinationService;

    @RequestMapping(value = "/createCombination", method = RequestMethod.POST)
    @ApiOperation(value = "添加组合", notes = "添加组合")
    public ModelAndView createCombination(@RequestParam(name = "name", required = true) String name,
                                          @RequestParam(name = "image", required = true) String image,
                                          @RequestParam(name = "activityIds", required = false) Integer[] activityIds) {

        AbstractView jsonView = new MappingJackson2JsonView();

        /*String uploadFile = FileUploadUtils.uploadFile(image);

        Map<String, Object> verify = ImagesVerifyUtils.verify(uploadFile);*/

        if (image == null || image.equals("")) {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
            return new ModelAndView(jsonView);
        }
        Map<String, Object> map = combinationService.createCombination(name, image, activityIds);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);

    }

    @RequestMapping(value = "/updateCombination", method = RequestMethod.POST)
    @ApiOperation(value = "修改组合", notes = "修改组合")
    public ModelAndView updateCombination(@RequestParam(name = "id", required = true) Integer id,
                                          @RequestParam(name = "name", required = false) String name,
                                          @RequestParam(name = "image", required = false) String images,
                                          @RequestParam(name = "activityIds", required = false) Integer[] activityIds) {

        AbstractView jsonView = new MappingJackson2JsonView();
        if (images == null || images.equals("")) {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.PARAM_MISS, "参数缺失"));
            return new ModelAndView(jsonView);
        }
        Combination combination = new Combination();
        combination.setId(id);
        combination.setName(name);
        combination.setImage(images);
        Map<String, Object> map = combinationService.updateCombination(combination, activityIds);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value = "/queryCombination", method = RequestMethod.POST)
    @ApiOperation(value = "组合查询", notes = "组合查询")
    public ModelAndView queryActivity(@RequestParam(name = "pageDataNum", required = true) Integer pageDataNum,
                                      @RequestParam(name = "pageNum", required = true) Integer pageNum,
                                      @RequestParam(name = "search", required = false) String search) {

        AbstractView jsonView = new MappingJackson2JsonView();


        Map<String, Object> map = combinationService.queryCombination(pageDataNum, pageNum, search);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value = "/queryCombinationActivity", method = RequestMethod.POST)
    @ApiOperation(value = "组合对应活动查询", notes = "组合对应活动查询")
    public ModelAndView queryCombinationActivity(@RequestParam(name = "pageDataNum", required = true) Integer pageDataNum,
                                                 @RequestParam(name = "pageNum", required = true) Integer pageNum,
                                                 @RequestParam(name = "combinationId", required = true) Integer combinationId) {

        AbstractView jsonView = new MappingJackson2JsonView();


        Map<String, Object> map = combinationService.queryCombinationActivity(pageDataNum, pageNum, combinationId);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryCombinationById", method = RequestMethod.POST)
    @ApiOperation(value = "通过id查询组合", notes = "通过id查询组合")
    public ModelAndView queryCombinationById(@RequestParam(name = "combinationId", required = true) Integer id) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = combinationService.queryCombinationById(id);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/queryCombinationIdByActivity", method = RequestMethod.POST)
    @ApiOperation(value = "通过组合id查询组合下的活动", notes = "通过组合id查询组合下的活动")
    public ModelAndView queryCombinationIdByActivity(@RequestParam(name = "combinationId", required = true) Integer id) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = combinationService.queryCombinationIdByActivity(id);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delCombination", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动", notes = "删除活动")
    public ModelAndView delCombination(@RequestParam(name = "combinationId", required = true) Integer id,
                                       @RequestParam(name = "delType", required = true) int del) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Combination combination = new Combination();
        combination.setId(id);
        combination.setDel(del);
        Map<String, Object> map = combinationService.updateCombination(combination, null);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/maskCombination", method = RequestMethod.POST)
    @ApiOperation(value = "屏幕活动", notes = "屏幕活动")
    public ModelAndView maskCombination(@RequestParam(name = "combinationId", required = true) Integer id,
                                        @RequestParam(name = "mask", required = true) int mask) {

        AbstractView jsonView = new MappingJackson2JsonView();
        Combination combination = new Combination();
        combination.setId(id);
        combination.setMask(mask);
        Map<String, Object> map = combinationService.updateCombination(combination, null);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
