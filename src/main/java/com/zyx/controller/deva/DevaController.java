package com.zyx.controller.deva;

import com.zyx.constants.Constants;
import com.zyx.constants.LiveConstants;
import com.zyx.model.Devaluation;
import com.zyx.service.deva.DevaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SubDong on 16-7-21.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title Devaluationcontroller
 * @package com.zyx.controller.devaluation
 * @update 16-7-21 上午11:56
 */
@Controller
@RequestMapping("/v1/deva")
public class DevaController {

    @Resource
    private DevaService devaService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "首推接口-新增首推", notes = "首推接口-新增首推")
    public ModelAndView queryActivity(@RequestParam(name = "model",required = true) Integer model,
                                      @RequestParam(name = "modelId",required = true) Integer modelId,
                                      @RequestParam(name = "area" ,required = true) Integer area,
                                      @RequestParam(name = "imageUrl", required = false) String imageUrl,
                                      @RequestParam(name = "state", required = true) Integer state,
                                      @RequestParam(name = "sequence" ,required = true) Integer sequence
                                     ) {
        Map<String, Object> result = new HashMap<>();
        Devaluation entity = new Devaluation();
        entity.setModel(model);
        entity.setModelId(modelId);
        entity.setCreateTime(System.currentTimeMillis());
        entity.setArea(area);
        entity.setImage(imageUrl);
        entity.setSequence(sequence);
        entity.setState(state);
        devaService.save(entity);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "首推接口-更新首推", notes = "首推接口-更新首推")
    public ModelAndView updateDeva(@RequestParam(name = "id",required = true) Integer id,
                                    @RequestParam(name = "area" ,required = false) Integer area,
                                    @RequestParam(name = "imageUrl", required = false) String imageUrl,
                                    @RequestParam(name = "state", required = false) Integer state,
                                    @RequestParam(name = "sequence" ,required = false) Integer sequence) {

        Map<String, Object> result = new HashMap<>();
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
    public ModelAndView deleteDeva(@RequestParam(name = "id",required = true) Integer id
                                    ) {
        Map<String, Object> result = new HashMap<>();
        devaService.delete(id);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "首推接口-获取首推", notes = "首推接口-获取首推")
    public ModelAndView getDevasByModel(@RequestParam(name = "model",required = true) Integer model ,@RequestParam(name = "area",required = false) Integer area ) {
        Map<String, Object> result = new HashMap<>();
        List<Devaluation> list = devaService.getDevas(model, area);
        System.out.println(list +"  "+ list.size());
        result.put(Constants.DATA,list);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }
}
