package com.zyx.service.live;

import com.zyx.model.LiveInfo;
import com.zyx.parm.live.LiveInfoParm;
import com.zyx.service.BaseService;

import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */
public interface LiveInfoService extends BaseService<LiveInfo>{
    public void addLiveInfo(LiveInfo liveInfo);
    public List<LiveInfo> getLiveInfos(LiveInfoParm parm);
}
