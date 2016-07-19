package com.zyx.mapper;

import com.zyx.model.SysMenu;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("sysMenuMapper")
public interface SysMenuMapper extends Mapper<SysMenu> {
}