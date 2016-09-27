package com.zyx.controller.pg;

import com.zyx.constants.Constants;
import com.zyx.service.deva.DevaService;
import com.zyx.service.pg.CircleService;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.ImagesVerifyUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    DevaService devaService;
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
                                       @RequestParam(value = "pageSize") Integer pageSize,
                                       @RequestParam(value = "searchText",required = false)String searchText) {
        Map<String, Object> map = null;
        if (Objects.equals(searchText, null) || Objects.equals(searchText, "")) {
            map=circleService.findByPager(start, pageSize);
        }else{
            map = circleService.search(start, pageSize, searchText);
        }
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
    public ModelAndView addCircle(
            @RequestParam("title") String title,
            @RequestParam(value = "createId") Integer createId,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "masterId", required = false) Integer masterId,
            @RequestParam(value = "adminIds", required = false) String adminIds,
            @RequestParam("circleType") Integer circleType,
            @RequestParam(value = "headImgUrl", required = false) String headImgUrl,
            @RequestParam("details") String details
    ) {
//
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = circleService.insertCircle(title, createId, state, circleType, details, headImgUrl, masterId, adminIds);
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

    @RequestMapping(value = "deleteOne", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除圈子", notes = "逻辑删除圈子")
    public ModelAndView deleteOne(@RequestParam(value = "id") Integer id) {
        Map<String, Object> map = circleService.deleteOne(id);
        devaService.cascadeDelete(Constants.MODEL_LIVE,id);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "setState", method = RequestMethod.DELETE)
    @ApiOperation(value = "状态设置", notes = "可以作为删除，屏蔽，取消屏蔽的接口，state:0为正常，-1位删除，-2位屏蔽")
    public ModelAndView setVisible(@RequestParam(value = "id") Integer id, @RequestParam(value = "state") Integer state) {
        Map<String, Object> map = circleService.setVisible(id, state);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

//
//    @RequestMapping(value = "setTop", method = RequestMethod.GET)
//    @ApiOperation(value = "精选圈子推荐", notes = "精选圈子推荐")
//    public ModelAndView tuiJian(@RequestParam(value = "circleId") Integer circleId, @RequestParam(value = "topSize") Integer topSize) {
//        Map<String, Object> map = circleService.tuiJian(circleId, topSize);
//        AbstractView jsonView = new MappingJackson2JsonView();
//        jsonView.setAttributesMap(map);
//        return new ModelAndView(jsonView);
//    }

//    @RequestMapping(value = "search", method = RequestMethod.POST)
//    @ApiOperation(value = "圈子列表搜索", notes = "圈子列表搜索")
//    public ModelAndView search(@RequestParam(value = "start") Integer start, @RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "searchText") String searchText) {
//        Map<String, Object> map = circleService.search(start, pageSize, searchText);
//        AbstractView jsonView = new MappingJackson2JsonView();
//        jsonView.setAttributesMap(map);
//        return new ModelAndView(jsonView);
//    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑圈子信息", notes = "编辑圈子")
    public ModelAndView editCircle(@RequestParam(value = "circleId") Integer circleId,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "imgFile", required = false) String imgDbUrl,
                                   @RequestParam(value = "circleType") Integer circleType,
                                   @RequestParam(value = "details", required = false) String details,
                                   @RequestParam(value = "masterId", required = false) Integer masterId,
                                   @RequestParam(value = "adminIds", required = false) String adminIds) {
        AbstractView jsonView = new MappingJackson2JsonView();
//        String imgDbUrl = "";
//        if (imgFile != null) {
//             imgDbUrl = FileUploadUtils.uploadFile(imgFile);
//            Map<String, Object> returnResult = ImagesVerifyUtils.verify(imgDbUrl);
//            if (returnResult != null) {
//                jsonView.setAttributesMap(returnResult);
//                return new ModelAndView(jsonView);
//            }
//        }
        Map<String, Object> resultMap = circleService.editCircle(circleId, title, imgDbUrl, circleType, details, masterId, adminIds);
        jsonView.setAttributesMap(resultMap);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "delete/{ids}", method = RequestMethod.DELETE)
    @ApiOperation(value = "圈子批量删除", notes = "圈子批量删除")
    @ApiParam(required = true, name = "ids", value = "圈子id字符串，以逗号隔开格式：1,2,3（逗号必须是英文类型）")
    public ModelAndView deleteByIds(@PathVariable(value = "ids") String ids) {
        Map<String, Object> map = circleService.delByIds(ids);
        AbstractView jsonView = new MappingJackson2JsonView();
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

}
