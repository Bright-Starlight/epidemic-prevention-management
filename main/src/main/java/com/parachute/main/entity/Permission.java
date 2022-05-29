package com.parachute.main.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Permission)表实体类
 *
 * @author makejava
 * @since 2022-05-25 23:12:47
 */
@SuppressWarnings("serial")
public class Permission extends Model<Permission> {
    /**
     * id
     */
    private Integer id;
    /**
     * 许可
     */
    private String permission;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

