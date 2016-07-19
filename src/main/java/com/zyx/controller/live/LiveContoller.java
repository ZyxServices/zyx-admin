package com.zyx.controller.live;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 文楷 on 2016/7/13.
 */
@Controller
@RequestMapping("/live")
public class LiveContoller {
    @RequestMapping(value = "/living", method = RequestMethod.GET)
    public ModelAndView live() {
        ModelAndView view = new ModelAndView("/live/living");
        return view;

    }
}
