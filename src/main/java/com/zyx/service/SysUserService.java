package com.zyx.service;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyx.mapper.SysUserMapper;
import com.zyx.model.SysUser;

/**
 * Created by chenkaihua on 15-9-15.
 */
@Service
public class SysUserService  {

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


    public int addUser(SysUser user){
    	int result = sysUserMapper.insert(user);
    	return result;
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
    
    public SysUser getUserByNamePass(String username,String password){
    	SysUser paer = new SysUser();
    	paer.setUsername(username);
    	paer.setPassword(password);
    	return sysUserMapper.selectOne(paer);
    }


}
