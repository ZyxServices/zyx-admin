package com.zyx.controller;

import com.zyx.model.SysRole;
import com.zyx.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
