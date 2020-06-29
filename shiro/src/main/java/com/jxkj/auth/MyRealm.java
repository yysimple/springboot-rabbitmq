package com.jxkj.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * 功能描述：shiro 配置类
 *
 * @author wcx
 * @version 1.0
 */
public class MyRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if (!"wcx".equals(username)) {
            throw new UnknownAccountException("账户不存在");
        }
        return new SimpleAuthenticationInfo(username, "123", getName());
    }


}
