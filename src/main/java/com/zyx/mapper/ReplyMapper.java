package com.zyx.mapper;

import com.zyx.model.Reply;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ReplyMapper extends Mapper<Reply> {

    List<Reply> findByParams(@Param(value = "replyType") Integer replyType,
                             @Param(value = "replyId") Integer replyId,
                             @Param(value = "replyState") Integer replyState);
}