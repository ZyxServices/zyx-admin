package com.zyx.mapper;

import com.zyx.model.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("sysUserMapper")
public interface SysUserMapper extends Mapper<SysUser> {
}