package com.zyx.controller.devaluation;

import com.zyx.model.Devaluation;
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
import java.util.HashMap;
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
@RequestMapping("/deva")
public class Devaluationcontroller {

    @Resource
    private DevaluationService devaluationService;

    @RequestMapping(value = "/queryActivity", method = RequestMethod.POST)
    @ApiOperation(value = "活动接口", notes = "活动发布")
    public ModelAndView queryActivity(@RequestParam(name = "types", required = true) Integer types,
                                      @RequestParam(name = "devaId", required = true) Integer devaluationId,
                                      @RequestParam(name = "image", required = false) MultipartFile image,
                                      @RequestParam(name = "sequence", required = true) Integer sequence,
                                      @RequestParam(name = "activation", required = true) Integer activation) {

        AbstractView jsonView = new MappingJackson2JsonView();

        Devaluation devaluation = new Devaluation();
        devaluation.setTypes(types);
        devaluation.setDevaluationId(devaluationId);
        if(image != null){
            String uploadFile = FileUploadUtils.uploadFile(image);
            devaluation.setImage(uploadFile);
        }

        devaluation.setSequence(sequence);
        devaluation.setActivation(activation);

        Map<String, Object> map = devaluationService.insterActivityDeva(devaluation);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
