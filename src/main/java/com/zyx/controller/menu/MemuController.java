package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MrDeng on 2016/7/19.
 */
@Controller
@RequestMapping(path="menu")
public class MemuController {
    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public ModelAndView redirectActivity() {
        return  new ModelAndView("/activity/list");
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView redirectAppUser() {
        return new ModelAndView("/appUser/userIndex");
    }

}
