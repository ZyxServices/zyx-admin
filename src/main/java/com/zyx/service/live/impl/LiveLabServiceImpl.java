package com.zyx.service.live.impl;

import com.zyx.constants.LiveConstants;
import com.zyx.mapper.LiveLabMapper;
import com.zyx.model.LiveLab;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.live.LiveLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrDeng on 2016/7/18.
 */

@Service("liveLabService")
public class LiveLabServiceImpl extends BaseServiceImpl<LiveLab> implements LiveLabService {
    @Autowired
    LiveLabMapper liveLabMapper;
    @Autowired
    private RedisTemplate<String, LiveLab> redisTemplate;

    public LiveLabServiceImpl() {
        super(LiveLab.class);
    }
    @Override
    public int addLiveLab(LiveLab liveLab) {
        liveLab.setDel(0);
        liveLab.setState(0);
        LiveLab rlab = (LiveLab) redisTemplate.opsForHash().get(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + liveLab.getLab(), LiveConstants.HASH_LIVE_LAB_LIVE_LAB);
        if (null == rlab) {
            liveLabMapper.insert(liveLab);
            redisTemplate.opsForHash().put(LiveConstants.MARK_LIVE_ID_LIVE_LAB + liveLab.getId(), LiveConstants.HASH_LIVE_ID_LIVE_LAB, liveLab);
            redisTemplate.opsForHash().put(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + liveLab.getLab(), LiveConstants.HASH_LIVE_LAB_LIVE_LAB, liveLab);
            return 1;
        }
        return 0;
    }

    @Override
    public LiveLab getByLab(String lab) {
        LiveLab rlab = (LiveLab) redisTemplate.opsForHash().get(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + lab, LiveConstants.HASH_LIVE_LAB_LIVE_LAB);
        if (null != rlab) {
            return rlab;
        } else {
            LiveLab record = new LiveLab();
            record.setLab(lab);
            List<LiveLab> records = liveLabMapper.select(record);
            rlab = records != null && records.size() == 1 ? records.get(0) : null;
            if(rlab!=null){
                redisTemplate.opsForHash().put(LiveConstants.MARK_LIVE_ID_LIVE_LAB + rlab.getId(), LiveConstants.HASH_LIVE_ID_LIVE_LAB, rlab);
                redisTemplate.opsForHash().put(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + rlab.getLab(), LiveConstants.HASH_LIVE_LAB_LIVE_LAB, rlab);
            }
            return rlab;
        }
    }

    @Override
    public List<LiveLab> getAllLabs() {
        return liveLabMapper.selectAll();
    }

    @Override
    public void deleteLiveLab(Integer id) {
        LiveLab rlab = (LiveLab) redisTemplate.opsForHash().get(LiveConstants.MARK_LIVE_ID_LIVE_LAB + id, LiveConstants.HASH_LIVE_ID_LIVE_LAB);
        if (null != rlab) {
            redisTemplate.opsForHash().delete(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + rlab.getLab(), LiveConstants.HASH_LIVE_LAB_LIVE_LAB);
            redisTemplate.opsForHash().delete(LiveConstants.MARK_LIVE_ID_LIVE_LAB + id, LiveConstants.HASH_LIVE_ID_LIVE_LAB);
        }
        liveLabMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteLiveLab(String lab) {
        LiveLab rlab = (LiveLab) redisTemplate.opsForHash().get(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + lab, LiveConstants.HASH_LIVE_LAB_LIVE_LAB);
        if (null != rlab) {
            redisTemplate.opsForHash().delete(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + lab, LiveConstants.HASH_LIVE_LAB_LIVE_LAB);
            redisTemplate.opsForHash().delete(LiveConstants.MARK_LIVE_ID_LIVE_LAB + rlab.getId(), LiveConstants.HASH_LIVE_ID_LIVE_LAB);
        }
        LiveLab record = new LiveLab();
        record.setLab(lab);
        liveLabMapper.delete(record);
    }

    @Override
    /**
     * By ID
     */
    public void updateLiveLab(LiveLab liveLab) {
        if(liveLab==null||liveLab.getId()==null){
            return;
        }
        LiveLab rlab = (LiveLab) redisTemplate.opsForHash().get(LiveConstants.MARK_LIVE_ID_LIVE_LAB + liveLab.getId(), LiveConstants.HASH_LIVE_ID_LIVE_LAB);
        if (null != rlab) {
            redisTemplate.opsForHash().delete(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + rlab.getLab(), LiveConstants.HASH_LIVE_LAB_LIVE_LAB);
        }
        redisTemplate.opsForHash().put(LiveConstants.MARK_LIVE_ID_LIVE_LAB + liveLab.getId(), LiveConstants.HASH_LIVE_ID_LIVE_LAB, liveLab);
        redisTemplate.opsForHash().put(LiveConstants.MARK_LIVE_LAB_LIVE_LAB + liveLab.getLab(), LiveConstants.HASH_LIVE_LAB_LIVE_LAB, liveLab);
        liveLabMapper.updateByPrimaryKeySelective(liveLab);
    }
}
