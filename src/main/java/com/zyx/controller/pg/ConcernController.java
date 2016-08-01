package com.zyx.controller.pg;

import com.zyx.constants.Constants;
import com.zyx.model.Concern;
import com.zyx.service.pg.ConcernService;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.controller.pg
 * Create by XiaoWei on 2016/7/25
 */
@Controller
@RequestMapping(value = "/concern")
public class ConcernController {

    @Resource
    private ConcernService concernService;

    @RequestMapping(value = "createConcern", method = RequestMethod.POST)
    @ApiOperation(value = "创建动态", notes = "创建动态")
    public ModelAndView createConcern(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "visible") Integer visible,
            @RequestParam(value = "type") Integer type,
            @RequestParam(value = "createId") Integer createId,
            @RequestPart(value = "imgFile") MultipartFile imgFile) {
        AbstractView jsonView = new MappingJackson2JsonView();
        if (Objects.equals(imgFile, null)) {
            jsonView.setAttributesMap(MapUtils.buildErrorMap(Constants.AUTH_ERROR_903, "文件未找到"));
            return new ModelAndView(jsonView);
        }
        String dbFilePath = FileUploadUtils.uploadFile(imgFile);
        Map<String, Object> map = concernService.createConcern(content, createId, type, visible, dbFilePath);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "concernList", method = RequestMethod.GET)
    @ApiOperation(value = "动态列表", notes = "动态列表")
    public ModelAndView findConcern(@RequestParam(value = "start") Integer page, @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = concernService.findByPager(page, pageSize);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "getOne", method = RequestMethod.GET)
    @ApiOperation(value = "获取单条动态数据", notes = "获取单条动态数据")
    public ModelAndView getOne(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = concernService.findById(id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "deleteOne", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除动态", notes = "逻辑删除")
    public ModelAndView deleteOne(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = concernService.deleteOne(id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "setVisible", method = RequestMethod.DELETE)
    @ApiOperation(value = "屏蔽动态", notes = "逻辑屏蔽")
    public ModelAndView setVisible(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = concernService.setVisible(id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑动态", notes = "编辑动态")
    public ModelAndView edit(@RequestParam(value = "id") Integer id,
                             @RequestParam(value = "topic_content") String topicContent,
                             @RequestParam(value = "img_url") String imgUrl) {
        Map<String, Object> map = concernService.edit(topicContent, imgUrl, id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
