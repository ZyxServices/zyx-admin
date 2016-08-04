package com.zyx.controller.pg;

import com.zyx.service.pg.CircleTypeService;
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
@RequestMapping(value = "/circleType")
public class CircleTypeController {

    @Resource
    private CircleTypeService circleTypeService;

    @RequestMapping(value = "/createCircleType", method = RequestMethod.POST)
    @ApiOperation(value = "添加圈子类型", notes = "添加圈子类型")
    public ModelAndView createCircleType(@RequestParam(value = "typeName") String typeName) {
        Map<String, Object> map = circleTypeService.createCircleType(typeName);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/circleTypeList", method = RequestMethod.GET)
    @ApiOperation(value = "圈子类型列表", notes = "查询state不等于0的数据")
    public ModelAndView circleTypeList() {
        Map<String, Object> map = circleTypeService.circleTypeList();
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/setState", method = RequestMethod.POST)
    @ApiOperation(value = "圈子类别设置状态", notes = "state，-1为删除，0位正常")
    ModelAndView setState(@RequestParam(value = "id") Integer id, @RequestParam(value = "state") Integer state) {
        Map<String, Object> map = circleTypeService.setState(id, state);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
