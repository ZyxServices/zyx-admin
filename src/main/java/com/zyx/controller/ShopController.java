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
public class ShopController {
    @Autowired

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ModelAndView goods() {
        ModelAndView view = new ModelAndView("/shop/goods");
        return view;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView order() {
        ModelAndView view = new ModelAndView("/shop/order");
        return view;
    }

}
