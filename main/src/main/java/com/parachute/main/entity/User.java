package com.parachute.main.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-05-21 15:31:18
 */
@SuppressWarnings("serial")
public class User extends Model<User> {
    //id
    private Integer id;
    //姓名
    private String name;
    //性别
    private String gender;
    //创建人
    private String createName;
    //身份证
    private String identityCard;
    //用户名
    private String userName;
    //密码
    private String password;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //修改人
    private Date updateName;
    //是否删除 1删除
    @TableLogic
    private String idDelete;


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

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateName() {
        return updateName;
    }

    public void setUpdateName(Date updateName) {
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

