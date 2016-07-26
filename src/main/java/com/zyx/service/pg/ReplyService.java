package com.zyx.service.pg;

import com.zyx.model.Reply;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.service.pg.impl
 * Create by XiaoWei on 2016/7/25
 */
public interface ReplyService extends BaseService<Reply> {
    /**
     * 根据回复主体类型，主体id，状态查询
     * @param replyType
     * @param replyId
     * @param replyState
     * @return
     */
     Map<String,Object> findReplyByParams(Integer replyType,Integer replyId,Integer replyState);
}
