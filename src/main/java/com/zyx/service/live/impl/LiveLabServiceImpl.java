package com.zyx.service.live.impl;

import com.zyx.mapper.LiveLabMapper;
import com.zyx.model.LiveLab;
import com.zyx.service.live.LiveLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */

@Service("liveLabService")
public class LiveLabServiceImpl implements LiveLabService {
    @Autowired
    LiveLabMapper liveLabMapper;

    @Override
    public void addLiveLab(LiveLab liveLab) {
        liveLabMapper.insert(liveLab);
    }

    @Override
    public LiveLab getByLab(String lab) {
        LiveLab record = new LiveLab();
        record.setLab(lab);
        List<LiveLab> records = liveLabMapper.select(record);
        return records != null && records.size() == 1 ? records.get(0) : null;
    }

    @Override
    public List<LiveLab> getAllLabs() {
        return liveLabMapper.selectAll();
    }

    @Override
    public void deleteLiveLab(Integer id) {
        liveLabMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteLiveLab(String lab) {
        LiveLab record = new LiveLab();
        record.setLab(lab);
        liveLabMapper.delete(record);
    }

    @Override
    public void updateLiveLab(LiveLab liveLab) {
        liveLabMapper.updateByPrimaryKey(liveLab);
    }
}
