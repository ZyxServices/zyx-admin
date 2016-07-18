package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/circle")
public class CircleMenuController {
    @RequestMapping(value = "/circlelist", method = RequestMethod.GET)
    public ModelAndView circleList() {
        return new ModelAndView("/circle/circlelist");
    }

    @RequestMapping(value = "/circlepost", method = RequestMethod.GET)
    public ModelAndView circlePost() {
        return new ModelAndView("/circle/circlepost");
    }
}

