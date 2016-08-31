package com.zyx.service.pg.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.PgConstants;
import com.zyx.mapper.ReplyMapper;
import com.zyx.model.Reply;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.ReplyService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg.impl
 * Create by XiaoWei on 2016/7/25
 */
@Service
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {
    @Resource
    private ReplyMapper replyMapper;

    public ReplyServiceImpl() {
        super(Reply.class);
    }


    @Override
    public Map<String, Object> findReplyByParams(Integer replyType, Integer replyId, Integer replyState) {
        Optional.ofNullable(replyType).orElse(0);
        Optional.ofNullable(replyId).orElse(0);
        Optional.ofNullable(replyState).orElse(0);
        List<Reply> replies = replyMapper.findByParams(replyType, replyId, replyState);
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, replies);
        return map;
    }
}
