package com.zyx.controller.activity;

import com.zyx.service.activity.CombinationService;
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
                                          @RequestPart(name = "image", required = true) MultipartFile image,
                                          @RequestParam(name = "activityIds", required = false) Integer[] activityIds) {

        AbstractView jsonView = new MappingJackson2JsonView();

        String uploadFile = FileUploadUtils.uploadFile(image);

        Map<String, Object> map = combinationService.createCombination(name, uploadFile, activityIds);
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

}
