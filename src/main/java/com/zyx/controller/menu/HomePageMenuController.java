package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZYX on 2016/7/14.
 */
@Controller
@RequestMapping("/homepage")
public class HomePageMenuController {
    @RequestMapping(value = "/banner", method = RequestMethod.GET)
    public ModelAndView banner() {
        return  new ModelAndView("/homepage/banner");
    }
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(String a) {
        return  new ModelAndView("/homepage/create");
    }
}