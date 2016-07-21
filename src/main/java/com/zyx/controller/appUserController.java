package com.zyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 文楷 on 2016/7/15.
 */
@Controller
@RequestMapping("/appUser")
public class appUserController {
    @RequestMapping(value = "/userIndex", method = RequestMethod.GET)
    public ModelAndView circleList() {
        return new ModelAndView("/appUser/userIndex");
    }

    @RequestMapping(value = "/circlecreat", method = RequestMethod.GET)
    public ModelAndView circleCreate() {
        return new ModelAndView("/circle/circlecreat");
    }
}
