package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wms on 2016/7/19.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title HomeMenuController.java
 */
@Controller
public class HomeMenuController {
    @RequestMapping("/index")
    public ModelAndView home() {
        return new ModelAndView("/login");
    }

    @RequestMapping("/home")
    public ModelAndView indexPage() {
        return new ModelAndView("/index");
    }
}
