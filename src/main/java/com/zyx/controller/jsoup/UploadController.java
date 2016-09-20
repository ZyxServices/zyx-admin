package com.zyx.controller.jsoup;

import com.zyx.constants.Constants;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.ImagesVerifyUtils;
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by SubDong on 16-8-10.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 */
@Controller
@RequestMapping("/v1/upload")
public class UploadController {
    // 创建一个线程池
    ExecutorService pool = Executors.newFixedThreadPool(100);

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public ModelAndView uploadFile(@RequestParam(name = "imgFile") MultipartFile file) {

        AbstractView jsonView = new MappingJackson2JsonView();
        try {
            if (file == null) {
                jsonView.setAttributesMap(Constants.MAP_PARAM_MISS);
            } else {
                System.out.println("file  :  " + file);
                System.out.println("file.getName()  :  " + file.getName());

                Callable c = new MyCallable(file);
                // 执行任务并获取Future对象
                Future f = pool.submit(c);

                String avatarId = f.get().toString();
                Map<String, Object> map = ImagesVerifyUtils.verify(avatarId);
                if (map != null) {
                    jsonView.setAttributesMap(map);
                } else {
                    map = new HashMap<>();
                    map.put(Constants.STATE, Constants.SUCCESS);
                    map.put(Constants.SUCCESS_MSG, "图片上传成功");
                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("url", avatarId);
                    map.put(Constants.DATA, map2);
                    jsonView.setAttributesMap(map);
                }
            }
        } catch (Exception e) {
            jsonView.setAttributesMap(Constants.MAP_500);
        }
        return new ModelAndView(jsonView);

    }
}

class MyCallable implements Callable<Object> {
    private MultipartFile file;

    MyCallable(MultipartFile file) {
        this.file = file;
    }

    public Object call() throws Exception {
        return FileUploadUtils.uploadFile(file);
    }
}