package com.zyx.secure.realm;

import com.zyx.jopo.UserPrincipal;
import com.zyx.model.SysRole;
import com.zyx.model.SysUser;
import com.zyx.service.SysRoleService;
import com.zyx.service.SysUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import static com.zyx.jopo.UserPrincipal.PrincipType.ADMIN;

/**
 * 域（认证所需数据源）封装
 *
 * @author Administrator
 */

public class StatelessRealm extends AuthorizingRealm {
    private static Logger logger = Logger.getLogger(StatelessRealm.class);

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        StatelessToken statelessToken = (StatelessToken) token;

        UserPrincipal userPrincipal = statelessToken.getUserPrincipal();

        String pass = statelessToken.getPassword();

        SysUser user = sysUserService.getUserByNamePass(userPrincipal.getUsername(), pass);

		/*User user = userService.getUserByPhonePass(userPrincipal.getUsername(),
                pass);*/

        if (user == null) {
            throw new AccountException();
        }

        SysRole role = sysRoleService.selectByRoleId(user.getRoleId());

        if (role == null) {
            throw new AccountException();
        }

        userPrincipal.setMenuPerm(role.getMenuPerm());

        if (user.getUsername().equals("admin")) {
            userPrincipal.setPrincipType(ADMIN);
        }

        statelessToken.setUser(user);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        SimplePrincipalCollection simplePrincipalCollection = new SimplePrincipalCollection();
        simplePrincipalCollection.add(userPrincipal, getName());
        authenticationInfo.setPrincipals(simplePrincipalCollection);
        authenticationInfo.setCredentials(pass);
        logger.info("认证成功!!! principalls:" + userPrincipal + " credentiasl:"
                + pass);

        return authenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {

        boolean result = token instanceof StatelessToken;

        return result;

    }

    /**
     * 获取授权信息,这里会进行判断，如果是认证登录通过，则拥有user:*权限 如果是
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        UserPrincipal userPrincipal = (UserPrincipal) principals.getPrimaryPrincipal();
        UserPrincipal.PrincipType principType = userPrincipal.getPrincipType();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(principType.getRoleName());
        String[] menuPermArray = {};
        if (userPrincipal.getMenuPerm() != null) {
            menuPermArray = userPrincipal.getMenuPerm().split(",");
        }

        for (String _perm : menuPermArray) {
            if (!_perm.equals("")) {
                authorizationInfo.addStringPermission(_perm);
            }
        }

        switch (principType) {
            case ADMIN:
                authorizationInfo.addStringPermission("menu:sys:*");
                break;
        }
        logger.info("authrizations: Roles:" + authorizationInfo.getRoles()
                + " permesins" + authorizationInfo.getStringPermissions());

        return authorizationInfo;
    }

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    /**
     * 支持or and not 关键词  不支持and or混用
     *
     * @param principals
     * @param permission
     * @return
     */
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

}
