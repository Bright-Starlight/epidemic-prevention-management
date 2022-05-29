package com.parachute.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.main.entity.Permission;
import com.parachute.main.entity.User;


import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-05-21 15:31:21
 */
public interface UserService extends IService<User> {

    /**
     * 注册
     *
     * @param user 用户
     */
    void register(User user);

    List<Permission> getPermission(String primaryPrincipal);

    List<User> getUser();
}

