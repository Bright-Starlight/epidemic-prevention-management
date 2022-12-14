package com.parachute.main.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2022-05-25 23:12:49
 */
@SuppressWarnings("serial")
public class Role extends Model<Role> {
    /**
     * id
     */
    private Integer id;
    /**
     * 角色
     */
    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

