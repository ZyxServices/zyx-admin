package com.zyx.controller;

import com.zyx.constants.Constants;
import com.zyx.jopo.RSAKeyMap;
import com.zyx.jopo.UserPrincipal;
import com.zyx.model.SysUser;
import com.zyx.secure.realm.StatelessToken;
import com.zyx.service.SysUserService;

import javax.servlet.ServletRequest;

import com.zyx.utils.CipherUtil;
import com.zyx.utils.RSAUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public ModelAndView in_get() {
        AbstractView jsonView = new MappingJackson2JsonView();

        try {
            RSAUtils rsa = new RSAUtils();
            //生成公钥和密钥
            Map<String, Object> keyMap = rsa.createKey();
            RSAPublicKey publicKey = (RSAPublicKey) keyMap.get("publicKey");
            RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get("privateKey");
            //js通过模和公钥指数获取公钥对字符串进行加密，注意必须转为16进制
            //模
            //java中的模和私钥指数不需要转16进制，但是js中的需要转换为16进制
            String Modulus16 = publicKey.getModulus().toString(16);
            String Modulus = publicKey.getModulus().toString();
            //公钥指数
            String Exponent = publicKey.getPublicExponent().toString(16);
            //私钥指数
            String private_exponent = privateKey.getPrivateExponent().toString();

            RSAKeyMap.RSA_MAP.put(Modulus, private_exponent);
            Map<String, Object> _map = new HashMap();
            _map.put("m", Modulus);
            _map.put("m16", Modulus16);
            _map.put("e", Exponent);
            jsonView.setAttributesMap(_map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(jsonView);

    }


    @RequestMapping(value = "/in", method = RequestMethod.POST)
    public ModelAndView in(@RequestParam String username, @RequestParam String password, @RequestParam String prm, ServletRequest request) {
        System.out.println("===========LOGIN=================================");

        //获取密码
        System.out.println(prm);
        System.out.println(password);

        RSAUtils rsa = new RSAUtils();
        String private_exponent = RSAKeyMap.RSA_MAP.get(prm).toString();
        RSAKeyMap.RSA_MAP.remove(prm);
        //根据模和私钥指数获取私钥
        RSAPrivateKey rsaPrivateKey = RSAUtils.getPrivateKey(prm, private_exponent);

        StatelessToken token = new StatelessToken();
        Subject currentUser = SecurityUtils.getSubject();
        // 如果是带验证的，则进行验证，否则没有验证，只能进行一般的请求
        if (username != null && password != null) {
            token.setPrincipal(new UserPrincipal(username, UserPrincipal.PrincipType.USER));

            try {
                //解密
                String password_new = rsa.decrypttoStr(rsaPrivateKey, password);

                System.out.println(password_new);

                password_new = new String(Base64.getDecoder().decode(password_new));

                System.out.println(password_new);

                token.setPassword(CipherUtil.generatePassword(password_new));

                currentUser.login(token);

            } catch (AuthenticationException e) {
                System.out.println("认证失败! " + e.getClass().getSimpleName());
                return new ModelAndView("redirect:/");
            } catch (Exception e) {
                System.out.println("其他认证失败! " + e.getClass().getSimpleName());
                e.printStackTrace();
                return new ModelAndView("redirect:/");
            }

        }

        if (currentUser.isAuthenticated()) {
            // 如果认证成功，则增加request的属性，用于@CurrentUser注解使用
            SysUser user = token.getUser();
            request.setAttribute(Constants.CURRENT_USER, user);
            currentUser.getSession().setAttribute(Constants.CURRENT_USER, user);
            return new ModelAndView("redirect:/home");
        } else {
            return new ModelAndView("redirect:/");
        }

    }


    @RequestMapping("/out")
    public ModelAndView out() {
        System.out.println("===========out=================================");
        SecurityUtils.getSubject().logout();
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/unauth")
    public ModelAndView unauth() {
        return new ModelAndView("/sys/403");
    }

}
