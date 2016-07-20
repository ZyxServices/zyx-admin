package com.zyx.service.live;

import com.zyx.model.LiveInfo;
import com.zyx.parm.live.LiveInfoParm;

import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */
public interface LiveInfoService {
    public List<LiveInfo> getLiveInfos(LiveInfoParm parm);
}
