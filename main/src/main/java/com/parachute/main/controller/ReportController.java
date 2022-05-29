package com.parachute.main.controller;

import com.parachute.main.constant.SysConstants;
import com.parachute.main.entity.ReportVO;
import com.parachute.main.service.CarrierService;
import com.parachute.main.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * 报告控制器
 *
 * @author machi
 * @date 2022/05/29
 */
@RestController
@RequestMapping("report")
@Slf4j
public class ReportController {

    @Autowired
    CarrierService carrierService;

    /**
     * 获取累计数据
     *
     * @return {@link Result}
     */
    @RequestMapping("getReport")
    public Result getReport(){
        try {
            List<ReportVO> data = carrierService.getReport();
            return Result.of(true,"",data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.SERVER_EXCEPTION);
        }

    }

    /**
     * 获取新增数据
     *
     * @return {@link Result}
     */
    @RequestMapping("getNewReport")
    public Result getNewReport(){
        try {
            List<ReportVO> data = carrierService.getNewReport();
            return Result.of(true,"",data);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.of(false, SysConstants.SERVER_EXCEPTION);
        }

    }
}
