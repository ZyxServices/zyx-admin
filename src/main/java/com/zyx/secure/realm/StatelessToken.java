package com.zyx.secure.realm;


import com.zyx.model.SysUser;
import org.apache.shiro.authc.AuthenticationToken;

import com.zyx.jopo.UserPrincipal;

/**
 * 认证令牌的封装
 * 
 * @author Administrator
 *
 */
public class StatelessToken implements AuthenticationToken {
	private UserPrincipal userPrincipal;

	private String password;
	
	private SysUser user;

	public StatelessToken() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param principal
	 * @param password
	 */
	public StatelessToken(UserPrincipal principal, String password) {
		super();
		this.userPrincipal = principal;
		this.password = password;
	}

	// 返回用户验证信息
	public Object getCredentials() {
		return password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param principal
	 *            要设置的 principal
	 */
	public void setPrincipal(UserPrincipal principal) {
		this.userPrincipal = principal;
	}

	/*
	 * (非 Javadoc) <p>Title: toString</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatelessToken [principal=" + userPrincipal + ", password="
				+ password + "]";
	}

	public Object getPrincipal() {
		return userPrincipal;
	}

	/** 
	 * @return 获取 userPrincipal 
	 */
	public UserPrincipal getUserPrincipal() {
		return userPrincipal;
	}

	/** 
	 * @param userPrincipal 要设置的 userPrincipal 
	 */
	public void setUserPrincipal(UserPrincipal userPrincipal) {
		this.userPrincipal = userPrincipal;
	}

	/** 
	 * @return 获取 user 
	 */
	public SysUser getUser() {
		return user;
	}

	/** 
	 * @param user 要设置的 user 
	 */
	public void setUser(SysUser user) {
		this.user = user;
	}
	
	

	
	

}
