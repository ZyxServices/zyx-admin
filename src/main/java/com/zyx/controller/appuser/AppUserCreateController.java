package com.zyx.controller.appuser;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.Constants;
import com.zyx.model.AppUser;
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
 * Created by wms on 2016/7/26.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Controller("appUserCreateController")
@RequestMapping("/v1/appUser")
public class AppUserCreateController {

    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "APP用户接口-新增用户", notes = "APP用户接口-新增用户")
    public ModelAndView insert(@RequestParam String phone,
                               @RequestParam String password,
                               @RequestPart(required = false) MultipartFile avatar,
                               @RequestParam String nickname,
                               @RequestParam String sex,
                               @RequestParam String official,
                               @RequestParam String address) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        try {
            if (appUserService.selectByPhone(phone) != null) {
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
                map = appUserService.insertAppUser(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = Constants.MAP_500;
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "APP用户接口-编辑用户", notes = "APP用户接口-编辑用户")
    public ModelAndView update(@RequestParam Integer id,
                               @RequestParam String phone,
                               @RequestParam(required = false) String password,
                               @RequestPart(required = false) MultipartFile avatar,
                               @RequestParam(required = false) String nickname,
                               @RequestParam(required = false) String sex,
                               @RequestParam(required = false) String official,
                               @RequestParam(required = false) String address,
                               @RequestParam(required = false) String authName,
                               @RequestParam(required = false) String authIDCard,
                               @RequestParam(required = false) String authMob,
                               @RequestParam(required = false) String authFile,
                               @RequestParam(required = false) String authInfo,
                               @RequestParam(required = false) String authFileWork) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        AppUser appUser = appUserService.selectByPhone(phone);

        if (appUser != null && !appUser.getId().equals(id)) {
            map = MapUtils.buildErrorMap(AppUserConstants.ERROR_APP_USER_5001, AppUserConstants.ERROR_APP_USER_5001_MSG);
        } else {
            AppUserCreateParam param = new AppUserCreateParam();
            param.setAppUserId(id);
            param.setPhone(phone);
            param.setAddress(address);
            if (sex != null) {
                param.setSex(Integer.parseInt(sex));
            }
            param.setNickname(nickname);
            if (official != null) {
                param.setOfficial(Integer.parseInt(official));
            }
            if (password != null) {
                param.setPassword(CipherUtil.generatePassword(password));
            }
            if (avatar != null && !avatar.isEmpty()) {
                String _avatar = FileUploadUtils.uploadFile(avatar);
                param.setAvatar(_avatar);
            }
            param.setModifyTime(System.currentTimeMillis());
            param.setAuthInfo(authInfo);
            param.setAuthFile(authFile);
//            map = appUserService.updateAppUser(param);
        }

//        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    @ApiOperation(value = "APP用户接口-重置密码", notes = "APP用户接口-重置密码")
    public ModelAndView resetPwd(@RequestParam String phone) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map;
        try {
            AppUserCreateParam param = new AppUserCreateParam();
            param.setPhone(phone);
            param.setPassword(CipherUtil.generatePassword("7788119"));
            map = appUserService.resetAppUser(param);
        } catch (Exception e) {
            e.printStackTrace();
            map = Constants.MAP_500;
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
