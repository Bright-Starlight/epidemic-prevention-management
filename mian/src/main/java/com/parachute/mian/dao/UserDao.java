package com.parachute.mian.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.mian.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-21 15:31:15
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

