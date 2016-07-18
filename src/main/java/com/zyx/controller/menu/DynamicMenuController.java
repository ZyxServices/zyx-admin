package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 文楷 on 2016/7/15.
 */
@Controller
@RequestMapping("/dynamic")
public class DynamicMenuController {
        @RequestMapping(value = "/dynamicIndex", method = RequestMethod.GET)
        public ModelAndView dynamicIndex() {
            return new ModelAndView("/dynamic/dynamicIndex");
        }

        @RequestMapping(value = "/circlecreat", method = RequestMethod.GET)
        public ModelAndView circleCreate() {
            return new ModelAndView("/circle/circlecreat");
        }
}
