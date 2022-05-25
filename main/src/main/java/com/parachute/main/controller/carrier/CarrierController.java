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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * (Carrier)表控制层
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@RestController
@RequestMapping("carrier")
@Slf4j
public class CarrierController  {

    @Autowired
    private CarrierService carrierService;




    @RequestMapping("/update")
    public Result update(@RequestBody Carrier carrier){
        try {
            ValidateConstants validate = carrierService.validate(carrier);
            if (Boolean.TRUE.equals(validate.getFlag())){
                LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
                wrapper.set(Carrier::getName,carrier.getName())
                        .set(Carrier::getIdentityCard,carrier.getIdentityCard())
                        .set(Carrier::getAge,carrier.getAge())
                        .set(Carrier::getGender,carrier.getGender())
                        .set(Carrier::getHomeAddress,carrier.getHomeAddress())
                        .set(Carrier::getTelephoneNumber,carrier.getTelephoneNumber())
                        .set(Carrier::getCause,carrier.getCause())
                        .set(Carrier::getFromHospital,carrier.getFromHospital())
                        .set(Carrier::getIsolationTime,carrier.getIsolationTime())
                        .set(Carrier::getUpdateTime, LocalDateTime.now())
                        .eq(Carrier::getId,carrier.getId());
                carrierService.update(wrapper);
                return Result.of(true,SysConstants.UPDATE_SUCCESS);
            }
            return Result.of(false,validate.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }


    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            carrierService.removeById(id);
            return Result.of(true,SysConstants.DELETE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.DELETE_FAIL);
        }
    }



















}

