package com.zyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zyx.model.SysRole;
import com.zyx.service.SysRoleService;

@Controller
@RequestMapping("/sysrole")
public class SysRoleController {
	@Autowired
	SysRoleService sysRoleService;
	
	 @RequestMapping(value = "/goAdd", method = RequestMethod.GET)
		public ModelAndView goAddRole() {
		    ModelAndView view = new ModelAndView("/sys/addrole");
			return view;
		}
	 
	 @RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView roles() {
			 ModelAndView view = new ModelAndView("/sys/listrole");
			List<SysRole> roles = sysRoleService.getSysRole(null);
			view.addObject("roles", roles);
			return view;
		}

}
