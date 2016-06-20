package com.zyx.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zyx.jopo.ErrorResponseEntity;
import com.zyx.model.SysUser;
import com.zyx.service.SysUserService;

@Controller
@RequestMapping("/syuser")
public class SysUserController {
	
	@Autowired
	SysUserService sysUserService;
	
	 @RequestMapping(value = "/goAdd", method = RequestMethod.GET)
		public ModelAndView goAddUser() {
		    ModelAndView view = new ModelAndView("/sys/adduser");
			return view;

		}

	 @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {APPLICATION_JSON_VALUE})
	public ResponseEntity addUser(@RequestBody SysUser user) {
		SysUser countUser = new SysUser();
		countUser.setUsername(user.getUsername());
		user.setUserId("10010");
		int result = sysUserService.addUser(user);
		if(result==1){
			return new ResponseEntity(user, HttpStatus.OK);
		}
		return ErrorResponseEntity.buildToResponseEntity(1000, "添加用户失败");

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView users(ServletRequest request,
			ServletResponse response) {
		 ModelAndView view = new ModelAndView("/sys/listuser");
		List<SysUser> users = sysUserService.getUsers(null);
		view.addObject("users", users);
		return view;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable int id) {
		sysUserService.deleteById(id);
	}

}
