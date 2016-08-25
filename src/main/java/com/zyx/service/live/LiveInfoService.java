package com.zyx.service.live;

import com.zyx.model.LiveInfo;
import com.zyx.parm.live.LiveInfoParm;
import com.zyx.service.BaseService;
import com.zyx.vo.live.LiveInfoVo;

import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */
public interface LiveInfoService extends BaseService<LiveInfo>{
    public void addLiveInfo(LiveInfo liveInfo);
    public List<LiveInfo> getLiveInfos(LiveInfoParm parm);

    /**
     * 获取视频直播地址
     * @param liveId
     * @return
     */
    public String getLiveUrl(Integer liveId);


    public int searchCount(String keyword);
    public List<LiveInfoVo> search(String keyword);
}
