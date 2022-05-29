package com.parachute.main.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-05-25 19:44:20
 */
@SuppressWarnings("serial")
public class User extends Model<User> {
    /**
     * id
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    private String updateName;
    /**
     * 是否删除 1删除
     */
    @TableLogic
    private String idDelete;
    /**
     * 授权信息
     */
    @TableField(exist = false)
    private String empowerInfo;
    /**
     * 用户名
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getEmpowerInfo() {
        return empowerInfo;
    }

    public void setEmpowerInfo(String empowerInfo) {
        this.empowerInfo = empowerInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(String idDelete) {
        this.idDelete = idDelete;
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

