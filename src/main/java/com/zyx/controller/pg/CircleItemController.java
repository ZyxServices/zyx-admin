package com.zyx.controller.pg;

import com.zyx.service.pg.CircleItemService;
import com.zyx.service.pg.CircleService;
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
 * Create by XiaoWei on 2016/7/25
 */
@Controller
@RequestMapping(value = "circleItem")
public class CircleItemController {

    @Resource
    private CircleItemService circleItemService;

    @RequestMapping(value = "circleItemList", method = RequestMethod.GET)
    @ApiOperation(value = "帖子列表", notes = "帖子列表")
    public ModelAndView findByPager(@RequestParam(value = "start") Integer start,
                                    @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = circleItemService.findByPager(start, pageSize);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "createCircleItem", method = RequestMethod.POST)
    @ApiOperation(value = "发布帖子", notes = "发布帖子")
    public ModelAndView addCircleItem(
            @RequestParam(name = "circle_id") Integer circle_id,
            @RequestParam(name = "create_id") Integer create_id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content) {
        Map<String, Object> map = circleItemService.addCircleItem(circle_id, create_id, title, content);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    @ApiOperation(value = "获取某一条帖子", notes = "获取某一条帖子")
    public ModelAndView findOne(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = circleItemService.findOne(id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "deleteOne", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除某一条帖子", notes = "逻辑删除某一条帖子")
    public ModelAndView deleteOne(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = circleItemService.deleteOne(id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }


    @RequestMapping(value = "setState", method = RequestMethod.DELETE)
    @ApiOperation(value = "屏蔽某一条帖子", notes = "可以作为删除，屏蔽，取消屏蔽的接口，state:0为正常，-1位删除，-2位屏蔽")
    public ModelAndView setVisible(@RequestParam(value = "id") Integer id,@RequestParam(value = "state") Integer state) {
        Map<String, Object> map = circleItemService.setVisible(id,state);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
