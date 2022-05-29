package com.parachute.main.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;

import com.github.pagehelper.page.PageMethod;
import com.parachute.main.constant.SysConstants;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.entity.User;
import com.parachute.main.service.UserService;
import com.parachute.main.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-05-21 15:31:14
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Result login(@RequestBody User user){
        //获取用户名和密码
        String name = user.getUserName();
        String password = user.getPassword();
        //存入shiro Token当中
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //调用shiro 登录方法进行验证
            subject.login(token);
        }catch (UnknownAccountException uae) {
            return Result.of(false, SysConstants.USERNAME_ERROR);
        } catch (IncorrectCredentialsException ice) {
            return Result.of(false, SysConstants.PASSWORD_ERROR);
        }
        catch (AuthenticationException ae) {
            return Result.of(false, SysConstants.UNKNOWN_ERROR);
        }
        //获取基本信息返回给前端
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        User one = userService.getOne(queryWrapper);
        return Result.of(true,SysConstants.LOGIN_SUCCEED,one);
    }

    @RequiresPermissions("user:createUser")
    @RequestMapping("insert")
    public Result insert(@RequestBody User user){
        try {
            //校验前端数据
            ValidateConstants validate = validate(user);
            if (Boolean.TRUE.equals(validate.getFlag())){
                userService.register(user);
                return Result.of(true,SysConstants.REGISTER_SUCCEED);
            }else {
                return Result.of(false,validate.getMessage());
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }

    }

    private ValidateConstants validate(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        User one = userService.getOne(queryWrapper);
        //用户名校验
        if (one != null){
            return ValidateConstants.of(ValidateConstants.USER_NAME_EXISTS,false);
        }
        //性别校验
        if (!ValidateConstants.MALE.equals(user.getGender())
                && !ValidateConstants.FEMALE.equals(user.getGender())) {
            return ValidateConstants.of(ValidateConstants.GENDER_ERROR,false);
        }
        return ValidateConstants.of("",true);
    }

    @RequiresPermissions("user:queryUser")
    @RequestMapping("getUser")
    public Result getUser(Integer page,Integer pageSize){
        //分页查询
        PageMethod.startPage(page,pageSize);
        try {
            List<User> users = userService.getUser();
            PageInfo<User> info = new PageInfo<>(users);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }

    @RequiresPermissions("user:deleteUser")
    @RequestMapping("delete")
    public Result delete(Integer id){
        try {
            userService.removeById(id);
            return Result.of(true,SysConstants.DELETE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.DELETE_FAIL);
        }
    }

    @RequiresPermissions("user:updateUser")
    @RequestMapping("update")
    public Result update(@RequestBody User user){
        try {
            userService.updateById(user);
            return Result.of(true,"",SysConstants.DELETE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.DELETE_FAIL);
        }
    }


}

