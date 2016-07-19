package com.zyx.controller;

import com.zyx.constants.Constants;
import com.zyx.jopo.UserPrincipal;
import com.zyx.model.SysUser;
import com.zyx.secure.realm.StatelessToken;
import com.zyx.service.SysUserService;
import javax.servlet.ServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/in")
    public ModelAndView in(@RequestParam String username, @RequestParam String password, ServletRequest request) {
        System.out.println("===========LOGIN=================================");
        StatelessToken token = new StatelessToken();
        // 如果是带验证的，则进行验证，否则没有验证，只能进行一般的请求
        if (username != null && password != null) {
            token.setPrincipal(new UserPrincipal(username, UserPrincipal.PrincipType.USER));
            token.setPassword(password);
            try {
                SecurityUtils.getSubject().login(token);
                // 如果认证成功，则增加request的属性，用于@CurrentUser注解使用
                SysUser user = token.getUser();
                request.setAttribute(Constants.CURRENT_USER, user);
                SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER, user);

            } catch (AuthenticationException e) {
                System.out.println("认证失败! " + e.getClass().getSimpleName());
                return new ModelAndView("redirect:/");
            } catch (Exception e) {
                System.out.println("其他认证失败! " + e.getClass().getSimpleName());
                e.printStackTrace();
                return new ModelAndView("redirect:/");
            }

        }
        return new ModelAndView("redirect:/indexPage");

    }


    @RequestMapping("/out")
    public ModelAndView out() {
        SecurityUtils.getSubject().logout();
        return new ModelAndView("/login");
    }

    @RequestMapping("/unauth")
    public ModelAndView unauth() {
        System.out.println("===========out=================================");
        return new ModelAndView("/sys/403");
    }

}
