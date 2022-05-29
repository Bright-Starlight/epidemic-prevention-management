package com.parachute.main.controller.carrier;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.main.constant.SysConstants;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.entity.Carrier;
import com.parachute.main.service.CarrierService;
import com.parachute.main.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 隔离控制器
 *
 * @author machi
 * @date 2022/05/25
 */
@RestController
@RequestMapping("carrier")
@Slf4j
public class IsolationController {

    @Autowired
    CarrierService carrierService;


    @RequiresPermissions("user:create")
    @RequestMapping("/insertIsolation")
    public Result insertIsolation(@RequestBody Carrier carrier){
        try {
            ValidateConstants validate = carrierService.validate(carrier);
            if (Boolean.TRUE.equals(validate.getFlag())){
                carrierService.insertIsolation(carrier);
                return Result.of(true, SysConstants.INSERT_SUCCESS);
            }
            return Result.of(false, validate.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }

    @RequiresPermissions("user:create")
    @RequestMapping("/insertIsolationComplete")
    public Result insertIsolationComplete(@RequestBody Carrier carrier){
        try {
            ValidateConstants validate = carrierService.validate(carrier);
            if (Boolean.TRUE.equals(validate.getFlag())){
                carrierService.insertIsolationComplete(carrier);
                return Result.of(true, SysConstants.INSERT_SUCCESS);
            }
            return Result.of(false, validate.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }

    /**
     * 将病人状态改为隔离完成
     *
     * @param id id
     * @return {@link Result}
     */
    @RequiresPermissions("user:update")
    @RequestMapping("updateToInIsolationComplete")
    public Result updateToInIsolationComplete(Integer id){
        try {
            LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Carrier::getIsIsolation,2)
                    .set(Carrier::getUpdateTime, LocalDateTime.now())
                    .eq(Carrier::getId,id);
            carrierService.update(wrapper);
            return Result.of(true,SysConstants.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }

    /**
     * 更将病人状态改为确诊
     *
     * @param id id
     * @return {@link Result}
     */
    @RequiresPermissions("user:update")
    @RequestMapping("updateToConfirm")
    public Result updateToConfirm(Integer id){
        try {
            LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Carrier::getIsConfirm,1)
                    .set(Carrier::getIsIsolation,2)
                    .set(Carrier::getUpdateTime,LocalDateTime.now())
                    .eq(Carrier::getId,id);
            carrierService.update(wrapper);
            return Result.of(true,SysConstants.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }

    /**
     * 将病人状态改为遇难
     *
     * @param id id
     * @return {@link Result}
     */
    @RequiresPermissions("user:update")
    @RequestMapping("updateToDie")
    public Result updateToDie(Integer id){
        try {
            LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Carrier::getIsConfirm,0)
                    .set(Carrier::getIsIsolation,2)
                    .set(Carrier::getIsCure,0)
                    .set(Carrier::getIsDie,1)
                    .set(Carrier::getUpdateTime,LocalDateTime.now())
                    .eq(Carrier::getId,id);
            carrierService.update(wrapper);
            return Result.of(true,SysConstants.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }
    @RequiresPermissions("user:query")
    @RequestMapping("getInIsolationComplete")
    public Result getInIsolationComplete(Integer page,Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getInIsolationComplete();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }
    @RequiresPermissions("user:query")
    @RequestMapping("getInIsolation")
    public Result getInIsolation(Integer page,Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getInIsolation();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }

}
