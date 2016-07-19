package com.zyx.service.live;

import com.zyx.model.LiveLab;

import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */
public interface LiveLabService {


    public LiveLab getByLab(String lab);

    public List<LiveLab> getAllLabs();

    public void addLiveLab(LiveLab liveLab);

    public void deleteLiveLab(Integer id);

    public void deleteLiveLab(String lab);

    public void updateLiveLab(LiveLab liveLab);
}
