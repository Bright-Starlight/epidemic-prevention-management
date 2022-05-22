package com.parachute.main.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.main.constant.SysConstants;
import com.parachute.main.entity.Carrier;
import com.parachute.main.service.CarrierService;
import com.parachute.main.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
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
            carrierService.insert(carrier);
            return Result.of(false, SysConstants.INSERT_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.INSERT_FAIL);
        }
    }

    @RequestMapping("/getNewIntimate")
    public Result getData(Integer page, Integer pageSize){
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

}

