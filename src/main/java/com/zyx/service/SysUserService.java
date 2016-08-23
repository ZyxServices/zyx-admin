package com.zyx.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.SysConstants;
import com.zyx.mapper.SysUserMapper;
import com.zyx.model.SysRole;
import com.zyx.model.SysUser;
import com.zyx.parm.sys.CreateSystemUserParam;
import com.zyx.parm.sys.QuerySystemUserParam;
import com.zyx.utils.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenkaihua on 15-9-15.
 */
@Service
public class SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;


    @RequiresRoles("admin")
    public void deleteWithAdminRoleById(int id) {
        sysUserMapper.deleteByPrimaryKey(id);
    }


    public SysUser getUserById(int id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }


    public boolean isExist(SysUser user) {
        System.out.println(user.toString());
        return sysUserMapper.selectCount(user) > 0;
    }


    public int addUser(SysUser user) {
        int result = sysUserMapper.insert(user);
        return result;
    }


    public List<SysUser> getUsers(SysUser user) {
        if (user == null) {
            return sysUserMapper.selectAll();
        }

        return sysUserMapper.select(user);
    }


    public void deleteById(int id) {
        sysUserMapper.deleteByPrimaryKey(id);

    }

    public void update(SysUser user) {
        sysUserMapper.updateByPrimaryKey(user);
    }

    public SysUser getUserByNamePass(String username, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);
        return sysUserMapper.selectOne(sysUser);
    }


    public Map<String, Object> queryList(QuerySystemUserParam param) {
        Map<String, Object> map = new HashedMap();

        try {
            List<SysUser> _list = sysUserMapper.querySystemUserList(param);
            int count = sysUserMapper.querySystemUserListCount(param);
            map.put("rows", _list);
            map.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, Object> insertSysUser(CreateSystemUserParam param) {
        SysUser _user = new SysUser();
        _user.setRoleId(param.getRoleId());
        _user.setPassword(param.getPassword());
        _user.setBz(param.getRemark());
        _user.setName(param.getName());
        _user.setUsername(param.getUserName());
        _user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));

        try {
            int result = sysUserMapper.insert(_user);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(SysConstants.ERROR_9005, SysConstants.ERROR_9005_MSG);
            }
        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }
    }

    public Map<String, Object> editSysRole(CreateSystemUserParam param) {
        try {
            int result = sysUserMapper.editSysRole(param);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(SysConstants.ERROR_9006, SysConstants.ERROR_9006_MSG);
            }
        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }
    }
}
