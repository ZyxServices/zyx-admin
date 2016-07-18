package com.zyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZYX on 2016/7/14.
 */
@Controller
@RequestMapping("/banner")
public class BannerPageMenuController {
    @RequestMapping(value = "/homebanner", method = RequestMethod.GET)
    public ModelAndView banner() {
        return  new ModelAndView("/banner/homebanner");
    }
}