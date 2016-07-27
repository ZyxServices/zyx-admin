package com.zyx.controller.pg;

import com.zyx.service.pg.CircleService;
import com.zyx.utils.FileUploadUtils;
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
import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.controller.pg
 * Create by XiaoWei on 2016/7/25
 */
@Controller
@RequestMapping(value = "circle")
public class CircleController {

    @Resource
    private CircleService circleService;

    /**
     * 圈子列表
     *
     * @param start
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "circleList", method = RequestMethod.GET)
    @ApiOperation(value = "圈子列表", notes = "圈子列表")
    public ModelAndView findCircleList(@RequestParam(value = "start") Integer start,
                                       @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> map = circleService.findByPager(start, pageSize);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 创建圈子
     *
     * @return
     */
    @RequestMapping(value = "createCircle", method = RequestMethod.POST)
    @ApiOperation(value = "添加圈子", notes = "添加圈子")
    public ModelAndView addCircle(@RequestParam("token") String token,
                                  @RequestParam("title") String title,
                                  @RequestParam("createId") Integer createId,
                                  @RequestParam("state") Integer state,
                                  @RequestParam("type") Integer type,
                                  @RequestParam("details") String details,
                                  @RequestPart("headImgUrl") MultipartFile headImgUrl) {
        AbstractView jsonView = new MappingJackson2JsonView();

        String imgDbUrl = FileUploadUtils.uploadFile(headImgUrl);
//        Map<String, Object> returnResult = ImagesVerifyUtils.verify(imgDbUrl);
//        if (returnResult != null) {
//            jsonView.setAttributesMap(returnResult);
//            return new ModelAndView(jsonView);
//        }
        Map<String, Object> map = circleService.insertCircle(title, createId, state, type, details, "");
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "getCircle", method = RequestMethod.GET)
    @ApiOperation(value = "获取单条圈子数据", notes = "获取单条圈子数据")
    public ModelAndView getCircle(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = circleService.findById(id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "deleteOne", method = RequestMethod.GET)
    @ApiOperation(value = "删除圈子", notes = "逻辑删除圈子")
    public ModelAndView deleteOne(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = circleService.deleteOne(id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "setTop", method = RequestMethod.GET)
    @ApiOperation(value = "精选圈子推荐", notes = "精选圈子推荐")
    public ModelAndView tuiJian(Integer circleId, Integer topSize) {
        Map<String, Object> map = circleService.tuiJian(circleId, topSize);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
