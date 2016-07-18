package com.zyx.mapper;

import com.zyx.model.AppUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by wms on 2016/7/15.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AppUserMapper.java
 */
@Repository("appUserMapper")
public interface AppUserMapper extends Mapper<AppUser> {
}
