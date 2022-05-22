package com.parachute.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.main.dao.UserDao;
import com.parachute.main.entity.User;
import com.parachute.main.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-05-21 15:31:21
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

