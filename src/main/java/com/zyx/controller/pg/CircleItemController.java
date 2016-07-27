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

    @RequestMapping(value = "circleItemList",method = RequestMethod.GET)
    @ApiOperation(value = "帖子列表", notes = "帖子列表")
    public ModelAndView findByPager(@RequestParam(value = "start") Integer start,
                                    @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = circleItemService.findByPager(start, pageSize);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/v1/circleItem/add", method = RequestMethod.POST)
    @ApiOperation(value = "发布帖子", notes = "发布帖子")
    public ModelAndView addCircleItem(@RequestParam(name = "token") String token,
                                      @RequestParam(name = "circle_id") Integer circle_id,
                                      @RequestParam(name = "create_id") Integer create_id,
                                      @RequestParam(name = "title") String title,
                                      @RequestParam(name = "content") String content) {
        Map<String, Object> map = circleItemService.addCircleItem(circle_id, create_id, title, content);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
