package com.zyx.secure.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zyx.jopo.UserPrincipal;

public class MyHttpMethodPermissionFilter  extends AuthorizationFilter   {
	private static Logger logger = LoggerFactory
			.getLogger(MyHttpMethodPermissionFilter.class);


	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject =  SecurityUtils.getSubject();
		UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
		/************************简单测试使用start***********************/
		if(userPrincipal.getUsername().equals("user")){
			return false;
		}
		
		if(subject.isAuthenticated()){
			return true;
		}else {
			return false;
		}
		/***********************简单测试使用end************************/
		
	}



	
	

}
