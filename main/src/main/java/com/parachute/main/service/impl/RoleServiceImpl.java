package com.parachute.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.main.dao.RoleDao;
import com.parachute.main.entity.Role;
import com.parachute.main.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2022-05-25 23:12:51
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}

