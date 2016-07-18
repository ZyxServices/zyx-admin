package com.zyx.service;

import com.zyx.mapper.SysUserMapper;
import com.zyx.model.SysUser;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenkaihua on 15-9-15.
 */
@Service
public class UserService  {

    @Autowired
    SysUserMapper sysUserMapper;



    @RequiresRoles("admin")
    public void deleteWithAdminRoleById(int id){
    	sysUserMapper.deleteByPrimaryKey(id);
    }


    public SysUser getUserById(int id){
        return sysUserMapper.selectByPrimaryKey(id);
    }



    public boolean isExist(SysUser user){
        System.out.println(user.toString());
        return  sysUserMapper.selectCount(user)>0;
    }


    public void addUser(SysUser user){
    	sysUserMapper.insert(user);
    }


    public List<SysUser> getUsers(SysUser user){
        if (user == null) {
            return sysUserMapper.selectAll();
        }

        return sysUserMapper.select(user);
    }


    public void deleteById(int id){
    	sysUserMapper.deleteByPrimaryKey(id);

    }

    public void update(SysUser user){
    	sysUserMapper.updateByPrimaryKey(user);
    }


}
