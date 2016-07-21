package com.zyx.mapper;

import com.zyx.model.AppUser;
import tk.mybatis.mapper.common.Mapper;

public interface AppUserMapper extends Mapper<AppUser> {
    int delByPrimaryKey(Integer id);

    int unDelByPrimaryKey(Integer id);

    int maskByPrimaryKey(Integer id);

    int unMaskByPrimaryKey(Integer id);

    int authAppUserByPrimaryKey(Integer id, int i);
}