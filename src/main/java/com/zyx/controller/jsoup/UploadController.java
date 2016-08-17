package com.zyx.controller.jsoup;

import com.zyx.constants.Constants;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * Created by SubDong on 16-8-10.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title UploadController
 * @package com.zyx.controller.jsoup
 * @update 16-8-10 下午6:31
 */
@Controller
@RequestMapping("/v1/upload")
public class UploadController {
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public ModelAndView uploadFile(@RequestParam(name = "imgFile", required = true) MultipartFile file) {

        AbstractView jsonView = new MappingJackson2JsonView();

        String temp_url = FileUploadUtils.uploadFile(file);

        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "文件上传成功", temp_url);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
