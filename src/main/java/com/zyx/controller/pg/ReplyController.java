package com.zyx.controller.pg;

import com.zyx.service.pg.ReplyService;
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
 * Create by XiaoWei on 2016/7/26
 */
@Controller
@RequestMapping(value = "reply")
public class ReplyController {

    @Resource
    private ReplyService replyService;

    @RequestMapping(value = "replyList",method = RequestMethod.GET)
    @ApiOperation(value = "回复列表", notes = "回复列表")
    public ModelAndView findByParams(@RequestParam(value = "reply_type") Integer replyType,
                                     @RequestParam(value = "reply_id") Integer replyId,
                                     @RequestParam(value = "reply_state") Integer replyState) {
        Map<String, Object> replys = replyService.findReplyByParams(replyType, replyId, 0);
        AbstractView abstractView = new MappingJackson2JsonView();
        abstractView.setAttributesMap(replys);
        return new ModelAndView(abstractView);
    }
}
