package com.zyx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyx.mapper.SysRoleMapper;
import com.zyx.model.SysMenu;
import com.zyx.model.SysRole;

@Service
public class SysRoleService {
	
	 @Autowired
	 SysRoleMapper sysRoleMapper;
	 
	  public SysRole getSysMenuById(int id){
	        return sysRoleMapper.selectByPrimaryKey(id);
	    }
	  
	  
	  public List<SysRole> getSysRole(SysRole per){
	        if (per == null) {
	            return sysRoleMapper.selectAll();
	        }

	        return sysRoleMapper.select(per);
	    }


}
