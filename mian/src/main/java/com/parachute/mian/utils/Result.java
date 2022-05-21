package com.parachute.mian.utils;/*




/**
 * 结果
 *
 * @author machi
 * @date 2022/04/25
 */

import java.io.Serializable;

/**
 * 返回结果类
 *
 * @author machi
 * @date 2022/05/21
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    /**
     * 返回结果标识
     * 执行结果，true为执行成功 false为执行失败
     */
    private boolean flag;
    /**
     * 消息
     * 返回提示信息，主要用于页面提示信息
     */
    private String message;
    /**
     * 数据
     * 返回数据
     */
    private Object data;

    /**
     * 无返回数据
     *
     * @param flag    国旗
     * @param message 消息
     */
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    /**
     * 带返回数据
     *
     * @param flag    国旗
     * @param message 消息
     * @param data    数据
     */
    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    /**无返回数据
     * @param flag    国旗
     * @param message 消息
     * @return {@link Result}
     */
    public static Result of(boolean flag, String message) {
        return new Result(flag, message);
    }

    /**带返回数据
     * @param flag    国旗
     * @param message 消息
     * @param data    数据
     * @return {@link Result}
     */
    public static Result of(boolean flag, String message, Object data) {
        return new Result(flag, message,data);
    }


    public boolean isFlag() {
        return flag;
    }


    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }
}

