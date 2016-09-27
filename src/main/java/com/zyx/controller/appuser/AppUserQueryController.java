package com.zyx.controller.appuser;

import com.zyx.constants.Constants;
import com.zyx.parm.QueryAppUserParam;
import com.zyx.service.AppUserService;
import com.zyx.service.deva.DevaService;
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
 * @title AppUserQueryController.java
 */
@Controller("appUserQueryController")
@RequestMapping("/v1/appUser")
public class AppUserQueryController {

    @Autowired
    AppUserService appUserService;
    @Autowired
    DevaService devaService;
    @RequestMapping(value = "/list/all", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam(required = false) String searchText, @RequestParam(required = false) String sortName, @RequestParam(required = false) String sortOrder) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QueryAppUserParam param = new QueryAppUserParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        param.setSearchText(searchText);
        param.setSortName(sortName);
        param.setSortOrder(sortOrder);
        Map<String, Object> map = appUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/list/yrz", method = RequestMethod.GET)
    public ModelAndView yrzList(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam(required = false) String searchText, @RequestParam(required = false) String sortName, @RequestParam(required = false) String sortOrder) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QueryAppUserParam param = new QueryAppUserParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        param.setSearchText(searchText);
        param.setSortName(sortName);
        param.setSortOrder(sortOrder);
        param.setAuthenticate(2);// 已认证用户
        Map<String, Object> map = appUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/list/dsh", method = RequestMethod.GET)
    public ModelAndView dshList(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam(required = false) String searchText, @RequestParam(required = false) String sortName, @RequestParam(required = false) String sortOrder) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QueryAppUserParam param = new QueryAppUserParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        param.setSearchText(searchText);
        param.setSortName(sortName);
        param.setSortOrder(sortOrder);
        param.setAuthenticate(1);// 待审核用户
        param.setOfficial(null);
        Map<String, Object> map = appUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/list/official", method = RequestMethod.GET)
    public ModelAndView officialList(@RequestParam Integer pageSize, @RequestParam Integer pageNumber, @RequestParam(required = false) String searchText, @RequestParam(required = false) String sortName, @RequestParam(required = false) String sortOrder) {
        AbstractView jsonView = new MappingJackson2JsonView();
        QueryAppUserParam param = new QueryAppUserParam();
        param.setPageSize(pageSize);
        param.setPageNumber((pageNumber - 1) * pageSize);
        param.setSearchText(searchText);
        param.setSortName(sortName);
        param.setSortOrder(sortOrder);
        param.setOfficial(1);
        Map<String, Object> map = appUserService.queryList(param);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/list/official/all", method = RequestMethod.GET)
    public ModelAndView officialListAll() {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.queryOfficialAccountList();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public ModelAndView del(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.del(id);
        devaService.cascadeDelete(Constants.MODEL_LIVE,id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/unDel", method = RequestMethod.GET)
    public ModelAndView unDel(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.unDel(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/mask", method = RequestMethod.GET)
    public ModelAndView mask(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.mask(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/unMask", method = RequestMethod.GET)
    public ModelAndView unMask(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.unMask(id);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/authPass", method = RequestMethod.GET)
    public ModelAndView authPass(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.authAppUser(id, 2);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/authFail", method = RequestMethod.GET)
    public ModelAndView authFail(@RequestParam Integer id) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appUserService.authAppUser(id, 3);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
