package com.zyx.controller.appuser;

import com.zyx.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wms on 2016/7/15.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserController.java
 */
@Controller("appUserController")
@RequestMapping("/v1/user/app")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

}
