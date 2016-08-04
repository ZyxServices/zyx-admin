package com.zyx.mapper;

import com.zyx.dto.SystemRoleListDto;
import com.zyx.model.SysRole;
import com.zyx.parm.sys.QuerySystemRoleParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("sysRoleMapper")
public interface SysRoleMapper extends Mapper<SysRole> {
    List<SysRole> querySystemRoleList(QuerySystemRoleParam param);

    int querySystemRoleListCount(QuerySystemRoleParam param);

    List<SystemRoleListDto> queryAllSystemRole();
}