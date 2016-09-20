package com.zyx.controller.appuser;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.Constants;
import com.zyx.parm.appUser.AppUserCreateParam;
import com.zyx.service.AppUserService;
import com.zyx.utils.CipherUtil;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * Created by wms on 2016/9/20.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @since 2016/9/20
 */
@Controller("appUserAuthController")
@RequestMapping("/v1/user")
public class AppUserAuthController {
    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/submit_auth", method = RequestMethod.POST)
    @ApiOperation(value = "APP用户接口-提交审核", notes = "APP用户接口-提交审核")
    public ModelAndView submitAuth(@RequestParam Integer userId,
                                   @RequestParam String authName,
                                   @RequestParam String authIDCard,
                                   @RequestParam String authMob,
                                   @RequestParam String authFile,
                                   @RequestParam String authInfo,
                                   @RequestParam String authFileWork) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        try {
            AppUserCreateParam param = new AppUserCreateParam();
            param.setAppUserId(userId);
            param.setAuthName(authName);
            param.setAuthIDCard(authIDCard);
            param.setAuthMob(authMob);
            param.setAuthFile(authFile);
            param.setAuthInfo(authInfo);
            param.setAuthFileWork(authFileWork);
            map = appUserService.submitAppUserAuthInfo(param);
        } catch (Exception e) {
            e.printStackTrace();
            map = Constants.MAP_500;
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
