package com.parachute.main.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.parachute.main.constant.SysConstants;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.entity.Hospital;
import com.parachute.main.service.HospitalService;
import com.parachute.main.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * (Hospital)表控制层
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@RestController
@RequestMapping("hospital")
@Slf4j
public class HospitalController {
    /**
     * 服务对象
     */
    @Resource
    private HospitalService hospitalService;


    @RequestMapping("/getAll")
    public Result getAll(){
        try {
            List<HashMap<String, String>> data =  hospitalService.getAll();
            return Result.of(true,"",data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }

    @RequestMapping("/getData")
    public Result getData(Integer  page,Integer  pageSize){
        try {
            PageMethod.startPage(page,pageSize);
            List<Hospital> data =  hospitalService.getPage();
            PageInfo<Hospital> info = new PageInfo<>(data);
            return Result.of(true,"",info);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.SERVER_EXCEPTION);
        }
    }


    @RequestMapping("/insert")
    public Result insert(@RequestBody Hospital hospital){
        try {
            ValidateConstants validate = hospitalService.validate(hospital);
            if (Boolean.TRUE.equals(validate.getFlag())){
                hospitalService.insert(hospital);
                return Result.of(true,SysConstants.INSERT_SUCCESS);
            }
            return Result.of(false,validate.getMessage());
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.INSERT_FAIL);
        }
    }


    @RequestMapping("/update")
    public Result update(@RequestBody Hospital hospital){
        try {
            ValidateConstants validate = hospitalService.validate(hospital);
            if (Boolean.TRUE.equals(validate.getFlag())){
                LambdaUpdateWrapper<Hospital> wrapper = Wrappers.lambdaUpdate();
                wrapper.set(Hospital::getHospitalName,hospital.getHospitalName())
                        .set(Hospital::getAddress,hospital.getAddress())
                        .set(Hospital::getTelephoneNumber,hospital.getTelephoneNumber())
                        .set(Hospital::getUpdateTime, LocalDateTime.now())
                        .eq(Hospital::getId,hospital.getId());
                hospitalService.update(wrapper);
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
           hospitalService.removeById(id);
            return Result.of(true,SysConstants.DELETE_SUCCESS);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false,SysConstants.DELETE_FAIL);
        }
    }

}

