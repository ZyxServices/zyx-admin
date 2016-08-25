package com.zyx.mapper;

import com.zyx.base.BaseMapper;
import com.zyx.model.LiveInfo;
import com.zyx.vo.live.LiveInfoVo;

import java.util.List;

public interface LiveInfoMapper extends BaseMapper<LiveInfo>{
    public List<LiveInfoVo> search(String  keyword);
    public int searchCount(String  keyword);
}