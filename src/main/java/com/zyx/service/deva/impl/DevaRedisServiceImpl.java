package com.zyx.service.deva.impl;

import com.zyx.constants.Constants;
import com.zyx.mapper.*;
import com.zyx.model.AppUser;
import com.zyx.model.LiveLab;
import com.zyx.service.deva.DevaRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MrDeng on 2016/8/24.
 */
@Service("devaRedisService")
public class DevaRedisServiceImpl implements DevaRedisService {

    @Override
    public void refreshDevaRedis(Integer model, Integer area) {

    }


}
