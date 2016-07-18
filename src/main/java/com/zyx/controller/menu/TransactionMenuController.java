package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZYX on 2016/7/14.
 */
@Controller
@RequestMapping("/transaction")
public class TransactionMenuController {
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView banner() {
        return  new ModelAndView("/transaction/order");
    }
    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public ModelAndView create(String a) {
        return  new ModelAndView("/transaction/recharge");
    }
}
