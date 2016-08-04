package com.zyx.mapper;

import com.zyx.model.SysUser;
import com.zyx.parm.sys.CreateSystemUserParam;
import com.zyx.parm.sys.QuerySystemUserParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository("sysUserMapper")
public interface SysUserMapper extends Mapper<SysUser> {
    List<SysUser> querySystemUserList(QuerySystemUserParam param);

    int querySystemUserListCount(QuerySystemUserParam param);

    int editSysRole(CreateSystemUserParam param);
}