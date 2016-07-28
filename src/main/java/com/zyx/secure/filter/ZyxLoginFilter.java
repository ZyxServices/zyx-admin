package com.zyx.secure.filter;

import com.zyx.jopo.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by wms on 2016/7/27.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title ZyxLoginFilter.java
 */
public class ZyxLoginFilter extends AuthorizationFilter {
    private static Logger logger = LoggerFactory
            .getLogger(ZyxLoginFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        if (userPrincipal == null) {// 未登录
            logger.debug("非法URL直接调用");
            return false;
        }
        return true;
    }
}
