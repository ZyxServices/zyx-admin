package com.zyx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyx.mapper.SysMenuMapper;
import com.zyx.model.SysMenu;

@Service
public class SysMenuService {
	
	 @Autowired
	 SysMenuMapper sysMenuMapper;
	 
	  public SysMenu getSysMenuById(int id){
	        return sysMenuMapper.selectByPrimaryKey(id);
	    }
	  
	  
	  public List<SysMenu> getSysMenu(SysMenu per){
	        if (per == null) {
	            return sysMenuMapper.selectAll();
	        }

	        return sysMenuMapper.select(per);
	    }


}
