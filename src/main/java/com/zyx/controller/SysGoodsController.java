package com.zyx.controller;

import com.zyx.model.SysRole;
import com.zyx.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 文楷 on 2016/7/14.
 */
@Controller
@RequestMapping("/shop")
public class SysGoodsController {
    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ModelAndView goAddRole() {
        ModelAndView view = new ModelAndView("/shop/goods");
        return view;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView roles() {
        ModelAndView view = new ModelAndView("/shop/order");
        List<SysRole> roles = sysRoleService.getSysRole(null);
        view.addObject("roles", roles);
        return view;
    }
}
