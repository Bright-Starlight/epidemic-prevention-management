package com.parachute.main.controller;




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

    @RequestMapping("/insert")
    public Result insert(@RequestBody Carrier carrier){
        try {
            ValidateConstants validate = carrierService.validate(carrier);
            if (Boolean.TRUE.equals(validate.getFlag())){
                carrierService.insert(carrier);
                return Result.of(true, SysConstants.INSERT_SUCCESS);
            }
            return Result.of(false, validate.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }

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

    @RequestMapping("/insertCure")
    public Result insertCure(@RequestBody Carrier carrier){
        try {
            ValidateConstants validate = carrierService.validate(carrier);
            if (Boolean.TRUE.equals(validate.getFlag())){
                carrierService.insertCure(carrier);
                return Result.of(true, SysConstants.INSERT_SUCCESS);
            }
            return Result.of(false, validate.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }



    @RequestMapping("/getNewIntimate")
    public Result getNewIntimate(Integer page, Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.selectNewIntimate();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }

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

    @RequestMapping("updateToInIsolation")
    public Result updateToInIsolation(Integer id){
        try {
            LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Carrier::getIsIsolation,1)
                    .set(Carrier::getUpdateTime,LocalDateTime.now())
                    .eq(Carrier::getId,id);
            carrierService.update(wrapper);
            return Result.of(true,SysConstants.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }

    @RequestMapping("updateToInIsolationComplete")
    public Result updateToInIsolationComplete(Integer id){
        try {
            LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Carrier::getIsIsolation,2)
                    .set(Carrier::getUpdateTime,LocalDateTime.now())
                    .eq(Carrier::getId,id);
            carrierService.update(wrapper);
            return Result.of(true,SysConstants.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }
    @RequestMapping("updateToCure")
    public Result updateToCure(Integer id){
        try {
            LambdaUpdateWrapper<Carrier> wrapper = Wrappers.lambdaUpdate();
            wrapper.set(Carrier::getIsCure,1)
                    .set(Carrier::getUpdateTime,LocalDateTime.now())
                    .set(Carrier::getIsConfirm,0)
                    .eq(Carrier::getId,id);
            carrierService.update(wrapper);
            return Result.of(true,SysConstants.UPDATE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.UPDATE_FAIL);
        }
    }
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

    @RequestMapping("getNewCure")
    public Result getNewCure(Integer page,Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getNewCure();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }
    @RequestMapping("getCure")
    public Result getCure(Integer page,Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getCure();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }

}

