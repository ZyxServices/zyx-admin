package com.zyx.secure.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zyx.jopo.UserPrincipal;
import org.springframework.web.util.UrlPathHelper;

public class MyHttpMethodPermissionFilter extends AuthorizationFilter {
    private static Logger logger = LoggerFactory
            .getLogger(MyHttpMethodPermissionFilter.class);


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        // 获取请求链接
        String uri = getURI((HttpServletRequest) request).replaceAll("/", ":").substring(1);

        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        /************************简单测试使用start***********************/
        if (userPrincipal.getUsername().equals("user")) {
            return false;
        }

        if (subject.isPermitted(uri)) {
            return true;
        } else {
            return false;
        }
        /***********************简单测试使用end************************/

    }

    /**
     * 获取请求URL
     */
    private static String getURI(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        return helper.getOriginatingRequestUri(request);
    }

}
