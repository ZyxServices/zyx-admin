package com.zyx.service;

import com.zyx.constants.AppUserConstants;
import com.zyx.constants.SysConstants;
import com.zyx.dto.SystemRoleListDto;
import com.zyx.dto.SystemUserListDto;
import com.zyx.mapper.SysRoleMapper;
import com.zyx.model.SysRole;
import com.zyx.model.SysUser;
import com.zyx.parm.sys.CreateSystemRoleParam;
import com.zyx.parm.sys.QuerySystemRoleParam;
import com.zyx.utils.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    public Map<String, Object> queryList(QuerySystemRoleParam param) {
        Map<String, Object> map = new HashedMap();

        try {
            List<SysRole> _list = sysRoleMapper.querySystemRoleList(param);
            int count = sysRoleMapper.querySystemRoleListCount(param);
            map.put("rows", _list);
            map.put("total", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public SysRole selectByRoleName(String roleName) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        return sysRoleMapper.selectOne(sysRole);
    }

    public SysRole selectByRoleId(String roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(roleId);
        return sysRoleMapper.selectOne(sysRole);
    }

    public Map<String, Object> insertSysRole(CreateSystemRoleParam param) {
        SysRole _role = new SysRole();
        _role.setRoleId(param.getRoleId());
        _role.setRoleName(param.getRoleName());
        _role.setRoleDesc(param.getRoleDesc());
        _role.setMenuPerm(param.getMenuPerm());

        try {
            int result = sysRoleMapper.insert(_role);
            if (result >= 1) {
                return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
            } else {
                return MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9002, SysConstants.ERROR_APP_USER_9002_MSG);
            }
        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }
    }

    public Map<String, Object> editSysRole(CreateSystemRoleParam param) {
        try {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(param.getId());
            if (sysRole == null) {
                return MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9003, SysConstants.ERROR_APP_USER_9003_MSG);
            } else {
                sysRole.setRoleDesc(param.getRoleDesc());
                sysRole.setMenuPerm(param.getMenuPerm());
                int result = sysRoleMapper.updateByPrimaryKey(sysRole);
                if (result >= 1) {
                    return MapUtils.buildSuccessMap(SysConstants.SUCCESS, SysConstants.MSG_SUCCESS, null);
                } else {
                    return MapUtils.buildErrorMap(SysConstants.ERROR_APP_USER_9003, SysConstants.ERROR_APP_USER_9003_MSG);
                }
            }
        } catch (Exception e) {
            return AppUserConstants.MAP_500;
        }
    }

    public List<SystemRoleListDto> queryAllList() {
        List<SystemRoleListDto> _list;

        try {
            _list = sysRoleMapper.queryAllSystemRole();
        } catch (Exception e) {
            _list = new ArrayList<>();
        }

        return _list;
    }
}
