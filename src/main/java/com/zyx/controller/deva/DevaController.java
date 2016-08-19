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
    @ApiOperation(value = "活动接口-活动推荐", notes = "活动接口-活动推荐")
    public ModelAndView queryActivity(@RequestParam(name = "types") Integer types,
                                      @RequestParam(name = "devaId") Integer devaluationId,
                                      @RequestParam(name = "image", required = false) MultipartFile image,
                                      @RequestParam(name = "sequence") Integer sequence,
                                      @RequestParam(name = "activation") Integer activation) {

        AbstractView jsonView = new MappingJackson2JsonView();

        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView appUserDeva(@RequestParam(name = "types") Integer types,
                                    @RequestParam(name = "devaId") Integer devaluationId,
                                    @RequestParam(name = "sequence") Integer sequence,
                                    @RequestParam(name = "activation") Integer activation) {

        AbstractView jsonView = new MappingJackson2JsonView();
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/list/model", method = RequestMethod.POST)
    public ModelAndView getDevasByModel(@RequestParam(name = "model",required = true) Integer model ,@RequestParam(name = "area") Integer area ) {
        Map<String, Object> result = new HashMap<>();
        List<Devaluation> list = devaService.getDevas(model, area);
        result.put(Constants.DATA,list);
        result.put(Constants.STATE, LiveConstants.SUCCESS);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(result);
        return new ModelAndView(jsonView);
    }
}
