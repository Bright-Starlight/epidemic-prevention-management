package com.parachute.main.controller.carrier;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.main.constant.SysConstants;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.entity.Carrier;
import com.parachute.main.service.CarrierService;
import com.parachute.main.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模控制器
 *
 * @author machi
 * @date 2022/05/25
 */
@RestController
@RequestMapping("carrier")
@Slf4j
public class DieController {

    @Autowired
    CarrierService carrierService;

    @RequestMapping("/insertDie")
    public Result insertDie(@RequestBody Carrier carrier){
        try {
            ValidateConstants validate = carrierService.validate(carrier);
            if (Boolean.TRUE.equals(validate.getFlag())){
                carrierService.insertDie(carrier);
                return Result.of(true, SysConstants.INSERT_SUCCESS);
            }
            return Result.of(false, validate.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }


    @RequestMapping("getDie")
    public Result getDie(Integer page, Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getDie();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.SERVER_EXCEPTION);
        }
    }

    @RequestMapping("getNewDie")
    public Result getNewDie(Integer page,Integer pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Carrier> data = carrierService.getNewDie();
            PageInfo<Carrier> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }
}
