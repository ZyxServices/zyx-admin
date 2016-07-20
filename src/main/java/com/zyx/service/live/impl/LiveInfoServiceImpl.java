package com.zyx.service.live.impl;

import com.zyx.mapper.LiveInfoMapper;
import com.zyx.model.LiveInfo;
import com.zyx.parm.live.LiveInfoParm;
import com.zyx.service.live.LiveInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */
@Service("liveInfoService")
public class LiveInfoServiceImpl implements LiveInfoService {
    @Autowired
    LiveInfoMapper liveInfoMapper;
    @Override
    public List<LiveInfo> getLiveInfos(LiveInfoParm param) {
        if(param.getPageNumber()!=null&&param.getPageNumber()>=0&&param.getPageSize()!=null&&param.getPageSize()>0){
            RowBounds rowBounds = new RowBounds((param.getPageNumber() - 1) * param.getPageSize(), param.getPageSize());
            Example example = new Example(LiveInfo.class);
            return liveInfoMapper.selectByExampleAndRowBounds(example,rowBounds);
        }
       return null;
    }
}
