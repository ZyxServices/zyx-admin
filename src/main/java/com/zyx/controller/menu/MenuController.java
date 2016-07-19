package com.zyx.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MrDeng on 2016/7/19.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping(value = "/activity/list", method = RequestMethod.GET)
    public ModelAndView redirectActivity() {
        return new ModelAndView("/activity/list");
    }

    @RequestMapping(value = "/user/userIndex", method = RequestMethod.GET)
    public ModelAndView redirectAppUser() {
        return new ModelAndView("/appUser/userIndex");
    }

    @RequestMapping(value = "/circle/circlelist", method = RequestMethod.GET)
    public ModelAndView redirectCircleList() {
        return new ModelAndView("/circle/circlelist");
    }

    @RequestMapping(value = "/circle/circlepost", method = RequestMethod.GET)
    public ModelAndView redirectCirclePost() {
        return new ModelAndView("/circle/circlepost");
    }

    @RequestMapping(value = "/dynamic/dynamicIndex", method = RequestMethod.GET)
    public ModelAndView redirectDynamicIndex() {
        return new ModelAndView("/dynamic/dynamicIndex");
    }

    @RequestMapping(value = "/homepage/banner", method = RequestMethod.GET)
    public ModelAndView redirectBanner() {
        return new ModelAndView("/homepage/banner");
    }

    @RequestMapping(value = "/homepage/create", method = RequestMethod.GET)
    public ModelAndView redirectCreate() {
        return new ModelAndView("/homepage/create");
    }

    @RequestMapping(value = "/live/living", method = RequestMethod.GET)
    public ModelAndView redirectLive() {
        return new ModelAndView("/live/living");
    }

    @RequestMapping(value = "/shop/goods", method = RequestMethod.GET)
    public ModelAndView redirectGoods() {
        return new ModelAndView("/shop/goods");
    }

    @RequestMapping(value = "/shop/order", method = RequestMethod.GET)
    public ModelAndView redirectOrder() {
        return new ModelAndView("/shop/order");
    }

    @RequestMapping(value = "/transaction/order", method = RequestMethod.GET)
    public ModelAndView redirectTransactionOrder() {
        return new ModelAndView("/transaction/order");
    }

    @RequestMapping(value = "/transaction/recharge", method = RequestMethod.GET)
    public ModelAndView redirectTransactionRecharge() {
        return new ModelAndView("/transaction/recharge");
    }
}
