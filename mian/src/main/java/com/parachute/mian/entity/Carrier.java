package com.parachute.mian.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (Carrier)表实体类
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@SuppressWarnings("serial")
public class Carrier extends Model<Carrier> {
    //id
    private Integer id;
    //姓名
    private String name;
    //身份证号码
    private String identityCard;
    //年龄
    private Integer age;
    //性别
    private String gender;
    //家庭地址
    private String homeAddress;
    //电话号码
    private Integer telephoneNumber;
    //接触原因
    private String cause;
    //所在医院
    private String formHosptial;
    //隔离时间
    private Date isolationTime;
    //是否确诊 1 确诊
    private String isConfirm;
    //是否 治愈 1治愈
    private String isCure;
    //是否死亡  1死亡
    private String isDie;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //修改人
    private Date updateName;
    //是否删除 1删除
    @TableLogic
    private String isDelete;


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

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getFormHosptial() {
        return formHosptial;
    }

    public void setFormHosptial(String formHosptial) {
        this.formHosptial = formHosptial;
    }

    public Date getIsolationTime() {
        return isolationTime;
    }

    public void setIsolationTime(Date isolationTime) {
        this.isolationTime = isolationTime;
    }

    public String getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(String isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getIsCure() {
        return isCure;
    }

    public void setIsCure(String isCure) {
        this.isCure = isCure;
    }

    public String getIsDie() {
        return isDie;
    }

    public void setIsDie(String isDie) {
        this.isDie = isDie;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

