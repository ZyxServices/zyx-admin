package com.zyx.service;

import com.zyx.mapper.AppUserMapper;
import com.zyx.model.AppUser;
import com.zyx.parm.QueryAppUserParam;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Map<String, Object> queryList(QueryAppUserParam param) {
        Map<String, Object> map = new HashedMap();

        try {
            RowBounds row = new RowBounds((param.getPageNumber() - 1) * param.getPageSize(), param.getPageSize());
            List<AppUser> ss = appUserMapper.selectByRowBounds(new AppUser(), row);
            int count = appUserMapper.selectCount(new AppUser());
            map.put("rows", ss);
            map.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
