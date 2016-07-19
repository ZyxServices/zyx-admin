package com.zyx.controller.appuser;

import com.zyx.parm.QueryAppUserParam;
import com.zyx.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * Created by wms on 2016/7/15.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserController.java
 */
@Controller("appUserController")
@RequestMapping("/v1/appUser")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView query(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QueryAppUserParam param = new QueryAppUserParam();
        param.setPageSize(pageSize);
        param.setPageNumber(pageNumber);
        Map<String, Object> map = appUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
