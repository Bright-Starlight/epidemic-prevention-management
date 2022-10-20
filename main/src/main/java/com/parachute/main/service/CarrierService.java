package com.parachute.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.entity.Carrier;
import com.parachute.main.entity.Hospital;
import com.parachute.main.entity.ReportVO;

import java.util.List;

/**
 * (Carrier)表服务接口
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
public interface CarrierService extends IService<Carrier> {

    /**
     * 新增病人
     *
     * @param carrier 病人
     */
    void insert(Carrier carrier);

    /**
     * 获取最新的密接人员
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> selectNewIntimate();

    /**
     * 验证
     *
     * @param carrier 病人
     * @return boolean
     */
    ValidateConstants validate(Carrier carrier);


    /**
     * 获取医院信息
     *
     * @param fromHospital 所在医院
     * @return {@link Hospital}
     */
    Integer getHospital(String fromHospital);

    /**
     * 获取隔离信息
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getInIsolation();

    /**
     * 添加隔离
     *
     * @param carrier 病人
     */
    void insertIsolation(Carrier carrier);

    /**
     * 获取隔离完成信息
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getInIsolationComplete();

    /**
     * 添加隔离完成
     *
     * @param carrier 病人
     */
    void insertIsolationComplete(Carrier carrier);

    /**
     * 获得确诊名单
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getConfirm();

    /**
     * 插入确诊名单
     *
     * @param carrier 病人
     */
    void insertConfirm(Carrier carrier);

    /**
     * 添加治疗
     *
     * @param carrier 病人
     */
    void insertCure(Carrier carrier);

    /**
     * 获得新增确诊
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getNewConfirm();

    /**
     * 得到治愈
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getCure();


    /**
     * 获得新增治愈
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getNewCure();

    /**
     * 获取遇难信息
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getDie();

    /**
     * 添加遇难
     *
     * @param carrier 病人
     */
    void insertDie(Carrier carrier);

    /**
     * 获得新增遇难
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getNewDie();

    /**
     * 得到累计报告
     *
     * @return {@link List}<{@link ReportVO}>
     */
    List<ReportVO> getReport();

    /**
     * 得到新增报告
     *
     * @return {@link List}<{@link ReportVO}>
     */
    List<ReportVO> getNewReport();
}

