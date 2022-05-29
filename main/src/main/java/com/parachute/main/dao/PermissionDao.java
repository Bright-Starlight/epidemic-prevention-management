package com.parachute.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.main.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Permission)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-25 23:12:47
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

}

