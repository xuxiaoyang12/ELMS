package com.mxiaixy.shiro;

import com.mxiaixy.mapper.UserMapper;
import com.mxiaixy.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mxia on 2017/3/10.
 */
@Component
public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();

        User user = userMapper.findByUsername(username);
        if(user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }


        return null;
    }
}
