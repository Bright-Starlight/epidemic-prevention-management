package com.parachute.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.main.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-25 23:12:49
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {

}

