package com.parachute.main.realm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.parachute.main.entity.Permission;
import com.parachute.main.entity.User;
import com.parachute.main.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * jdbc域
 *
 * @author machi
 * @date 2022/04/17
 */
public class JdbcRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权信息
     *
     * @param principalCollection 主要收集
     * @return {@link AuthorizationInfo}
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        List<Permission> permissions = userService.getPermission(primaryPrincipal);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> p = new ArrayList<>();
        if (permissions != null){
            permissions.forEach(permission-> p.add(permission.getPermission()));
            info.addStringPermissions(p);

        }else {
            throw new AuthorizationException();
        }



        return info;

    }

    /**
     * 认证用户名和密码
     *
     * @param authenticationToken 身份验证令牌
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        Object principal = authenticationToken.getPrincipal();
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,principal);
        //查询数据库进行比较
        User user = userService.getOne(wrapper);
        //用户名是否为空
        if (user == null){
            throw new UnknownAccountException();
        }
        String password = user.getPassword();

        return new SimpleAuthenticationInfo(principal,password,getName());
    }
}
