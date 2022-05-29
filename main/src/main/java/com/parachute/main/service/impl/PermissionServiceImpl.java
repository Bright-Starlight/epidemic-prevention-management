package com.parachute.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.main.dao.PermissionDao;
import com.parachute.main.entity.Permission;
import com.parachute.main.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * (Permission)表服务实现类
 *
 * @author makejava
 * @since 2022-05-25 23:12:48
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

}

