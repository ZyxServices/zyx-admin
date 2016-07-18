package com.zyx.service;

import com.zyx.mapper.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wms on 2016/7/15.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserService.java
 */
@Service
public class AppUserService {

    @Autowired
    AppUserMapper appUserMapper;
}
