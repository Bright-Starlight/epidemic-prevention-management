package com.parachute.main.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.main.entity.Permission;
import com.parachute.main.entity.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-05-21 15:31:15
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 授权
     *
     * @param empowerInfo 授权信息
     * @param id          id
     */
    @Insert("INSERT INTO user_role (user_id,role_id) VALUES(#{id},#{empowerInfo}) ")
    void empower(@Param("empowerInfo") String empowerInfo,@Param("id") Integer id);

    /**
     * 获得许可
     *
     * @param primaryPrincipal 小学校长
     * @return {@link List}<{@link Permission}>
     */
    @Select("SELECT p.permission from `user` u,user_role ur,role_permission rp,permission p WHERE u.id = ur.user_id and ur.role_id = rp.role_id and rp.permission_id = p.id\n" +
            "and u.user_name = #{primaryPrincipal}")
    List<Permission> getPermission(@Param("primaryPrincipal") String primaryPrincipal);
}

