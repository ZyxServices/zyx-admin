package com.zyx.controller.appuser;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.Constants;
import com.zyx.file.csource.common.Base64;
import com.zyx.model.AppUser;
import com.zyx.parm.appUser.AppUserCreateParam;
import com.zyx.service.AppUserService;
import com.zyx.utils.CipherUtil;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wms on 2016/7/26.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserCreateController.java
 */
@Controller("appUserCreateController")
@RequestMapping("/v1/appUser")
public class AppUserCreateController {

    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insert(@RequestParam String phone, @RequestParam String password, @RequestParam(required = false) MultipartFile avatar, @RequestParam String nickname, @RequestParam String sex, @RequestParam String official, @RequestParam String address, @RequestParam String authInfo, @RequestParam MultipartFile[] authFile) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        AppUser appUser = appUserService.selectByPhone(phone);

        if (appUser != null) {
            map = MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5001, AppUserConstants.ERROR_APP_USER_5001_MSG);
        } else {
            AppUserCreateParam param = new AppUserCreateParam();
            param.setPhone(phone);
            param.setAddress(address);
            param.setSex(Integer.parseInt(sex));
            param.setNickname(nickname);
            param.setOfficial(Integer.parseInt(official));
            param.setPassword(CipherUtil.generatePassword(password));
            if (!avatar.isEmpty()) {
                String _avatar = FileUploadUtils.uploadFile(avatar);
                param.setAvatar(_avatar);
            }
            param.setAuthInfo(authInfo);
            if (authFile != null && authFile.length != 0) {// 上传认证
                StringBuilder sb = new StringBuilder();
                for (MultipartFile _file : authFile) {
                    String _temp_url = FileUploadUtils.uploadFile(_file);
                    sb.append(_temp_url).append(",");
                }
                param.setAuthFile(sb.substring(0, sb.length() - 1).toString());
            }

            map = appUserService.insertAppUser(param);
        }

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
