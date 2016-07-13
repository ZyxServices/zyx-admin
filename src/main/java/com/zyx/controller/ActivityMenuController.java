package com.zyx.controller;
        
         import com.zyx.model.SysRole;
         import org.springframework.stereotype.Controller;
         import org.springframework.web.bind.annotation.RequestMapping;
         import org.springframework.web.bind.annotation.RequestMethod;
         import org.springframework.web.servlet.ModelAndView;
        
         import java.util.List;
        
         /**
   * Created by ZYX on 2016/7/12.
   */
         @Controller
 @RequestMapping("/activity")
 public class ActivityMenuController {
             @RequestMapping(value = "/list", method = RequestMethod.GET)
             public ModelAndView list() {
                 return  new ModelAndView("/activity/list");
             }

             @RequestMapping(value = "/create", method = RequestMethod.GET)
             public ModelAndView create(String a) {
                 return  new ModelAndView("/activity/create");
             }
     }
