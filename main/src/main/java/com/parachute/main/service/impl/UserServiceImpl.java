package com.parachute.main.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.main.dao.UserDao;
import com.parachute.main.entity.Permission;
import com.parachute.main.entity.User;
import com.parachute.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-05-21 15:31:21
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public void register(User user) {
        String password = user.getPassword();
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setPassword(s);
        userDao.insert(user);
        empower(user.getEmpowerInfo(),user.getId());
    }

    @Override
    public List<Permission> getPermission(String primaryPrincipal) {
        return userDao.getPermission(primaryPrincipal);
    }

    @Override
    public List<User> getUser() {
        return this.list();

    }

    private void empower(String empowerInfo,Integer id) {
        userDao.empower(empowerInfo,id);
    }


}

