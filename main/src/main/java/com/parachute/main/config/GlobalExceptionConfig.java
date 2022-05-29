package com.parachute.main.config;

import com.parachute.main.constant.SysConstants;
import com.parachute.main.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常配置
 *
 * @author machi
 * @date 2022/05/29
 */
@ControllerAdvice
public class GlobalExceptionConfig {

    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseBody
    public Result handle(UnauthenticatedException e) {
        return Result.of(false,SysConstants.NO_LOGIN);
    }

    /**
     * shiro 未授权异常
     */
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result handle(AuthorizationException e) {
        return Result.of(false,SysConstants.UNAUTHORIZED);
    }

}
