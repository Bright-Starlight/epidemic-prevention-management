package com.parachute.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.main.constant.ValidateConstants;
import com.parachute.main.entity.Carrier;
import com.parachute.main.entity.Hospital;

import java.util.List;

/**
 * (Carrier)表服务接口
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
public interface CarrierService extends IService<Carrier> {

    /**
     * 插入
     *
     * @param carrier 航空公司
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
     * @param carrier 航空公司
     * @return boolean
     */
    ValidateConstants validate(Carrier carrier);


    /**
     * 得到医院
     *
     * @param fromHospital 从医院
     * @return {@link Hospital}
     */
    Integer getHospital(String fromHospital);

    /**
     * 在隔离
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getInIsolation();

    /**
     * 插入隔离
     *
     * @param carrier 航空公司
     */
    void insertIsolation(Carrier carrier);

    /**
     * 在隔离完成
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getInIsolationComplete();

    /**
     * 插入隔离完成
     *
     * @param carrier 航空公司
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
     * @param carrier 航空公司
     */
    void insertConfirm(Carrier carrier);

    /**
     * 插入治疗
     *
     * @param carrier 航空公司
     */
    void insertCure(Carrier carrier);

    /**
     * 获得新确认
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
     * 获得新治疗
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getNewCure();

    /**
     * 会死
     *
     * @return {@link List}<{@link Carrier}>
     */
    List<Carrier> getDie();

    /**
     * 插入模
     *
     * @param carrier 航空公司
     */
    void insertDie(Carrier carrier);

    List<Carrier> getNewDie();
}

