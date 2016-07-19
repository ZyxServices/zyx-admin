package com.zyx.mapper;

import com.zyx.model.SysRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("sysRoleMapper")
public interface SysRoleMapper extends Mapper<SysRole> {
}