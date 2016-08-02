package com.zyx.controller.pg;

import com.zyx.service.pg.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.controller.pg
 * Create by XiaoWei on 2016/8/2
 */
@Controller
@RequestMapping(value = "/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @RequestMapping(value = "/createTag", method = RequestMethod.POST)
    @ApiOperation(value = "创建标签", notes = "创建标签")
    public ModelAndView createTag(@RequestParam(value = "tagName") String tagName) {
        Map<String, Object> map = tagService.createTag(tagName);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/tagList", method = RequestMethod.GET)
    @ApiOperation(value = "标签列表", notes = "标签列表")
    public ModelAndView tagList() {
        Map<String, Object> map = tagService.findAll();
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/setState", method = RequestMethod.POST)
    @ApiOperation(value = "设置状态", notes = "state，-1为删除，0位正常")
    public ModelAndView setState(@RequestParam(value = "id") Integer id, @RequestParam(value = "state") Integer state) {
        Map<String, Object> map = tagService.setState(id, state);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
