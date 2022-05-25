package com.parachute.main.constant;

import lombok.Data;

/**
 * 验证常量类
 *
 * @author machi
 * @date 2022/05/22
 */
@Data
public class ValidateConstants {
    public static final String TIME_ERROR = "时间不能大于当前时间";
    public static final String TELEPHONE_NUMBER_ERROR = "电话号码错误";
    public static final String ALREADY_EXISTED = "该人已添加";
    public static final String IDENTITY_CARD_ERROR = "身份证号错误";
    public static final String AGE_ERROR = "年龄错误";
    public static final String MALE = "男";
    public static final String FEMALE = "女";
    public static final String GENDER_ERROR = "性别错误";
    public static final Integer AGE_MAX = 130;
    public static final Integer AGE_MIN = 0;

    private  ValidateConstants(){}

    private ValidateConstants(String message, Boolean flag) {
        this.message = message;
        this.flag = flag;
    }

    private String message;
    private Boolean flag;



    public static ValidateConstants of(String msg,Boolean flag){
        return new ValidateConstants(msg,flag);
    }

}
