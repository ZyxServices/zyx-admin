package com.zyx.service.live.impl;

import com.zyx.mapper.LiveInfoMapper;
import com.zyx.model.LiveInfo;
import com.zyx.parm.live.LiveInfoParm;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.live.LiveInfoService;
import com.zyx.vo.live.LiveInfoVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */
@Service("liveInfoService")
public class LiveInfoServiceImpl extends BaseServiceImpl<LiveInfo> implements LiveInfoService {
    @Autowired
    LiveInfoMapper liveInfoMapper;
    public LiveInfoServiceImpl() {
        super(LiveInfo.class);
    }
    @Override
    public void addLiveInfo(LiveInfo liveInfo) {
        liveInfo.setDel(0);
        liveInfoMapper.insert(liveInfo);
    }

    @Override
    public List<LiveInfo> getLiveInfos(LiveInfoParm param) {
        Example example = new Example(LiveInfo.class);
        Criteria criteria = example.createCriteria();
        if (param.getPageNumber() != null && param.getPageNumber() >= 0 && param.getPageSize() != null && param.getPageSize() > 0) {
            RowBounds rowBounds = new RowBounds((param.getPageNumber() - 1) * param.getPageSize(), param.getPageSize());
            return liveInfoMapper.selectByExampleAndRowBounds(example, rowBounds);
        } else {
            return liveInfoMapper.selectByExample(example);
        }
    }
    @Override
    public String getLiveUrl(Integer liveId) {
        LiveInfo record = liveInfoMapper.selectByPrimaryKey(liveId);
        return record.getVedioUrl();
    }

    @Override
    public int searchCount(String keyword) {
        return 0;
    }

    @Override
    public List<LiveInfoVo> search(String keyword) {
        return liveInfoMapper.search("%"+keyword+"%");
    }
}
