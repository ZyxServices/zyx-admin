package com.zyx.controller.pg;

import com.zyx.model.Concern;
import com.zyx.service.pg.ConcernService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "concernList")
    public ModelAndView findConcern(@RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = concernService.findByPager(page, pageSize);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
