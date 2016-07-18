package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 文楷 on 2016/7/15.
 */
@Controller
@RequestMapping("/appUser")
public class AppUserMenuController {
    @RequestMapping(value = "/userIndex", method = RequestMethod.GET)
    public ModelAndView circleList() {
        return new ModelAndView("/appUser/userIndex");
    }

}
