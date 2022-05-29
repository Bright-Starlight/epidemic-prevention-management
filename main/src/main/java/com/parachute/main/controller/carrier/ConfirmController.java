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
 * 确认控制器
 *
 * @author machi
 * @date 2022/05/25
 */
@RestController
@RequestMapping("carrier")
@Slf4j
public class ConfirmController {

    @Autowired
    CarrierService carrierService;

    @RequiresPermissions("user:create")
    @RequestMapping("/insertConfirm")
    public Result insertConfirm(@RequestBody Carrier carrier){
        try {
            ValidateConstants validate = carrierService.validate(carrier);
            if (Boolean.TRUE.equals(validate.getFlag())){
                carrierService.insertConfirm(carrier);
                return Result.of(true, SysConstants.INSERT_SUCCESS);
            }
            return Result.of(false, validate.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }

    /**
     * 将病人状态改为治愈
     *
     * @param id id
     * @return {@link Result}
     */
    @RequiresPermissions("user:update")
    @RequestMapping("updateToCure")
    public Result updateToCure(Integer id){
        try {
            LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Carrier::getIsCure,1)
                    .set(Carrier::getUpdateTime, LocalDateTime.now())
                    .set(Carrier::getIsConfirm,0)
                    .eq(Carrier::getId,id);
            carrierService.update(wrapper);
            return Result.of(true,SysConstants.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }
    @RequiresPermissions("user:query")
    @RequestMapping("getConfirm")
    public Result getConfirm(Integer page,Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getConfirm();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }
    @RequiresPermissions("user:query")
    @RequestMapping("getNewConfirm")
    public Result getNewConfirm(Integer page,Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getNewConfirm();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }

}
